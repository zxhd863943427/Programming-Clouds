package org.greatfree.testing.coordinator.admin;

import org.greatfree.concurrency.reactive.NotificationThreadCreatable;
import org.greatfree.testing.message.ShutdownCrawlServerNotification;

/*
 * The creator here attempts to create instances of ShutdownCrawlServerThread. It works with the notification dispatcher to schedule the tasks concurrently. 11/27/2014, Bing Li
 */

// Created: 11/27/2014, Bing Li
public class ShutdownCrawlServerThreadCreator implements NotificationThreadCreatable<ShutdownCrawlServerNotification, ShutdownCrawlServerThread>
{
	@Override
	public ShutdownCrawlServerThread createNotificationThreadInstance(int taskSize)
	{
		return new ShutdownCrawlServerThread(taskSize);
	}
}
