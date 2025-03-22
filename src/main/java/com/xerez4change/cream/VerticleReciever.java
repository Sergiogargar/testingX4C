package com.xerez4change.cream;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleReciever extends AbstractVerticle{

	@Override
	public void start(Promise<Void> startFuture) {
		getVertx().eventBus().consumer("mensaje-punto-a-punto", message -> {
			String customMessage = (String) message.body();
			System.out.println("Mensaje recibido (" + message.address() + "): " + customMessage);
			String replyMessage = "S�, yo te he escuchado al mensaje \"" + message.body().toString() + "\"";
			message.reply(replyMessage);
		});
		startFuture.complete();
	}

	@Override
	public void stop(Promise<Void> stopFuture) throws Exception {
		super.stop(stopFuture);
	}
	
}
