package com.xerez4change.cream.db.hardware;

import com.xerez4change.cream.entities.GroupEntity;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

public class GroupDB {

	public static void getAllGroups(MySQLPool mySqlClient) {
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
	
	public static void getAllGroupsWithConnection(MySQLPool mySqlClient) {
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
	
	public static void getGroupsByName(MySQLPool mySqlClient, String name) {
		mySqlClient.getConnection(connection -> {
			if (connection.succeeded()) {
				connection.result().preparedQuery("SELECT * FROM x4c.group WHERE name = ?",
						Tuple.of(name), res -> {
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
	
	public static void putGroupByName(MySQLPool mySqlClient, int idGroup, String newName) {
	    mySqlClient.getConnection(connection -> {
	        if (connection.succeeded()) {
	            connection.result().preparedQuery("UPDATE x4c.group SET name = ? WHERE idGroup = ?",
	                    Tuple.of(newName, idGroup), res -> {
	                        if (res.succeeded()) {
	                            if (res.result().rowCount() > 0) {
	                                System.out.println("Group updated successfully.");
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
	
	public static void postOneGroup(MySQLPool mySqlClient, int idGroup, String name) {
	    mySqlClient.getConnection(connection -> {
	        if (connection.succeeded()) {
	            connection.result().preparedQuery("INSERT INTO x4c.group (idGroup, name) VALUES (?, ?)",
	                    Tuple.of(idGroup, name), res -> {
	                        if (res.succeeded()) {
	                            System.out.println("Group added successfully.");
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
	
	public static void deleteGroupById(MySQLPool mySqlClient, int idGroup) {
	    mySqlClient.getConnection(connection -> {
	        if (connection.succeeded()) {
	            connection.result().preparedQuery("DELETE FROM x4c.group WHERE idGroup = ?",
	                    Tuple.of(idGroup), res -> {
	                        if (res.succeeded()) {
	                            if (res.result().rowCount() > 0) {
	                                System.out.println("Group deleted successfully.");
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
