package com.worldline.activiti.sample;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestActiviti2Camel {

	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("activiti.cfg.xml");
		ProcessEngine processEngine = applicationContext.getBean(ProcessEngine.class);
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		repositoryService.createDeployment().addClasspathResource("processes/activiti2Camel.bpmn").deploy();

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("vara", "value a");
		for (int i = 0; i < 2000; i++) {
			ProcessInstance instance = runtimeService.startProcessInstanceByKey("activiti2Camel", variables);
			System.out.println("started " + instance.getId());
		}

	}

}
