package com.rst.helloworld.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class HelloWorldService {

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldService.class);
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public String getDesc() {
		logger.info("========== HelloWorldService.getDesc() START ==========");
		logger.debug("[DEBUG] getDesc() method is being executed");
		String description = "Maven + Spring MVC Example By Rushi Tech";
		logger.debug("[DEBUG] Generated description: {}", description);
		logger.info("========== HelloWorldService.getDesc() END ==========");
		return description;
	}

	public String getTitle(String name) {
		logger.info("========== HelloWorldService.getTitle() START ==========");
		logger.debug("[DEBUG] getTitle() method called with parameter name: {}", name);
		logger.debug("[DEBUG] Name is empty: {}", StringUtils.isEmpty(name));

		String title;
		if(StringUtils.isEmpty(name)){
			title = "Welcome! Hello From Rushi Technologies";
			logger.debug("[DEBUG] Empty name detected - returning default welcome message");
		} else {
			title = "Hello " + name.trim();
			logger.debug("[DEBUG] Custom name provided - generating personalized title: {}", title);
		}
		
		logger.debug("[DEBUG] Final title: {}", title);
		logger.info("========== HelloWorldService.getTitle() END ==========");
		return title;
	}

	public Map<String, Object> getAppMetadata() {
		logger.info("========== HelloWorldService.getAppMetadata() START ==========");
		logger.debug("[DEBUG] Collecting application metadata");
		
		Map<String, Object> metadata = new HashMap<>();
		metadata.put("applicationName", "Rushi Technologies Web App");
		metadata.put("timestamp", LocalDateTime.now().format(dateFormatter));
		metadata.put("javaVersion", System.getProperty("java.version"));
		metadata.put("osName", System.getProperty("os.name"));
		
		logger.debug("[DEBUG] Metadata collected: {}", metadata);
		logger.info("========== HelloWorldService.getAppMetadata() END ==========");
		return metadata;
	}

	public String getGreeting(String userName) {
		logger.info("========== HelloWorldService.getGreeting() START ==========");
		logger.debug("[DEBUG] getGreeting() called for user: {}", userName);
		
		if(StringUtils.isEmpty(userName)) {
			logger.warn("[WARNING] User name is empty, using default greeting");
			return "Welcome Guest! 👋";
		}
		
		String greeting = "Welcome, " + userName + "! 👋";
		logger.debug("[DEBUG] Generated greeting: {}", greeting);
		logger.info("========== HelloWorldService.getGreeting() END ==========");
		return greeting;
	}

}
