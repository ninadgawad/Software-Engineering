## S3 Testing
 S3-compatible local storage solution 

| Use Case |	Option's |
|----------|-----------|
|Lightweight local storage|	MinIO|
|Full AWS services mock	|LocalStack|
|Unit testing in Java|	S3Mock|
|Alternative S3 backend	|Zenko CloudServer|


## MinIO with Spring Boot 

**Step1:** Run MinIO via Docker
```sh
docker run -p 9000:9000 -p 9090:9090 \
    -e "MINIO_ROOT_USER=admin" \
    -e "MINIO_ROOT_PASSWORD=admin123" \
    quay.io/minio/minio server /data --console-address ":9090"
```
MinIO Web UI: Open http://localhost:9090 
S3 API Endpoint: http://localhost:9000


**Step2:** Add AWS SDK to Spring Boot
```xml
<dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>s3</artifactId>
    <version>2.20.138</version>
</dependency>
```

**Step 3:** Configure Spring Boot to Use MinIO
```yml
aws:
  s3:
    endpoint: http://localhost:9000
    region: us-east-1
    access-key: admin
    secret-key: admin123
    bucket-name: my-local-bucket

```

 **Step 4:** Create an S3 Client Bean
 ```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .endpointOverride(URI.create("http://localhost:9000"))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("admin", "admin123") 
                ))
                .region(Region.US_EAST_1)
                .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build()) // Important for MinIO
                .build();
    }
}

```

**Step 5:** Implement S3 Service
```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.nio.file.Paths;

@Service
public class S3Service {
    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void createBucket() {
        if (!s3Client.listBuckets().buckets().stream().anyMatch(b -> b.name().equals(bucketName))) {
            s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
            System.out.println("Bucket created: " + bucketName);
        }
    }

    public void uploadFile(String filePath, String key) {
        s3Client.putObject(PutObjectRequest.builder().bucket(bucketName).key(key).build(),
                RequestBody.fromFile(Paths.get(filePath)));
        System.out.println("File uploaded: " + key);
    }

    public void listFiles() {
        ListObjectsV2Response response = s3Client.listObjectsV2(ListObjectsV2Request.builder().bucket(bucketName).build());
        response.contents().forEach(s3Object -> System.out.println("File: " + s3Object.key()));
    }

    public void downloadFile(String key, String destinationPath) {
        s3Client.getObject(GetObjectRequest.builder().bucket(bucketName).key(key).build(),
                Paths.get(destinationPath));
        System.out.println("File downloaded: " + key);
    }

    public void deleteFile(String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(key).build());
        System.out.println("File deleted: " + key);
    }
}

```

 **Step 6:** Test the S3 Service
 ```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {
    private final S3Service s3Service;

    public MainApplication(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) {
        s3Service.createBucket();
        s3Service.uploadFile("test.txt", "test-file");
        s3Service.listFiles();
        s3Service.downloadFile("test-file", "downloaded-test.txt");
        s3Service.deleteFile("test-file");
    }
}

```


**Step7: ** Running the Application
- Start MinIO
```sh
docker run -p 9000:9000 -p 9090:9090 \
    -e "MINIO_ROOT_USER=admin" \
    -e "MINIO_ROOT_PASSWORD=admin123" \
    quay.io/minio/minio server /data --console-address ":9090"
```
- Run the Spring boot app
```sh
mvn spring-boot:run
```
