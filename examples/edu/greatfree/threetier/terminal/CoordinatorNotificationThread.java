package edu.greatfree.threetier.terminal;

import org.greatfree.concurrency.reactive.NotificationQueue;
import org.greatfree.data.ServerConfig;

import edu.greatfree.threetier.message.CoordinatorNotification;

// Created: 07/07/2018, Bing Li
class CoordinatorNotificationThread extends NotificationQueue<CoordinatorNotification>
{

	public CoordinatorNotificationThread(int taskSize)
	{
		super(taskSize);
	}

	@Override
	public void run()
	{
		CoordinatorNotification notification;
		while (!this.isShutdown())
		{
			while (!this.isEmpty())
			{
				try
				{
					notification = this.getNotification();
					System.out.println("The coordinator notification: " + notification.getNotification());
					this.disposeMessage(notification);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			try
			{
				this.holdOn(ServerConfig.NOTIFICATION_THREAD_WAIT_TIME);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
