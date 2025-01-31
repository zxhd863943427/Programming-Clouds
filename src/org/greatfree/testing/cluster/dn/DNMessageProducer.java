package org.greatfree.testing.cluster.dn;

import org.greatfree.client.MessageStream;
import org.greatfree.concurrency.Runner;
import org.greatfree.data.ServerConfig;
import org.greatfree.message.ServerMessage;
import org.greatfree.server.abandoned.MessageProducer;

/*
 * The class is a singleton to enclose the instances of MessageProducer. Each of the enclosed message producers serves for one particular client that connects to a respective port on the DN server. Usually, each port aims to provide one particular service. 11/23/2014, Bing Li
 * 
 * The class is a wrapper that encloses all of the asynchronous message producers. It is responsible for assigning received messages to the corresponding producer in an asynchronous way. 11/23/2014, Bing Li
 */

// Created: 11/22/2016, Bing Li
public class DNMessageProducer
{
//	private Runner<MessageProducer<DNDispatcher>, DNMessageProducerDisposer> producerThreader;
	private Runner<MessageProducer<DNDispatcher>> producerThreader;

	private DNMessageProducer()
	{
	}
	
	/*
	 * The class is required to be a singleton since it is nonsense to initiate it for the producers are unique. 11/23/2014, Bing Li
	 */
	private static DNMessageProducer instance = new DNMessageProducer();
	
	public static DNMessageProducer CLUSTER()
	{
		if (instance == null)
		{
			instance = new DNMessageProducer();
			return instance;
		}
		else
		{
			return instance;
		}
	}

	/*
	 * Dispose the producers when the process of the server is shutdown. 11/23/2014, Bing Li
	 */
	public void dispose() throws InterruptedException
	{
		this.producerThreader.stop();
	}

	/*
	 * Initialize the message producers. It is invoked when the connection modules of the server is started since clients can send requests or notifications only after it is started. 08/22/2014, Bing Li
	 */
	public void init()
	{
		// Initialize the crawling message producer. A threader is associated with the message producer such that the producer is able to work in a concurrent way. 11/23/2014, Bing Li
//		this.producerThreader = new Runner<MessageProducer<DNDispatcher>, DNMessageProducerDisposer>(new MessageProducer<DNDispatcher>(new DNDispatcher(ServerConfig.DISPATCHER_POOL_SIZE, ServerConfig.DISPATCHER_POOL_THREAD_POOL_ALIVE_TIME, ServerConfig.SCHEDULER_POOL_SIZE, ServerConfig.SCHEDULER_KEEP_ALIVE_TIME)), new DNMessageProducerDisposer());
		this.producerThreader = new Runner<MessageProducer<DNDispatcher>>(new MessageProducer<DNDispatcher>(new DNDispatcher(ServerConfig.DISPATCHER_POOL_SIZE, ServerConfig.DISPATCHER_POOL_THREAD_POOL_ALIVE_TIME, ServerConfig.SCHEDULER_POOL_SIZE, ServerConfig.SCHEDULER_KEEP_ALIVE_TIME)));
		// Start the associated thread for the crawling message producer. 11/23/2014, Bing Li
		this.producerThreader.start();
	}

	/*
	 * Assign messages, requests or notifications, to the bound message dispatcher such that they can be responded or dealt with. 11/23/2014, Bing Li
	 */
	public void produceMessage(MessageStream<ServerMessage> message)
	{
		this.producerThreader.getFunction().produce(message);
	}
}
