# EmailSender API

## Overview

The **EmailSender API** is designed to send emails using Amazon's Simple Email Service (SES). Initially, it was developed as a standalone service dedicated to sending emails. Later, it was refactored to act as a Kafka consumer, enabling it to automatically send a welcome message to users who register in another microservice.

## Features

- **Amazon SES Integration**: Utilizes AWS SES to send emails securely and reliably.
- **Kafka Consumer**: Listens to a Kafka topic for user registration events and sends a personalized welcome email to new users.

## Technologies

- **Java 17**
- **Spring Boot**
- **Postgresql**
- **Amazon SES**
- **Kafka**
- **Docker**
- **Docker Hub**
- **CI/CD with GitHub Actions**

## Setup and Configuration

### Prerequisites

- Java 17
- Maven 3.6+
- Docker (optional, for running Kafka and Zookeeper)
- AWS SES credentials

### Getting Started

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/emailsender.git
   cd emailsender

2. **Configure AWS SES:**
  ```properties
  cloud.aws.credentials.access-key=YOUR_AWS_ACCESS_KEY
  cloud.aws.credentials.secret-key=YOUR_AWS_SECRET_KEY
  cloud.aws.region.static=YOUR_AWS_REGION

3. **Kafka Configuration:**
   Ensure Kafka is running and accessible on `localhost:9092`. The `docker-compose.yml` file in the project root can be used to start Kafka and Zookeeper containers.

   ```bash
   docker-compose up -d

4. **Build e Execução da Aplicação:**

   ```bash
   mvn clean install
   java -jar target/email-sender.jar

## Usage

- **Standalone Email Sending**: The API can send emails independently by posting to the `/sending-email` endpoint.
- **Kafka Consumer**: The API listens to the Kafka topic `user_registration_topic`. When a new user is registered in the TaskManager microservice, this API sends a welcome email.

### Example

**Sending an Email**:

```bash
POST /sending-email
Content-Type: application/json

{
  "ownerRef": "Admin",
  "emailFrom": "your-email@domain.com",
  "emailTo": "user-email@domain.com",
  "subject": "Welcome!",
  "text": "Welcome to our service!"
}

## Contributing
Feel free to fork this repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.
  
     
