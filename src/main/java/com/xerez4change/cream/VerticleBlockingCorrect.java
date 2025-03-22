package com.xerez4change.cream;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleBlockingCorrect extends AbstractVerticle{

	@SuppressWarnings("deprecation")
	public void start(Promise<Void> startFuture) {
		vertx.executeBlocking(future -> {
			try {
				Thread.sleep(5000);
				future.complete("Finalizado");
			} catch (Exception e) {
				e.printStackTrace();
				future.complete("Interrumpido");
			}
		}, res -> {
			System.out.println("El resultado es: " + res.result());
		});
	}
}
