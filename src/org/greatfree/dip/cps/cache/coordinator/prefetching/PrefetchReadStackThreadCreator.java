package org.greatfree.dip.cps.cache.coordinator.prefetching;

import org.greatfree.concurrency.reactive.NotificationObjectThreadCreatable;
import org.greatfree.dip.cps.cache.message.FetchStackNotification;

// Created: 08/23/2018, Bing Li
public class PrefetchReadStackThreadCreator implements NotificationObjectThreadCreatable<FetchStackNotification, PrefetchReadStackThread>
{

	@Override
	public PrefetchReadStackThread createNotificationThreadInstance(int taskSize)
	{
		return new PrefetchReadStackThread(taskSize);
	}

}
