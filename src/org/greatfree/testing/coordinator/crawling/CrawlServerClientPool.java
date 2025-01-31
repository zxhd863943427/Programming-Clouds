package org.greatfree.testing.coordinator.crawling;

import java.io.IOException;

import org.greatfree.client.FreeClientPool;
import org.greatfree.testing.coordinator.CoorConfig;

/*
 * This is a pool that creates and manages instances of FreeClient to achieve the goal of using minimum instances of clients to reach a high performance. 11/24/2014, Bing Li
 */

// Created: 11/24/2014, Bing Li
public class CrawlServerClientPool
{
	// An instance of FreeClientPool is defined to interact with the crawler. 11/23/2014, Bing Li
	private FreeClientPool clientPool;
	
	private CrawlServerClientPool()
	{
	}
	
	/*
	 * A singleton definition. 11/24/2014, Bing Li
	 */
	private static CrawlServerClientPool instance = new CrawlServerClientPool();
	
	public static CrawlServerClientPool COORDINATE()
	{
		if (instance == null)
		{
			instance = new CrawlServerClientPool();
			return instance;
		}
		else
		{
			return instance;
		}
	}
	
	/*
	 * Dispose the free client pool. 11/24/2014, Bing Li
	 */
	public void dispose()
	{
		try
		{
			this.clientPool.dispose();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Initialize the client pool. The method is called when the coordinator process is started. 11/24/2014, Bing Li
	 */
	public void init()
	{
		// Initialize the client pool. 11/24/2014, Bing Li
		this.clientPool = new FreeClientPool(CoorConfig.CSERVER_CLIENT_POOL_SIZE);
		// Set idle checking for the client pool. 11/24/2014, Bing Li
		this.clientPool.setIdleChecker(CoorConfig.CSERVER_CLIENT_IDLE_CHECK_DELAY, CoorConfig.CSERVER_CLIENT_IDLE_CHECK_PERIOD, CoorConfig.CSERVER_CLIENT_MAX_IDLE_TIME);
	}
	
	/*
	 * Expose the client pool. 11/24/2014, Bing Li
	 */
	public FreeClientPool getPool()
	{
		return this.clientPool;
	}
}
