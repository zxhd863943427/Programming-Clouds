package org.greatfree.dip.multicast.root;

import org.greatfree.concurrency.reactive.RequestThreadCreatable;
import org.greatfree.dip.multicast.message.ClientHelloWorldBroadcastRequest;
import org.greatfree.dip.multicast.message.ClientHelloWorldBroadcastResponse;
import org.greatfree.dip.multicast.message.ClientHelloWorldBroadcastStream;

// Created: 08/26/2018, Bing Li
class ClientHelloWorldBroadcastRequestThreadCreator implements RequestThreadCreatable<ClientHelloWorldBroadcastRequest, ClientHelloWorldBroadcastStream, ClientHelloWorldBroadcastResponse, ClientHelloWorldBroadcastRequestThread>
{

	@Override
	public ClientHelloWorldBroadcastRequestThread createRequestThreadInstance(int taskSize)
	{
		return new ClientHelloWorldBroadcastRequestThread(taskSize);
	}

}
