package org.greatfree.dip.streaming.unicast.child;

import org.greatfree.concurrency.reactive.NotificationThreadCreatable;
import org.greatfree.dip.multicast.message.RootIPAddressBroadcastNotification;

// Created: 03/22/2020, Bing Li
class RootIPAddressBroadcastNotificationThreadCreator implements NotificationThreadCreatable<RootIPAddressBroadcastNotification, RootIPAddressBroadcastNotificationThread>
{

	@Override
	public RootIPAddressBroadcastNotificationThread createNotificationThreadInstance(int taskSize)
	{
		return new RootIPAddressBroadcastNotificationThread(taskSize);
	}

}
