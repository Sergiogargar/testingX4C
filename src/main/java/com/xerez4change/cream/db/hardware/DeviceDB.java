package com.xerez4change.cream.db.hardware;

import com.xerez4change.cream.entities.DeviceEntity;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

public class DeviceDB {
	
	public static void getAllDevices(MySQLPool mySqlClient) {
		mySqlClient.query("SELECT * FROM x4c.device;", res -> {
			if (res.succeeded()) {
				RowSet<Row> resultSet = res.result();
				System.out.println(resultSet.size());
				JsonArray result = new JsonArray();
				for (Row elem : resultSet) {
					result.add(JsonObject.mapFrom(new DeviceEntity(elem.getInteger("idDevice"), elem.getInteger("Group_idGroup"), elem.getString("name"), elem.getInteger("type"))));
				}
				System.out.println(result.toString());
			} else {
				System.out.println("Error: " + res.cause().getLocalizedMessage());
			}
		});
	}
	
	public static void getAllDevicesWithConnection(MySQLPool mySqlClient) {
		mySqlClient.getConnection(connection -> {
			if (connection.succeeded()) {
				connection.result().query("SELECT * FROM x4c.device;", res -> {
					if (res.succeeded()) {
						// Get the result set
						RowSet<Row> resultSet = res.result();
						System.out.println(resultSet.size());
						JsonArray result = new JsonArray();
						for (Row elem : resultSet) {
							result.add(JsonObject
									.mapFrom(new DeviceEntity(elem.getInteger("idDevice"), elem.getInteger("Group_idGroup"), elem.getString("name"), elem.getInteger("type"))));
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
	
	public static void getDeviceByName(MySQLPool mySqlClient, String name) {
		mySqlClient.getConnection(connection -> {
			if (connection.succeeded()) {
				connection.result().preparedQuery("SELECT * FROM x4c.device WHERE name = ?",
						Tuple.of(name), res -> {
							if (res.succeeded()) {
								// Get the result set
								RowSet<Row> resultSet = res.result();
								System.out.println(resultSet.size());
								JsonArray result = new JsonArray();
								for (Row elem : resultSet) {
									result.add(JsonObject.mapFrom(new DeviceEntity(elem.getInteger("idDevice"), elem.getInteger("Group_idGroup"), elem.getString("name"), elem.getInteger("type"))));
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
	
	public static void postOneDevice(MySQLPool mySqlClient, DeviceEntity device) {
	    mySqlClient.getConnection(connection -> {
	        if (connection.succeeded()) {
	            connection.result().preparedQuery("INSERT INTO x4c.device (idDevice, name, Group_idGroup, type) VALUES (?, ?, ?, ?)",
	                    Tuple.of(device.getIdDevice(), device.getNombre(), device.getIdGroup(), device.getType()), res -> {
	                        if (res.succeeded()) {
	                            System.out.println("Device added successfully.");
	                        } else {
	                            System.out.println("Error: " + res.cause().getLocalizedMessage());
	                        }
	                        connection.result().close();
	                    });
	        } else {
	            System.out.println("Connection failed: " + connection.cause().toString());
	        }
	    });
	}
	
	public static void deleteGroupById(MySQLPool mySqlClient, int idDevice) {
	    mySqlClient.getConnection(connection -> {
	        if (connection.succeeded()) {
	            connection.result().preparedQuery("DELETE FROM x4c.device WHERE idDevice = ?",
	                    Tuple.of(idDevice), res -> {
	                        if (res.succeeded()) {
	                            if (res.result().rowCount() > 0) {
	                                System.out.println("Device deleted successfully.");
	                            } else {
	                                System.out.println("No group found with the given ID.");
	                            }
	                        } else {
	                            System.out.println("Error: " + res.cause().getLocalizedMessage());
	                        }
	                        connection.result().close();
	                    });
	        } else {
	            System.out.println("Connection failed: " + connection.cause().toString());
	        }
	    });
	}

}
