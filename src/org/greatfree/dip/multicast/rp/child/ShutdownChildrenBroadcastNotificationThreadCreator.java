package org.greatfree.dip.multicast.rp.child;

import org.greatfree.concurrency.reactive.NotificationThreadCreatable;
import org.greatfree.dip.multicast.message.ShutdownChildrenBroadcastNotification;

// Created: 10/22/2018, Bing Li
public class ShutdownChildrenBroadcastNotificationThreadCreator implements NotificationThreadCreatable<ShutdownChildrenBroadcastNotification, ShutdownChildrenBroadcastNotificationThread>
{

	@Override
	public ShutdownChildrenBroadcastNotificationThread createNotificationThreadInstance(int taskSize)
	{
		return new ShutdownChildrenBroadcastNotificationThread(taskSize);
	}

}
