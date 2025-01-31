package org.greatfree.framework.multicast.child;

import java.io.IOException;

import org.greatfree.data.ServerConfig;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.TerminateSignal;

// Created: 09/08/2018, Bing Li
class StartChild
{

	public static void main(String[] args)
	{
		System.out.println("Multicast child starting up ...");

		// Start up the cluster root. 06/11/2017, Bing Li
		try
		{
			ChildPeer.CHILD().start();
		}
		catch (ClassNotFoundException | IOException | RemoteReadException e)
		{
			e.printStackTrace();
		}

		System.out.println("Multicast child started ...");
		
		while (!TerminateSignal.SIGNAL().isTerminated())
		{
			try
			{
				// If the terminating flag is false, it is required to sleep for some time. Otherwise, it might cause the high CPU usage. 08/22/2014, Bing Li
				Thread.sleep(ServerConfig.TERMINATE_SLEEP);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}
