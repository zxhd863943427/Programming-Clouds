package org.greatfree.app.container.cs.multinode.library.server;

import java.io.IOException;

import org.greatfree.chat.ChatConfig;
import org.greatfree.data.ServerConfig;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.TerminateSignal;

// Created: 12/19/2018, Bing Li
public class StartServer
{

	public static void main(String[] args)
	{
		System.out.println("Library server starting up ...");

		try
		{
			Library.CS().start(ChatConfig.CHAT_SERVER_PORT, new LibraryTask());
//			Library.CS().start(ChatConfig.CHAT_SERVER_PORT, new LibraryTask(), "/home/libing/Clouds/Config/ServerContainer.xml");
//			Library.CS().start(new LibraryTask(), "/home/libing/Clouds/Config/ServerContainer.xml");
		}
		catch (ClassNotFoundException | IOException | RemoteReadException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Library server started ...");

		// After the server is started, the loop check whether the flag of terminating is set. If the terminating flag is true, the process is ended. Otherwise, the process keeps running. 08/22/2014, Bing Li
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
