package org.greatfree.chat.message.cs.business;

import java.io.ObjectOutputStream;
import java.util.concurrent.locks.Lock;

import org.greatfree.client.OutMessageStream;

// Created: 12/04/2017, Bing Li
public class PostMerchandiseStream extends OutMessageStream<PostMerchandiseNotification>
{

	public PostMerchandiseStream(ObjectOutputStream out, Lock lock, PostMerchandiseNotification message)
	{
		super(out, lock, message);
	}

}
