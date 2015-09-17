package com.worldline.activiti.exercise.routes;

import java.util.Random;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class WarehouseRouteBuilder extends RouteBuilder {
	private Random r = new Random();

	@Override
	public void configure() throws Exception {
		from("activiti:activiti2Camel:sendListOfProducts?copyVariablesFromProperties=true").process(logProcessor);
	}

	Processor logProcessor = new Processor() {
		@Override
		public void process(Exchange exchange) throws Exception {
			int pid = Integer.parseInt(exchange.getProperties().get("PROCESS_ID_PROPERTY").toString());
			int i = Integer.parseInt(exchange.getProperties().get("loopCounter").toString());
			System.out.printf("pid : %s, loop : %s %n", pid, i);
			if (r.nextInt(10) % 10 == 0)
				throw new RuntimeException("exception from Camel");
		}
	};
}
