package com.xerez4change.cream;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

public class MainVerticle extends AbstractVerticle {

	MySQLPool mySqlClient;

	@Override
	public void start(Promise<Void> startFuture) {
		MySQLConnectOptions connectOptions = new MySQLConnectOptions().setPort(3307).setHost("localhost")
				.setDatabase("x4c").setUser("root").setPassword("Mequierotirarunpedo1.");

		PoolOptions poolOptions = new PoolOptions().setMaxSize(5);

		mySqlClient = MySQLPool.pool(vertx, connectOptions, poolOptions);

		getAllGroups();

		getAllWithConnection();

		for (int i = 0; i < 100; i++) {
			getByName("Calle Palomar Nº23");	
		}

	}

	private void getAllGroups() {
		mySqlClient.query("SELECT * FROM x4c.group;", res -> {
			if (res.succeeded()) {
				RowSet<Row> resultSet = res.result();
				System.out.println(resultSet.size());
				JsonArray result = new JsonArray();
				for (Row elem : resultSet) {
					result.add(JsonObject.mapFrom(new GroupEntity(elem.getInteger("idGroup"), elem.getString("name"))));
				}
				System.out.println(result.toString());
			} else {
				System.out.println("Error: " + res.cause().getLocalizedMessage());
			}
		});
	}

	private void getAllWithConnection() {
		mySqlClient.getConnection(connection -> {
			if (connection.succeeded()) {
				connection.result().query("SELECT * FROM x4c.group;", res -> {
					if (res.succeeded()) {
						// Get the result set
						RowSet<Row> resultSet = res.result();
						System.out.println(resultSet.size());
						JsonArray result = new JsonArray();
						for (Row elem : resultSet) {
							result.add(JsonObject
									.mapFrom(new GroupEntity(elem.getInteger("idGroup"), elem.getString("name"))));
						}
						System.out.println(result.toString());
					} else {
						System.out.println("Error: " + res.cause().getLocalizedMessage());
					}
					connection.result().close();
				});
			} else {
				System.out.println(connection.cause().toString());
			}
		});
	}

	private void getByName(String username) {
		mySqlClient.getConnection(connection -> {
			if (connection.succeeded()) {
				connection.result().preparedQuery("SELECT * FROM x4c.group WHERE name = ?",
						Tuple.of(username), res -> {
							if (res.succeeded()) {
								// Get the result set
								RowSet<Row> resultSet = res.result();
								System.out.println(resultSet.size());
								JsonArray result = new JsonArray();
								for (Row elem : resultSet) {
									result.add(JsonObject.mapFrom(new GroupEntity(elem.getInteger("idGroup"),
											elem.getString("name"))));
								}
								System.out.println(result.toString());
							} else {
								System.out.println("Error: " + res.cause().getLocalizedMessage());
							}
							connection.result().close();
						});
			} else {
				System.out.println(connection.cause().toString());
			}
		});
	}
	
	@Override
	public void stop(Promise<Void> stopFuture) throws Exception {
		getVertx().undeploy(VerticleReciever.class.getName());
		getVertx().undeploy(VerticleSender.class.getName());
		getVertx().undeploy(VerticleBlockingCorrect.class.getName());
		super.stop(stopFuture);
	}

}

