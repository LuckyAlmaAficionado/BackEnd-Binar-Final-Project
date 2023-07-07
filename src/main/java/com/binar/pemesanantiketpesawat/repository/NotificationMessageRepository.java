package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.NotificationMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface NotificationMessageRepository extends JpaRepository<NotificationMessage, Integer> {
    List<NotificationMessage> findByUuidUser(UUID uuidRequest);

    @Modifying
    @Query("DELETE FROM NotificationMessage e WHERE e.uuidUser = ?1")
    void deleteByUuidUser(UUID uuidUser);
}