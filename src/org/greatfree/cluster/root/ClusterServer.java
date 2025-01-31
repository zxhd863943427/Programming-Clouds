package org.greatfree.cluster.root;

import java.io.IOException;

import org.greatfree.cluster.RootTask;
import org.greatfree.cluster.root.container.RootServiceProvider;
import org.greatfree.exceptions.DistributedNodeFailedException;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.framework.cluster.original.cs.twonode.message.StopChatClusterNotification;
import org.greatfree.server.Peer.PeerBuilder;
import org.greatfree.util.Builder;

// Created:09/23/2018, Bing Li
// public class ServerOnCluster extends Peer<RootDispatcher>
public class ClusterServer
{
	public ClusterServer(ServerOnClusterBuilder builder) throws IOException
	{
		PeerBuilder<RootDispatcher> peerBuilder = new PeerBuilder<RootDispatcher>();
		
		peerBuilder.peerPort(builder.getPeerPort())
			.peerName(builder.getPeerName())
			.registryServerIP(builder.getRegistryServerIP())
			.registryServerPort(builder.getRegistryServerPort())
			.isRegistryNeeded(true)
			.listenerCount(builder.getListenerCount())
//			.serverThreadPoolSize(builder.getServerThreadPoolSize())
//			.serverThreadKeepAliveTime(builder.getServerThreadKeepAliveTime())
			.dispatcher(new RootDispatcher(builder.getServerThreadPoolSize(), builder.getServerThreadKeepAliveTime(), builder.getSchedulerPoolSize(), builder.getSchedulerKeepAliveTime()))
//			.dispatcher(new RootDispatcher(builder.getSchedulerPoolSize(), builder.getSchedulerKeepAliveTime()))
			.freeClientPoolSize(builder.getClientPoolSize())
			.readerClientSize(builder.getReaderClientSize())
			.syncEventerIdleCheckDelay(builder.getClientIdleCheckDelay())
			.syncEventerIdleCheckPeriod(builder.getClientIdleCheckPeriod())
			.syncEventerMaxIdleTime(builder.getClientMaxIdleTime())
			.asyncEventQueueSize(builder.getAsyncEventQueueSize())
			.asyncEventerSize(builder.getAsyncEventerSize())
			.asyncEventingWaitTime(builder.getAsyncEventingWaitTime())
			.asyncEventerWaitTime(builder.getAsyncEventerWaitTime())
			.asyncEventerWaitRound(builder.getAsyncEventerWaitRound())
			.asyncEventIdleCheckDelay(builder.getAsyncEventIdleCheckDelay())
			.asyncEventIdleCheckPeriod(builder.getAsyncEventIdleCheckPeriod())
//			.clientThreadPoolSize(builder.getClientPoolSize())
//			.clientThreadKeepAliveTime(builder.getClientThreadKeepAliveTime())
			.schedulerPoolSize(builder.getSchedulerPoolSize())
			.schedulerKeepAliveTime(builder.getSchedulerKeepAliveTime());

		ClusterRoot.CLUSTER().init(peerBuilder, builder.getRootBranchCount(), builder.getTreeBranchCount(), builder.getRequestWaitTime());
	}
	
	public static class ServerOnClusterBuilder implements Builder<ClusterServer>
	{
//		private PeerBuilder<RootDispatcher> builder;
		private String peerName;
		private int peerPort;
		private String registryServerIP;
		private int registryServerPort;
		private boolean isRegistryNeeded;
		private int listenerCount;
		// The size of the thread pool that manages the threads of the server. 05/11/2017, Bing Li
		private int serverThreadPoolSize;
		// The time to keep alive for threads of the server. 05/11/2017, Bing Li
		private long serverThreadKeepAliveTime;
		
//		private int dispatcherThreadPoolSize;
//		private long dispatcherThreadKeepAliveTime;

		private int clientPoolSize;
		private int readerClientSize;
		private long clientIdleCheckDelay;
		private long clientIdleCheckPeriod;
		private long clientMaxIdleTime;
		
		private int asyncEventQueueSize;
		private int asyncEventerSize;
		private long asyncEventingWaitTime;
		private long asyncEventerWaitTime;
		private int asyncEventerWaitRound;
		private long asyncEventIdleCheckDelay;
		private long asyncEventIdleCheckPeriod;
		
//		private int clientThreadPoolSize;
//		private long clientThreadKeepAliveTime;
		
		private int schedulerPoolSize;
		private long schedulerKeepAliveTime;

		private int rootBranchCount;
		private int treeBranchCount;
		private long requestWaitTime;
		
		public ServerOnClusterBuilder()
		{
		}

		/*
		public ServerOnClusterBuilder builder(PeerBuilder<RootDispatcher> peer)
		{
			this.builder = peer;
			return this;
		}
		*/
		
