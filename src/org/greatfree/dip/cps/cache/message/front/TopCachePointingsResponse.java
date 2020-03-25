package org.greatfree.dip.cps.cache.message.front;

import java.util.List;

import org.greatfree.dip.cps.cache.data.MyCachePointing;
import org.greatfree.dip.cps.cache.data.MyCacheTiming;
import org.greatfree.dip.cps.cache.message.TestCacheMessageType;
import org.greatfree.message.ServerMessage;

// Created: 07/24/2018, Bing Li
public class TopCachePointingsResponse extends ServerMessage
{
	private static final long serialVersionUID = 4057207381172567019L;
	
	private List<MyCachePointing> pointings;
	private List<MyCacheTiming> timings;

	public TopCachePointingsResponse(List<MyCachePointing> pointings)
	{
		super(TestCacheMessageType.TOP_CACHE_POINTINGS_RESPONSE);
		this.pointings = pointings;
	}

	public TopCachePointingsResponse(String key, List<MyCacheTiming> timings)
	{
		super(TestCacheMessageType.TOP_CACHE_POINTINGS_RESPONSE);
		this.timings = timings;
	}

	public List<MyCachePointing> getPointings()
	{
		return this.pointings;
	}

	public List<MyCacheTiming> getTimings()
	{
		return this.timings;
	}
}
