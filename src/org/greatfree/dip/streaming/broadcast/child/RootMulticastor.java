package org.greatfree.dip.streaming.broadcast.child;

import java.io.IOException;
import java.util.Map;

import org.greatfree.client.FreeClientPool;
import org.greatfree.concurrency.ThreadPool;
import org.greatfree.exceptions.DistributedNodeFailedException;
import org.greatfree.message.multicast.MulticastMessage;
import org.greatfree.multicast.root.RootClient;
import org.greatfree.multicast.root.RootRendezvousPoint;
import org.greatfree.util.IPAddress;
import org.greatfree.util.UtilConfig;

// Created: 03/21/2020, Bing Li
public class RootMulticastor
{
	private RootClient client;

	private RootMulticastor()
	{
	}
	
	private static RootMulticastor instance = new RootMulticastor();
	
	public static RootMulticastor CHILD_STREAM()
	{
		if (instance == null)
		{
			instance = new RootMulticastor();
			return instance;
		}
		else
		{
			return instance;
		}
	}
	
	public void stop() throws IOException, InterruptedException
	{
		this.client.close();
	}

	public void start(FreeClientPool clientPool, int rootBranchCount, int treeBranchCount, long waitTime, ThreadPool pool)
	{
		this.client = new RootClient(clientPool, rootBranchCount, treeBranchCount, waitTime, pool);
	}

	public RootRendezvousPoint getRP()
	{
		return this.client.getRP();
	}
	
	public boolean resetChildren(Map<String, IPAddress> ips)
	{
		if (ips != UtilConfig.NO_IPS)
		{
			this.client.clearChildren();
			for (IPAddress entry : ips.values())
			{
				System.out.println("RootMulticastor@Child: resetChildren(): ip = " + entry);
				this.client.addChild(entry.getIP(), entry.getPort());
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	public void broadcastNotify(MulticastMessage notification) throws InstantiationException, IllegalAccessException, IOException, InterruptedException, DistributedNodeFailedException
	{
		this.client.broadcastNotify(notification);
	}
}
