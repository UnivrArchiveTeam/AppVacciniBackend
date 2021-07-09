package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.id.IdNotification;
import com.kodikas.appvaccinibackend.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, IdNotification> {
	public List<Notification> findAllByIdNotification_FiscalCode(String fiscalCode);
}