		public ServerOnClusterBuilder peerName(String peerName)
		{
			this.peerName = peerName;
			return this;
		}

		public ServerOnClusterBuilder peerPort(int peerPort)
		{
			this.peerPort = peerPort;
			return this;
		}
		
		public ServerOnClusterBuilder registryServerIP(String registryServerIP)
		{
			this.registryServerIP = registryServerIP;
			return this;
		}
		
		public ServerOnClusterBuilder registryServerPort(int registryServerPort)
		{
			this.registryServerPort = registryServerPort;
			return this;
		}
		
		public ServerOnClusterBuilder isRegistryNeeded(boolean isRegistryNeeded)
		{
			this.isRegistryNeeded = isRegistryNeeded;
			return this;
		}

		public ServerOnClusterBuilder listenerCount(int listenerCount)
		{
			this.listenerCount = listenerCount;
			return this;
		}

		public ServerOnClusterBuilder serverThreadPoolSize(int serverThreadPoolSize)
		{
			this.serverThreadPoolSize = serverThreadPoolSize;
			return this;
		}
		
		public ServerOnClusterBuilder serverThreadKeepAliveTime(long serverThreadKeepAliveTime)
		{
			this.serverThreadKeepAliveTime = serverThreadKeepAliveTime;
			return this;
		}

		/*
		public ServerOnClusterBuilder dispatcherThreadPoolSize(int dispatcherThreadPoolSize)
		{
			this.dispatcherThreadPoolSize = dispatcherThreadPoolSize;
			return this;
		}
		
		public ServerOnClusterBuilder dispatcherThreadKeepAliveTime(long dispatcherThreadKeepAliveTime)
		{
			this.dispatcherThreadKeepAliveTime = dispatcherThreadKeepAliveTime;
			return this;
		}
		*/

		public ServerOnClusterBuilder clientPoolSize(int clientPoolSize)
		{
			this.clientPoolSize = clientPoolSize;
			return this;
		}
		
		public ServerOnClusterBuilder readerClientSize(int readerClientSize)
		{
			this.readerClientSize = readerClientSize;
			return this;
		}
		
		public ServerOnClusterBuilder syncEventerIdleCheckDelay(long idleCheckDelay)
		{
			this.clientIdleCheckDelay = idleCheckDelay;
			return this;
		}
		
		public ServerOnClusterBuilder syncEventerIdleCheckPeriod(long idleCheckPeriod)
		{
			this.clientIdleCheckPeriod = idleCheckPeriod;
			return this;
		}

		public ServerOnClusterBuilder syncEventerMaxIdleTime(long maxIdleTime)
		{
			this.clientMaxIdleTime = maxIdleTime;
			return this;
		}

		public ServerOnClusterBuilder asyncEventQueueSize(int asyncEventQueueSize)
		{
			this.asyncEventQueueSize = asyncEventQueueSize;
			return this;
		}

		public ServerOnClusterBuilder asyncEventerSize(int asyncEventerSize)
		{
			this.asyncEventerSize = asyncEventerSize;
			return this;
		}

		public ServerOnClusterBuilder asyncEventingWaitTime(long asyncEventingWaitTime)
		{
			this.asyncEventingWaitTime = asyncEventingWaitTime;
			return this;
		}

		public ServerOnClusterBuilder asyncEventerWaitTime(long asyncEventerWaitTime)
		{
			this.asyncEventerWaitTime = asyncEventerWaitTime;
			return this;
		}

		public ServerOnClusterBuilder asyncEventerWaitRound(int asyncEventerWaitRound)
		{
			this.asyncEventerWaitRound = asyncEventerWaitRound;
			return this;
		}

		public ServerOnClusterBuilder asyncEventIdleCheckDelay(long asyncEventIdleCheckDelay)
		{
			this.asyncEventIdleCheckDelay = asyncEventIdleCheckDelay;
			return this;
		}

		public ServerOnClusterBuilder asyncEventIdleCheckPeriod(long asyncEventIdleCheckPeriod)
		{
			this.asyncEventIdleCheckPeriod = asyncEventIdleCheckPeriod;
			return this;
		}

		public ServerOnClusterBuilder schedulerPoolSize(int schedulerPoolSize)
		{
			this.schedulerPoolSize = schedulerPoolSize;
			return this;
		}

		public ServerOnClusterBuilder schedulerKeepAliveTime(long schedulerKeepAliveTime)
		{
			this.schedulerKeepAliveTime = schedulerKeepAliveTime;
			return this;
		}

		/*
		public ServerOnClusterBuilder clientThreadPoolSize(int threadPoolSize)
		{
			this.clientThreadPoolSize = threadPoolSize;
			return this;
		}
		
		public ServerOnClusterBuilder clientThreadKeepAliveTime(long threadKeepAliveTime)
		{
			this.clientThreadKeepAliveTime = threadKeepAliveTime;
			return this;
		}
		*/
		
