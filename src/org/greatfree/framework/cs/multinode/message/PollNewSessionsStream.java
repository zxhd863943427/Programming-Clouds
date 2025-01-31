package org.greatfree.framework.cs.multinode.message;

import java.io.ObjectOutputStream;
import java.util.concurrent.locks.Lock;

import org.greatfree.client.MessageStream;

// Created: 04/24/2017, Bing Li
public class PollNewSessionsStream extends MessageStream<PollNewSessionsRequest>
{

	public PollNewSessionsStream(ObjectOutputStream out, Lock lock, PollNewSessionsRequest message)
	{
		super(out, lock, message);
	}

}
