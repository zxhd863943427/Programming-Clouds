package org.greatfree.dip.multicast.bound.child;

import org.greatfree.concurrency.reactive.BoundNotificationQueue;
import org.greatfree.data.ServerConfig;
import org.greatfree.dip.multicast.message.MessageDisposer;
import org.greatfree.dip.multicast.message.OldHelloWorldBroadcastNotification;

// Created: 08/26/2018, Bing Li
public class BroadcastHelloWorldNotificationThread extends BoundNotificationQueue<OldHelloWorldBroadcastNotification, MessageDisposer<OldHelloWorldBroadcastNotification>>
{

	public BroadcastHelloWorldNotificationThread(int taskSize, String dispatcherKey, MessageDisposer<OldHelloWorldBroadcastNotification> binder)
	{
		super(taskSize, dispatcherKey, binder);
	}

	@Override
	public void run()
	{
		// Declare a notification instance of HelloWorldNotification. 11/27/2014, Bing Li
		OldHelloWorldBroadcastNotification notification;
		// The thread always runs until it is shutdown by the BoundNotificationDispatcher. 11/27/2014, Bing Li
		while (!this.isShutdown())
		{
			// Check whether the notification queue is empty. 11/27/2014, Bing Li
			while (!this.isEmpty())
			{
				try
				{
					// Dequeue the notification. 11/27/2014, Bing Li
					notification = this.getNotification();

					/*
					 * All the code in the class is out of date. Since the message is upgraded, the code here is abandoned. 12/15/2018, Bing Li
					 */
					// Disseminate the notification of HelloWorldNotification among children. 11/27/2014, Bing Li
//					ChildMulticastor.CHILD().notify(notification);
					// Notify the binder that the notification is consumed by the thread. 11/27/2014, Bing Li
					this.bind(super.getDispatcherKey(), notification);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			try
			{
				// Wait for a moment after all of the existing notifications are processed. 11/26/2014, Bing Li
				this.holdOn(ServerConfig.NOTIFICATION_THREAD_WAIT_TIME);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}
