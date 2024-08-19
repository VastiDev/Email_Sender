# EmailSender API

## Overview

The **EmailSender API** is designed to send emails using Amazon's Simple Email Service (SES). Initially, it was developed as a standalone service dedicated to sending emails. Later, it was refactored to act as a Kafka consumer, enabling it to automatically send a welcome message to users who register in another microservice.

## Features

- **Amazon SES Integration**: Utilizes AWS SES to send emails securely and reliably.
- **Kafka Consumer**: Listens to a Kafka topic for user registration events and sends a personalized welcome email to new users.

## Technologies

![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.4-brightgreen?style=for-the-badge)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13-blue?style=for-the-badge)
![Amazon SES](https://img.shields.io/badge/Amazon%20SES-SES-orange?style=for-the-badge)
![Kafka](https://img.shields.io/badge/Kafka-2.8.0-red?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-20.10.7-blue?style=for-the-badge)
![Docker Hub](https://img.shields.io/badge/Docker%20Hub-Repository-blue?style=for-the-badge)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-CI%2FCD-lightgrey?style=for-the-badge)

<p align="center">
  <img src="https://github.com/vastidev/emailsender/raw/main/artificial-intelligence.gif" alt="Demo" width="400">
</p>

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

