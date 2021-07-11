package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.id.IdNotification;
import com.kodikas.appvaccinibackend.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, IdNotification> {
	List<Notification> findAllByIdNotification_FiscalCode(String fiscalCode);
	void deleteNotificationByIdNotification_CampaignIdAndIdNotification_FiscalCode(long campaignId, String fiscalCode);

}
