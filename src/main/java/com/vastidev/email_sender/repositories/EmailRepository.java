package com.vastidev.email_sender.repositories;

import com.vastidev.email_sender.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
