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

**Step 3**: Configure Spring Boot to Use MinIO
```yml
aws:
  s3:
    endpoint: http://localhost:9000
    region: us-east-1
    access-key: admin
    secret-key: admin123
    bucket-name: my-local-bucket

```
