package org.greatfree.multicast.root.abandoned;

import java.io.Serializable;

import org.greatfree.message.RootBroadcastNotificationCreatable;
import org.greatfree.message.abandoned.OldMulticastMessage;
import org.greatfree.reuse.HashDisposable;

/*
 * The disposer collects the instance of RootUnicastNotifier. 05/04/2017, Bing Li
 */

// Created: 05/04/2017, Bing Li
class RootUnicastNotifierDisposer<Data extends Serializable, Notification extends OldMulticastMessage, NotificationCreator extends RootBroadcastNotificationCreatable<Notification, Data>> implements HashDisposable<RootUnicastNotifier<Data, Notification, NotificationCreator>>
{

	@Override
	public void dispose(RootUnicastNotifier<Data, Notification, NotificationCreator> t)
	{
		t.dispose();
	}

}
