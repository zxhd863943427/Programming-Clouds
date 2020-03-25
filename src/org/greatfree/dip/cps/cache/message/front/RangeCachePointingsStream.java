package org.greatfree.dip.cps.cache.message.front;

import java.io.ObjectOutputStream;
import java.util.concurrent.locks.Lock;

import org.greatfree.client.OutMessageStream;

// Created: 07/24/2018, Bing Li
public class RangeCachePointingsStream extends OutMessageStream<RangeCachePointingsRequest>
{

	public RangeCachePointingsStream(ObjectOutputStream out, Lock lock, RangeCachePointingsRequest message)
	{
		super(out, lock, message);
	}

}
