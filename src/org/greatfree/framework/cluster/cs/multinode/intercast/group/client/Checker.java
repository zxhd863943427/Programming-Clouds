package org.greatfree.framework.cluster.cs.multinode.intercast.group.client;

import java.io.IOException;

import org.greatfree.exceptions.RemoteReadException;

// Created: 04/06/2019, Bing Li
class Checker implements Runnable
{

	@Override
	public void run()
	{
		try
		{
			ChatMaintainer.GROUP().checkNewChats();
		}
		catch (ClassNotFoundException | RemoteReadException | IOException e)
		{
			e.printStackTrace();
		}
	}

}
