package org.greatfree.cluster.root.container;

import org.greatfree.concurrency.reactive.RequestQueueCreator;
import org.greatfree.message.multicast.container.IntercastRequest;
import org.greatfree.message.multicast.container.IntercastRequestStream;
import org.greatfree.message.multicast.container.CollectedClusterResponse;

// Created: 03/04/2019, Bing Li
class IntercastRequestThreadCreator implements RequestQueueCreator<IntercastRequest, IntercastRequestStream, CollectedClusterResponse, IntercastRequestThread>
{

	@Override
	public IntercastRequestThread createInstance(int taskSize)
	{
		return new IntercastRequestThread(taskSize);
	}

}

