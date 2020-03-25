package org.greatfree.dip.cps.cache.coordinator.front;

import org.greatfree.concurrency.reactive.NotificationThreadCreatable;
import org.greatfree.dip.cps.threetier.message.FrontNotification;

// Created: 07/07/2018, Bing Li
public class FrontNotificationThreadCreator implements NotificationThreadCreatable<FrontNotification, FrontNotificationThread>
{

	@Override
	public FrontNotificationThread createNotificationThreadInstance(int taskSize)
	{
		return new FrontNotificationThread(taskSize);
	}

}
