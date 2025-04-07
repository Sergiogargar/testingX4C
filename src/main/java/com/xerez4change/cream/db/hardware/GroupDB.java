package com.xerez4change.cream.db.hardware;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xerez4change.cream.entities.GroupEntity;
import com.xerez4change.cream.main.MainVerticle;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

public class GroupDB {
	
	private static MySQLPool mySqlClient;
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	static {
		mySqlClient = MainVerticle.mySqlClient;
	}
	
	public static void getAllGroups(RoutingContext routingContext) {
		mySqlClient.getConnection(connection -> {
			if (connection.succeeded()) {
				connection.result().query("SELECT * FROM x4c.group;", res -> {
					if (connection.succeeded()) {
						RowSet<Row> resultSet = res.result();
						System.out.println(resultSet.size());
						List<GroupEntity> result = new ArrayList<>();
						for (Row elem : resultSet) {
							result.add(new GroupEntity(elem.getInteger("idGroup"), elem.getString("name")));
						}
						connection.result().close();
						routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(200)
						.end(gson.toJson(result));
					} else {
						System.out.println("Error: " + connection.cause().getLocalizedMessage());
						connection.result().close();
	                    routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(400)
						.end(gson.toJson(new ArrayList<GroupEntity>()));
					}
				});
			} else {
				System.out.println(connection.cause().toString());
	            routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(400)
				.end(gson.toJson(new ArrayList<GroupEntity>()));
			}
		});
	}
	
	public static void getAllGroups() {
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
	
	public static void getGroupByName(String name) {
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
	
	public static void getGroupByName(RoutingContext routingContext, String name) {
		mySqlClient.getConnection(connection -> {
			if (connection.succeeded()) {
				connection.result().preparedQuery("SELECT * FROM x4c.group WHERE name = ?",
						Tuple.of(name), res -> {
							if (res.succeeded()) {
								RowSet<Row> resultSet = res.result();
								System.out.println(resultSet.size());
								List<GroupEntity> result = new ArrayList<>();
								for (Row elem : resultSet) {
									result.add(new GroupEntity(elem.getInteger("groupId"), elem.getString("name")));
								}
								connection.result().close();
								routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(200)
								.end(gson.toJson(result));
							} else {
								System.out.println("Error: " + res.cause().getLocalizedMessage());
								connection.result().close();
			                    routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(400)
								.end(gson.toJson(new ArrayList<GroupEntity>()));
			                }
						});
			} else {
				System.out.println(connection.cause().toString());
	            routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(400)
				.end(gson.toJson(new ArrayList<GroupEntity>()));
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
	
	public static void postOneGroup(RoutingContext routingContext, int idGroup, String name) {
	    mySqlClient.getConnection(connection -> {
	        if (connection.succeeded()) {
	            connection.result().preparedQuery("INSERT INTO x4c.group (idGroup, name) VALUES (?, ?)",
	                    Tuple.of(idGroup, name), res -> {
	                        if (res.succeeded()) {
	                        	GroupEntity result = new GroupEntity(idGroup, name);
	                            System.out.println("Group added successfully.");
	                            connection.result().close();
								routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(200)
								.end(gson.toJson(result));
	                        } else {
	                        	System.out.println("Error: " + res.cause().getLocalizedMessage());
	    						connection.result().close();
	    	                    routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(400)
	    						.end(gson.toJson(new ArrayList<GroupEntity>()));
	                        }
	                        connection.result().close();
	                    });
	        } else {
	        	System.out.println(connection.cause().toString());
	            routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(400)
				.end(gson.toJson(new ArrayList<GroupEntity>()));
	        }
	    });
	}
	
	public static void deleteGroupById(int idGroup) {
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
	
	public static void deleteGroupById(RoutingContext routingContext, Integer groupId) {
	    mySqlClient.getConnection(connection -> {
	        if (connection.succeeded()) {
	            connection.result().preparedQuery("DELETE FROM x4c.group WHERE idGroup = ?",
	                    Tuple.of(groupId), res -> {
	                        if (res.succeeded()) {
	                            if (res.result().rowCount() > 0) {
	                            	GroupEntity result = new GroupEntity();
	                                System.out.println("Group deleted successfully.");
	                                connection.result().close();
	        						routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(200)
	        						.end(gson.toJson(result));
	                            } else {
	                            	System.out.println("Error: " + res.cause().getLocalizedMessage());
	        						connection.result().close();
	        	                    routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(400)
	        						.end(gson.toJson(new ArrayList<GroupEntity>()));
	                            }
	                        } else {
	                        	System.out.println("Error: " + res.cause().getLocalizedMessage());
	    						connection.result().close();
	    	                    routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(400)
	    						.end(gson.toJson(new ArrayList<GroupEntity>()));
	                        }
	                    });
	        } else {
	            System.out.println(connection.cause().toString());
	            routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(400)
				.end(gson.toJson(new ArrayList<GroupEntity>()));
	        }
	    });
	}
}
