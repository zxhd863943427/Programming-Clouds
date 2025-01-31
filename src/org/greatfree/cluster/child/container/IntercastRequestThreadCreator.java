package org.greatfree.cluster.child.container;

import org.greatfree.concurrency.reactive.RequestQueueCreator;
import org.greatfree.message.multicast.container.IntercastRequest;
import org.greatfree.message.multicast.container.IntercastRequestStream;
import org.greatfree.message.multicast.container.CollectedClusterResponse;

// Created: 01/26/2019, Bing Li
// class IntercastRequestThreadCreator implements RequestThreadCreatable<IntercastRequest, IntercastRequestStream, ServerMessage, IntercastRequestThread>
class IntercastRequestThreadCreator implements RequestQueueCreator<IntercastRequest, IntercastRequestStream, CollectedClusterResponse, IntercastRequestThread>
{

	@Override
	public IntercastRequestThread createInstance(int taskSize)
	{
		return new IntercastRequestThread(taskSize);
	}

}


