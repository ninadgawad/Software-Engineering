## Amazon Web Services	

AWS offers a comprehensive suite of services, enabling architects to design highly available, scalable, and secure applications. 
Key capabilities include:
- Managed container orchestration for microservices and web apps.
- Fully managed databases for relational and NoSQL workloads.
- Event streaming and messaging for decoupled architectures.
- Scalable storage solutions for structured and unstructured data.
- Analytics and batch processing for real-time and scheduled data workloads.

## Popular AWS Services
| Application Layer          | AWS Service(s)                           |
|----------------------------|------------------------------------------|
| Container Orchestration    | Amazon ECS, EKS, AWS Fargate, App Runner |
| Container Registry         | Amazon ECR                               |
| Node.js/React.js App       | ECS/EKS/App Runner                       |
| Spring Boot Core Service   | ECS/EKS/App Runner                       |
| Relational Database        | Amazon RDS for PostgreSQL                |
| Document Store             | Amazon DocumentDB (MongoDB compatible)   |
| Object Store               | Amazon S3                                |
| Event Streaming            | Amazon MSK (Managed Kafka)               |
| Analytics/Batch Processing | AWS Batch, Amazon EMR (Spark), S3        |


## Core AWS Services Overview

**Amazon Elastic Container Service (ECS):**
It serves as the primary container orchestration platform, offering deep AWS integration and simplified management. 
For teams requiring Kubernetes, Amazon Elastic Kubernetes Service (EKS) provides a managed Kubernetes control plane with enterprise-grade security and scalability.

**AWS Fargate:**
It eliminates infrastructure management by providing serverless compute for containers, automatically scaling based on demand while maintaining security isolation. 
This combination allows architects to focus on application logic rather than infrastructure provisioning.

**Application Load Balancer (ALB)**
Provides Layer 7 load balancing with advanced routing capabilities, SSL termination, and health checks. 
The service integrates seamlessly with ECS and EKS for automated service discovery and traffic distribution.

**Amazon VPC (Virtual Private Cloud)**
Ceates isolated network environments with customizable subnets, security groups, and NACLs. 
Multi-AZ deployment across availability zones ensures high availability and fault tolerance.

**AWS Certificate Manager (ACM)**
Automates SSL/TLS certificate provisioning and renewal, eliminating manual certificate management overhead while ensuring secure communications.

**Amazon CloudFront**
It serves as the global content delivery network, caching static React.js assets at edge locations worldwide. 
This reduces latency and improves user experience while offloading traffic from origin servers.

**Amazon API Gateway**
Provides managed API endpoints with throttling, authentication, and monitoring capabilities. 
It serves as the entry point for microservices, handling request routing, validation, and response transformation.

**Amazon Elastic Container Registry (ECR)** 
Stores Docker images with vulnerability scanning, lifecycle policies, and fine-grained access controls. 
Integration with ECS/EKS enables automated deployments and rollbacks.

**Amazon RDS PostgreSQL** 
It provides managed relational database services with automated backups, patch management, and Multi-AZ deployment for high availability. 
Read replicas can be deployed across regions for read scaling and disaster recovery.

**Amazon RDS Proxy**
Manages database connections efficiently, reducing connection overhead and improving application performance during traffic spikes.

**Amazon S3**
S3 provides virtually unlimited object storage with multiple storage classes for cost optimization. 
S3 integrates with CloudFront for content delivery and supports versioning, lifecycle policies, and cross-region replication.

**Amazon MSK (Managed Streaming for Apache Kafka)
Provides fully managed Kafka clusters with automatic provisioning, monitoring, and scaling. 
MSK handles high-throughput event streaming for real-time notifications and data processing.

**Amazon SQS and Amazon SNS**
Complement Kafka for simpler messaging patterns, providing reliable message queuing and pub/sub capabilities with dead letter queues for error handling.

**Amazon EMR**
Runs Apache Spark clusters for large-scale data processing and analytics workloads. 
EMR integrates with S3 for data lake architectures and supports automatic scaling based on workload demands.

**AWS Glue**
Provides serverless ETL capabilities for data preparation and transformation, with a managed catalog for metadata management and schema evolution.

**Amazon CloudWatch**
Ccollects metrics, logs, and traces from all AWS services. 
Custom metrics and alarms enable proactive monitoring and automated responses to system events.



