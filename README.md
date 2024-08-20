# EmailSender API

## Overview

The **EmailSender API** is designed to send emails using Amazon's Simple Email Service (SES). Initially, it was developed as a standalone service dedicated to sending emails. Later, it was refactored to act as a Kafka consumer, enabling it to automatically send a welcome message to users who register in another microservice.

## Features

- **Amazon SES Integration**: Utilizes AWS SES to send emails securely and reliably.
- **Kafka Consumer**: Listens to a Kafka topic for user registration events and sends a personalized welcome email to new users.

## Technologies

## Technologies

## Technologies

![Java](https://img.shields.io/badge/Java-blue?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-brightgreen?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue?style=for-the-badge&logo=postgresql&logoColor=white)
![Amazon SES](https://img.shields.io/badge/Amazon%20SES-orange?style=for-the-badge&logo=amazon&logoColor=white)
![Kafka](https://img.shields.io/badge/Kafka-red?style=for-the-badge&logo=apache-kafka&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-blue?style=for-the-badge&logo=docker&logoColor=white)
![Docker Hub](https://img.shields.io/badge/Docker%20Hub-blue?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-lightgrey?style=for-the-badge&logo=github-actions&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-BC0200?style=for-the-badge&logo=lombok&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)




<p align="center">
  <img src="https://github.com/VastiDev/Email_Sender/blob/feature/develop/artificial-intelligence.gif?raw=true" alt="Demo" width="600">
</p>


## Setup and Configuration

### Prerequisites

- Java 17
- Maven 3.6+
- Docker (optional, for running Kafka and Zookeeper)
- AWS SES credentials

### Usage

- **Standalone Email Sending**: The API can send emails independently by posting to the `/sending-email` endpoint.
- **Kafka Consumer**: The API listens to the Kafka topic `user_registration_topic`. When a new user is registered in the TaskManager microservice, this API sends a welcome email.
  

### Getting Started


1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/emailsender.git
   cd emailsender

2. **Configure AWS SES:**
   ```bash
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
