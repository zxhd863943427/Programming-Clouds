package org.greatfree.framework.cps.cache.coordinator.prefetching;

import java.io.IOException;

import org.greatfree.concurrency.reactive.NotificationObjectQueue;
import org.greatfree.data.ServerConfig;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.framework.cps.cache.coordinator.MyDistributedList;
import org.greatfree.framework.cps.cache.message.postfetch.FetchMyUKValueNotification;

// Created: 02/25/2019, Bing Li
public class PrefetchMyUKValueThread extends NotificationObjectQueue<FetchMyUKValueNotification>
{

	public PrefetchMyUKValueThread(int taskSize)
	{
		super(taskSize);
	}

	@Override
	public void run()
	{
		FetchMyUKValueNotification notification;
		while (!this.isShutdown())
		{
			while (!this.isEmpty())
			{
				try
				{
					notification = this.getNotification();
					MyDistributedList.MIDDLE().prefetch(notification);
					this.disposeObject(notification);
				}
				catch (InterruptedException | ClassNotFoundException | RemoteReadException | IOException e)
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
