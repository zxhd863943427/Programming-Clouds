package org.greatfree.framework.cps.threetier.message;

import java.io.ObjectOutputStream;
import java.util.concurrent.locks.Lock;

import org.greatfree.client.MessageStream;

// Created: 07/06/2018, Bing Li
public class FrontStream extends MessageStream<FrontRequest>
{

	public FrontStream(ObjectOutputStream out, Lock lock, FrontRequest message)
	{
		super(out, lock, message);
	}

}
