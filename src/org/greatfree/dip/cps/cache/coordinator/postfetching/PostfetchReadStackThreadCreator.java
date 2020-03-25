package org.greatfree.dip.cps.cache.coordinator.postfetching;

import org.greatfree.concurrency.reactive.NotificationObjectThreadCreatable;
import org.greatfree.dip.cps.cache.message.FetchStackNotification;

// Created: 08/23/2018, Bing Li
public class PostfetchReadStackThreadCreator implements  NotificationObjectThreadCreatable<FetchStackNotification, PostfetchReadStackThread>
{

	@Override
	public PostfetchReadStackThread createNotificationThreadInstance(int taskSize)
	{
		return new PostfetchReadStackThread(taskSize);
	}

}
