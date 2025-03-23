package com.xerez4change.cream.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xerez4change.cream.entities.GroupEntity;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class RestServer extends AbstractVerticle{

	private Map<Integer, GroupEntity> groups = new HashMap<Integer, GroupEntity>();
	private Gson gson;
	
	public void start(Promise<Void> startFuture) {
		// Creating some synthetic data
		createSomeData(25);

		// Instantiating a Gson serialize object using specific date format
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		// Defining the router object
		Router router = Router.router(vertx);

		// Handling any server startup result
		vertx.createHttpServer().requestHandler(router::handle).listen(3000, result -> {
			if (result.succeeded()) {
				startFuture.complete();
			} else {
				startFuture.fail(result.cause());
			}
		});

		// Defining URI paths for each method in RESTful interface, including body
		// handling by /api/users* or /api/users/*
		router.route("/*").handler(BodyHandler.create());
		router.get("/api/groups").handler(this::getAllWithParams);
		router.get("/api/groups/:groupid").handler(this::getOne);
		router.post("/api/groups").handler(this::addOne);
		router.delete("/api/groups/del/:groupid").handler(this::deleteOne);
		router.put("/api/groups/:groupid").handler(this::putOne);
	}

	@SuppressWarnings("unused")
	private void getAll(RoutingContext routingContext) {
		routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(200)
				.end(gson.toJson(groups.values()));
	}
	
	private void getAllWithParams(RoutingContext routingContext) {
		final String name = routingContext.queryParams().contains("name") ? 
				routingContext.queryParam("name").get(0) : null;
		final Integer group = routingContext.queryParams().contains("id") ? 
				Integer.parseInt(routingContext.queryParam("id").get(0)) : null;
		
		routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(200)
				.end(gson.toJson(groups.values().stream().filter(elem -> {
					boolean res = true;
					res = res && name != null ? elem.getName().equals(name) : true;
					res = res &&  group != null ? elem.getIdGroup().equals(group) : true;
					return res;
				}).collect(Collectors.toList())));
	}

	private void getOne(RoutingContext routingContext) {
		int id = Integer.parseInt(routingContext.request().getParam("groupid"));
		if (groups.containsKey(id)) {
			GroupEntity ds = groups.get(id);
			routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(200)
					.end(gson.toJson(ds));
		} else {
			routingContext.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(204)
					.end();
		}
	}

	private void addOne(RoutingContext routingContext) {
		final GroupEntity group = gson.fromJson(routingContext.getBodyAsString(), GroupEntity.class);
		groups.put(group.getIdGroup(), group);
		routingContext.response().setStatusCode(201).putHeader("content-type", "application/json; charset=utf-8")
				.end(gson.toJson(group));
	}

	private void deleteOne(RoutingContext routingContext) {
		int id = Integer.parseInt(routingContext.request().getParam("groupid"));
		if (groups.containsKey(id)) {
			GroupEntity group = groups.get(id);
			groups.remove(id);
			routingContext.response().setStatusCode(200).putHeader("content-type", "application/json; charset=utf-8")
					.end(gson.toJson(group));
		} else {
			routingContext.response().setStatusCode(204).putHeader("content-type", "application/json; charset=utf-8")
					.end();
		}
	}

	private void putOne(RoutingContext routingContext) {
		int id = Integer.parseInt(routingContext.request().getParam("userid"));
		GroupEntity ds = groups.get(id);
		final GroupEntity element = gson.fromJson(routingContext.getBodyAsString(), GroupEntity.class);
		ds.setName(element.getName());
		ds.setIdGroup(element.getIdGroup());
		routingContext.response().setStatusCode(201).putHeader("content-type", "application/json; charset=utf-8")
				.end(gson.toJson(element));
	}

	private void createSomeData(int number) {
		Random rnd = new Random();
		IntStream.range(0, number).forEach(elem -> {
			int id = rnd.nextInt();
			groups.put(id, new GroupEntity(id, "Nombre_" + id));
		});
	}
	
}
