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
Ceates isolated network environments with customizable subnets, security groups, and NACLs. Multi-AZ deployment across availability zones ensures high availability and fault tolerance.

**AWS Certificate Manager (ACM)**
Automates SSL/TLS certificate provisioning and renewal, eliminating manual certificate management overhead while ensuring secure communications.

**Amazon CloudFront**
It serves as the global content delivery network, caching static React.js assets at edge locations worldwide. 
This reduces latency and improves user experience while offloading traffic from origin servers.

**Amazon API Gateway**
Provides managed API endpoints with throttling, authentication, and monitoring capabilities. 
It serves as the entry point for microservices, handling request routing, validation, and response transformation.


