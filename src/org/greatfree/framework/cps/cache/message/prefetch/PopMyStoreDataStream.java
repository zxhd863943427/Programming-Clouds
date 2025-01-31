package org.greatfree.framework.cps.cache.message.prefetch;

import java.io.ObjectOutputStream;
import java.util.concurrent.locks.Lock;

import org.greatfree.client.MessageStream;

// Created: 08/09/2018, Bing Li
public class PopMyStoreDataStream extends MessageStream<PopMyStoreDataRequest>
{

	public PopMyStoreDataStream(ObjectOutputStream out, Lock lock, PopMyStoreDataRequest message)
	{
		super(out, lock, message);
	}

}
