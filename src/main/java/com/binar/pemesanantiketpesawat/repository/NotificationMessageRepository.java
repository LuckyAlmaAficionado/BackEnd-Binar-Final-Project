package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.NotificationMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationMessageRepository extends JpaRepository<NotificationMessage, Integer> {
    List<NotificationMessage> findByUuidUser(UUID uuidRequest);
}
