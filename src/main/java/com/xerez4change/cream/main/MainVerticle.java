package com.xerez4change.cream.main;

import com.xerez4change.cream.db.hardware.GroupDB;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;

public class MainVerticle extends AbstractVerticle {

	MySQLPool mySqlClient;

	@Override
	public void start(Promise<Void> startFuture) {
		MySQLConnectOptions connectOptions = new MySQLConnectOptions().setPort(3307).setHost("localhost")
				.setDatabase("x4c").setUser("root").setPassword("Mequierotirarunpedo1.");

		PoolOptions poolOptions = new PoolOptions().setMaxSize(5);

		mySqlClient = MySQLPool.pool(vertx, connectOptions, poolOptions);

		//GroupDB.getAllGroups(mySqlClient);

		//GroupDB.getAllGroupsWithConnection(mySqlClient);

		//GroupDB.getGroupsByName(mySqlClient, "Avenida Reina Mercedes S/N");	
		
		//GroupDB.putGroupByName(mySqlClient, 1, "Avenida Reina Mercedes S/N");
		
		GroupDB.postOneGroup(mySqlClient, 2, "Calle Palomar Nº23");
		
		//GroupDB.deleteGroupById(mySqlClient, 2);
		
		//GroupDB.getAllGroups(mySqlClient);

	}
	
	@Override
	public void stop(Promise<Void> stopFuture) throws Exception {
		getVertx().undeploy(VerticleReciever.class.getName());
		getVertx().undeploy(VerticleSender.class.getName());
		getVertx().undeploy(VerticleBlockingCorrect.class.getName());
		super.stop(stopFuture);
	}

}

