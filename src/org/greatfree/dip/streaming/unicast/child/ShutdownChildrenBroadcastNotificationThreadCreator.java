package org.greatfree.dip.streaming.unicast.child;

import org.greatfree.concurrency.reactive.NotificationThreadCreatable;
import org.greatfree.dip.multicast.message.ShutdownChildrenBroadcastNotification;

// Created: 03/23/2020, Bing Li
class ShutdownChildrenBroadcastNotificationThreadCreator implements NotificationThreadCreatable<ShutdownChildrenBroadcastNotification, ShutdownChildrenBroadcastNotificationThread>
{

	@Override
	public ShutdownChildrenBroadcastNotificationThread createNotificationThreadInstance(int taskSize)
	{
		return new ShutdownChildrenBroadcastNotificationThread(taskSize);
	}

}
