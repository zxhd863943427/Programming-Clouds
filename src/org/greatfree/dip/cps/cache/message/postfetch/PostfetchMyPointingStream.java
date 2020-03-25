package org.greatfree.dip.cps.cache.message.postfetch;

import java.io.ObjectOutputStream;
import java.util.concurrent.locks.Lock;

import org.greatfree.client.OutMessageStream;

// Created: 07/12/2018, Bing Li
public class PostfetchMyPointingStream extends OutMessageStream<PostfetchMyPointingByKeyRequest>
{

	public PostfetchMyPointingStream(ObjectOutputStream out, Lock lock, PostfetchMyPointingByKeyRequest message)
	{
		super(out, lock, message);
	}

}
