package org.greatfree.framework.cluster.cs.multinode.wurb.clusterserver.child;

import java.io.IOException;
import java.util.Calendar;

import org.greatfree.cluster.ChildTask;
import org.greatfree.cluster.message.ClusterApplicationID;
import org.greatfree.data.ServerConfig;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.framework.cluster.cs.multinode.wurb.message.AddPartnerNotification;
import org.greatfree.framework.cluster.cs.multinode.wurb.message.ChatApplicationID;
import org.greatfree.framework.cluster.cs.multinode.wurb.message.ChatNotification;
import org.greatfree.framework.cluster.cs.multinode.wurb.message.ChatPartnerRequest;
import org.greatfree.framework.cluster.cs.multinode.wurb.message.ChatPartnerResponse;
import org.greatfree.framework.cluster.cs.multinode.wurb.message.ChatRegistryRequest;
import org.greatfree.framework.cluster.cs.multinode.wurb.message.ChatRegistryResponse;
import org.greatfree.framework.cluster.cs.multinode.wurb.message.PollNewChatsRequest;
import org.greatfree.framework.cluster.cs.multinode.wurb.message.PollNewChatsResponse;
import org.greatfree.framework.cluster.cs.multinode.wurb.message.PollNewSessionsRequest;
import org.greatfree.framework.cluster.cs.multinode.wurb.message.PollNewSessionsResponse;
import org.greatfree.framework.cs.multinode.server.CSAccount;
import org.greatfree.framework.cs.multinode.server.PrivateChatSessions;
import org.greatfree.framework.cs.twonode.server.AccountRegistry;
import org.greatfree.message.multicast.MulticastResponse;
import org.greatfree.message.multicast.container.InterChildrenNotification;
import org.greatfree.message.multicast.container.InterChildrenRequest;
import org.greatfree.message.multicast.container.IntercastNotification;
import org.greatfree.message.multicast.container.IntercastRequest;
import org.greatfree.message.multicast.container.ClusterNotification;
import org.greatfree.message.multicast.container.ClusterRequest;
import org.greatfree.message.multicast.container.CollectedClusterResponse;
import org.greatfree.util.UtilConfig;

// Created: 01/28/2019, Bing Li
class ChatTask implements ChildTask
{

	@Override
	public void processNotification(ClusterNotification notification)
	{
		switch (notification.getApplicationID())
		{
			case ChatApplicationID.ADD_PARTNER_NOTIFICATION:
				System.out.println("ADD_PARTNER_NOTIFICATION received @" + Calendar.getInstance().getTime());
				AddPartnerNotification apn = (AddPartnerNotification)notification;
				PrivateChatSessions.HUNGARY().addSession(apn.getPartnerKey(), apn.getLocalUserKey());
				break;

			case ChatApplicationID.CHAT_NOTIFICATION:
				System.out.println("CHAT_NOTIFICATION received @" + Calendar.getInstance().getTime());
				ChatNotification cn = (ChatNotification)notification;
//				System.out.println("ChatTask-processNotification(): " + cn.getSessionKey() + ", " + cn.getSenderKey() + ", " + cn.getReceiverKey() + ", " + cn.getMessage());
				PrivateChatSessions.HUNGARY().addMessage(cn.getSessionKey(), cn.getSenderKey(), cn.getReceiverKey(), cn.getMessage());
				break;
				
			case ClusterApplicationID.STOP_CHAT_CLUSTER_NOTIFICATION:
				System.out.println("STOP_CHAT_CLUSTER_NOTIFICATION received @" + Calendar.getInstance().getTime());
				try
				{
					ChatChild.CLUSTER_CONTAINER().stop(ServerConfig.SERVER_SHUTDOWN_TIMEOUT);
				}
				catch (ClassNotFoundException | IOException | InterruptedException | RemoteReadException e)
				{
					e.printStackTrace();
				}
				break;

			case ClusterApplicationID.STOP_ONE_CHILD_ON_CLUSTER_NOTIFICATION:
				System.out.println("STOP_ONE_CHILD_ON_CLUSTER_NOTIFICATION received @" + Calendar.getInstance().getTime());
				try
				{
					ChatChild.CLUSTER_CONTAINER().stop(ServerConfig.SERVER_SHUTDOWN_TIMEOUT);
				}
				catch (ClassNotFoundException | IOException | InterruptedException | RemoteReadException e)
				{
					e.printStackTrace();
				}
				break;
		}
	}

	@Override
	public MulticastResponse processRequest(ClusterRequest request)
	{
		switch (request.getApplicationID())
		{
			case ChatApplicationID.CHAT_REGISTRY_REQUEST:
				System.out.println("CHAT_REGISTRY_REQUEST received @" + Calendar.getInstance().getTime());
				ChatRegistryRequest crr = (ChatRegistryRequest)request;
				AccountRegistry.CS().add(new CSAccount(crr.getUserKey(), crr.getUserName(), crr.getDescription()));
				return new ChatRegistryResponse(request.getCollaboratorKey(), true);

			case ChatApplicationID.CHAT_PARTNER_REQUEST:
				System.out.println("CHAT_PARTNER_REQUEST received @" + Calendar.getInstance().getTime());
				ChatPartnerRequest cpr = (ChatPartnerRequest)request;
				if (AccountRegistry.CS().isAccountExisted(cpr.getUserKey()))
				{
					CSAccount account = AccountRegistry.CS().getAccount(cpr.getUserKey());
					return new ChatPartnerResponse(account.getUserKey(), account.getUserName(), account.getDescription(), request.getCollaboratorKey());
				}
				else
				{
					return new ChatPartnerResponse(UtilConfig.EMPTY_STRING, UtilConfig.EMPTY_STRING, UtilConfig.EMPTY_STRING, request.getCollaboratorKey());
				}

			case ChatApplicationID.POLL_NEW_SESSIONS_REQUEST:
				System.out.println("POLL_NEW_SESSIONS_REQUEST received @" + Calendar.getInstance().getTime());
				PollNewSessionsRequest pnsr = (PollNewSessionsRequest)request;
				if (PrivateChatSessions.HUNGARY().isSessionExisted(pnsr.getReceiverKey()))
				{
					PollNewSessionsResponse response = new PollNewSessionsResponse(PrivateChatSessions.HUNGARY().getSessionKeys(pnsr.getReceiverKey()), pnsr.getCollaboratorKey());
					PrivateChatSessions.HUNGARY().removeSession(pnsr.getReceiverKey());
					return response;
				}
				else
				{
					return new PollNewSessionsResponse(null, pnsr.getCollaboratorKey());
				}
				
			case ChatApplicationID.POLL_NEW_CHATS_REQUEST:
				System.out.println("POLL_NEW_CHATS_REQUEST received @" + Calendar.getInstance().getTime());
				PollNewChatsRequest pncr = (PollNewChatsRequest)request;
				System.out.println("ChatTask-processRequest(): collaboratorKey = " + pncr.getCollaboratorKey());
				return new PollNewChatsResponse(PrivateChatSessions.HUNGARY().getNewMessages(pncr.getChatSessionKey(), pncr.getReceiverKey()), pncr.getCollaboratorKey());
		}
		return null;
	}

	@Override
	public InterChildrenNotification prepareNotification(IntercastNotification notification)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	@Override
	public void processDestinationNotification(InterChildrenNotification notification)
	{
		// TODO Auto-generated method stub
		
	}
	*/

	@Override
	public InterChildrenRequest prepareRequest(IntercastRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MulticastResponse processRequest(InterChildrenRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processResponse(CollectedClusterResponse response)
	{
		// TODO Auto-generated method stub
		
	}

}