		public ServerOnClusterBuilder rootBranchCount(int rootBranchCount)
		{
			this.rootBranchCount = rootBranchCount;
			return this;
		}
		
		public ServerOnClusterBuilder treeBranchCount(int treeBranchCount)
		{
			this.treeBranchCount = treeBranchCount;
			return this;
		}
		
		public ServerOnClusterBuilder requestWaitTime(long requestWaitTime)
		{
			this.requestWaitTime = requestWaitTime;
			return this;
		}

		@Override
		public ClusterServer build() throws IOException
		{
			return new ClusterServer(this);
		}
		
		/*
		public PeerBuilder<RootDispatcher> getBuilder()
		{
			return this.builder;
		}
		*/
		
		public String getPeerName()
		{
			return this.peerName;
		}
		
		public int getPeerPort()
		{
			return this.peerPort;
		}
		
		public String getRegistryServerIP()
		{
			return this.registryServerIP;
		}
		
		public int getRegistryServerPort()
		{
			return this.registryServerPort;
		}
		
		public boolean isRegistryNeeded()
		{
			return this.isRegistryNeeded;
		}
		
		public int getListenerCount()
		{
			return this.listenerCount;
		}

		public int getServerThreadPoolSize()
		{
			return this.serverThreadPoolSize;
		}
		
		public long getServerThreadKeepAliveTime()
		{
			return this.serverThreadKeepAliveTime;
		}

		/*
		public int getDispatcherThreadPoolSize()
		{
			return this.dispatcherThreadPoolSize;
		}
		
		public long getDispatcherThreadKeepAliveTime()
		{
			return this.dispatcherThreadKeepAliveTime;
		}
		*/
		
		public int getClientPoolSize()
		{
			return this.clientPoolSize;
		}
		
		public int getReaderClientSize()
		{
			return this.readerClientSize;
		}
		
		public long getClientIdleCheckDelay()
		{
			return this.clientIdleCheckDelay;
		}
		
		public long getClientIdleCheckPeriod()
		{
			return this.clientIdleCheckPeriod;
		}
		
		public long getClientMaxIdleTime()
		{
			return this.clientMaxIdleTime;
		}
		
		public int getAsyncEventQueueSize()
		{
			return this.asyncEventQueueSize;
		}
		
		public int getAsyncEventerSize()
		{
			return this.asyncEventerSize;
		}
		
		public long getAsyncEventingWaitTime()
		{
			return this.asyncEventingWaitTime;
		}
		
		public long getAsyncEventerWaitTime()
		{
			return this.asyncEventerWaitTime;
		}
		
		public int getAsyncEventerWaitRound()
		{
			return this.asyncEventerWaitRound;
		}
		
		public long getAsyncEventIdleCheckDelay()
		{
			return this.asyncEventIdleCheckDelay;
		}
		
		public long getAsyncEventIdleCheckPeriod()
		{
			return this.asyncEventIdleCheckPeriod;
		}

		/*
		public int getClientThreadPoolSize()
		{
			return this.clientThreadPoolSize;
		}
		
		public long getClientThreadKeepAliveTime()
		{
			return this.clientThreadKeepAliveTime;
		}
		*/

		public int getSchedulerPoolSize()
		{
			return this.schedulerPoolSize;
		}
		
		public long getSchedulerKeepAliveTime()
		{
			return this.schedulerKeepAliveTime;
		}

		public int getRootBranchCount()
		{
			return this.rootBranchCount;
		}
		
		public int getTreeBranchCount()
		{
			return this.treeBranchCount;
		}
		
		public long getRequestWaitTime()
		{
			return this.requestWaitTime;
		}
	}
	
	public boolean isChildrenEmpty()
	{
		return ClusterRoot.CLUSTER().getChildrenCount() <= 0;
	}
	
	public void stopCluster() throws IOException, DistributedNodeFailedException
	{
//		System.out.println("ServerOnCluster-stopCluster(): chidlren count = " + ClusterRoot.ROOT().getChildrenCount());
		if (!this.isChildrenEmpty())
		{
			ClusterRoot.CLUSTER().broadcastNotify(new StopChatClusterNotification());
		}
		else
		{
			System.out.println("No children join!");
		}
	}
	
	public void stop(long timeout) throws ClassNotFoundException, IOException, InterruptedException, RemoteReadException
	{
		ClusterRoot.CLUSTER().dispose(timeout);
	}

	public void start(RootTask task) throws IOException, ClassNotFoundException, RemoteReadException, DistributedNodeFailedException
	{
		RootServiceProvider.ROOT().init(task);
		ClusterRoot.CLUSTER().start();
	}
}

