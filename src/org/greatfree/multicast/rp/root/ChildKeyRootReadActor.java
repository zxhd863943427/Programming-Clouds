package org.greatfree.multicast.rp.root;

import java.io.IOException;

import org.greatfree.concurrency.Async;
import org.greatfree.exceptions.DistributedNodeFailedException;

// Created: 10/20/2018, Bing Li
class ChildKeyRootReadActor extends Async<ChildKeyMulticastRequest>
{
	private RootSyncMulticastor multicastor;
	
	public ChildKeyRootReadActor(RootSyncMulticastor multicastor)
	{
		this.multicastor = multicastor;
	}

	@Override
	public void perform(ChildKeyMulticastRequest message)
	{
		try
		{
			this.multicastor.read(message.getChildKey(), message.getRequest());
		}
		catch (IOException | DistributedNodeFailedException e)
		{
			e.printStackTrace();
		}
	}

}
