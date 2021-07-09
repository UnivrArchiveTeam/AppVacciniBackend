package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Notification;
import com.kodikas.appvaccinibackend.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/notifications")
public class NotificationController {
	private final NotificationService notificationService;

	@GetMapping(path = "/{fiscalCode}")
	public List<Notification> getNotifications(@PathVariable String fiscalCode) {
		return notificationService.getNotifications(fiscalCode);
	}

	@PostMapping
	public Notification addNotification(Notification notification) {
		return notificationService.addNotification(notification);
	}
}
