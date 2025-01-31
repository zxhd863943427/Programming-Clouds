package org.greatfree.framework.threading.ct.master;

import java.io.IOException;
import java.util.Calendar;

import org.greatfree.concurrency.threading.message.TaskStateNotification;
import org.greatfree.concurrency.threading.message.ATMMessageType;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.framework.threading.TaskConfig;
import org.greatfree.framework.threading.ThreadInfo;
import org.greatfree.message.ServerMessage;
import org.greatfree.message.container.Notification;
import org.greatfree.message.container.Request;
import org.greatfree.server.container.ServerTask;
import org.greatfree.util.ServerStatus;

/*
 * Case A. 09/16/2019, Bing Li
 */

// Created: 09/16/2019, Bing Li
class MasterTaskA implements ServerTask
{
	@Override
	public void processNotification(Notification notification)
	{
		switch (notification.getApplicationID())
		{
			case ATMMessageType.TASK_STATE_NOTIFICATION:
				System.out.println("TASK_STATE_NOTIFICATION received @" + Calendar.getInstance().getTime());
				TaskStateNotification rst = (TaskStateNotification)notification;
				if (rst.getTaskKey().equals(TaskConfig.PRINT_TASK_KEY))
				{
					if (rst.isDone())
					{
						try
						{
//							Master.MASTER().wait(ThreadInfo.ASYNC().getThreadAKey());
							Master.MASTER().assignTask(ThreadInfo.ASYNC().getThreadAKey());
//							Thread.sleep(3000);
//							Thread.sleep(8000);
							if (!Master.MASTER().isAlive(ThreadInfo.ASYNC().getThreadAKey()))
							{
								System.out.println("MasterTask-processNotification(): The thread is NOT Alive");
								Master.MASTER().execute(ThreadInfo.ASYNC().getThreadAKey());
							}
							else
							{
								System.out.println("MasterTask-processNotification(): The thread is Alive");
//								Master.MASTER().signal(ThreadInfo.ASYNC().getThreadAKey());
							}
						}
						catch (IOException | InterruptedException | ClassNotFoundException | RemoteReadException e)
						{
							ServerStatus.FREE().printException(e);
						}
					}
				}
				break;
		}
	}

	@Override
	public ServerMessage processRequest(Request request)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
