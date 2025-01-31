package org.greatfree.framework.cps.cache.terminal.man;

import org.greatfree.chat.message.ShutdownServerNotification;
import org.greatfree.concurrency.reactive.NotificationQueueCreator;

// Created: 07/07/2018, Bing Li
public class ShutdownTerminalServerThreadCreator implements NotificationQueueCreator<ShutdownServerNotification, ShutdownTerminalServerThread>
{

	@Override
	public ShutdownTerminalServerThread createInstance(int taskSize)
	{
		return new ShutdownTerminalServerThread(taskSize);
	}

}
