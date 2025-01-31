package org.greatfree.multicast.root.abandoned;

import java.io.Serializable;

import org.greatfree.client.FreeClientPool;
import org.greatfree.message.RootBroadcastNotificationCreatable;
import org.greatfree.message.abandoned.OldMulticastMessage;

/*
 * The class provides the pool with the initial values to create a RootBroadcastNotifier. The sources that are needed to create an instance of RootMulticastor are enclosed in the class. That is required by the pool to create multicastors. 05/04/2017, Bing Li
 */

// Created: 05/04/2017, Bing Li
class RootBroadcastNotifierSource<Data extends Serializable, Notification extends OldMulticastMessage, NotificationCreator extends RootBroadcastNotificationCreatable<Notification, Data>> extends BaseBroadcastNotifierSource<Data, Notification, NotificationCreator>
{

	public RootBroadcastNotifierSource(FreeClientPool clientPool, int rootBranchCount, int treeBranchCount, NotificationCreator creator)
	{
		super(clientPool, rootBranchCount, treeBranchCount, creator);
	}

}
