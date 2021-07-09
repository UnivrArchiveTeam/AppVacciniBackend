package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Notification;
import com.kodikas.appvaccinibackend.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;

	public List<Notification> getNotifications(String fiscalCode) {
		return notificationRepository.findAllByIdNotification_FiscalCode(fiscalCode);
	}

	public Notification addNotification(Notification notification) {
		return notificationRepository.save(notification);
	}
}
