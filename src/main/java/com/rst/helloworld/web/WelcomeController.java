package com.rst.helloworld.web;

import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.rst.helloworld.service.HelloWorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WelcomeController {

	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private final HelloWorldService helloWorldService;
	private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Autowired
	public WelcomeController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
		logger.info("WelcomeController initialized with HelloWorldService");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {
		logger.info("\n\n========== INDEX REQUEST START ==========");
		logger.info("[INFO] HTTP GET request to / endpoint received");
		logger.debug("[DEBUG] index() method is being executed");
		logger.debug("[DEBUG] Processing default home page request");
		
		try {
			logger.debug("[DEBUG] Calling helloWorldService.getTitle() with empty name");
			String title = helloWorldService.getTitle("");
			logger.debug("[DEBUG] Retrieved title: {}", title);
			
			logger.debug("[DEBUG] Calling helloWorldService.getDesc()");
			String desc = helloWorldService.getDesc();
			logger.debug("[DEBUG] Retrieved description: {}", desc);
			
			// Add dynamic content
			model.put("title", title);
			model.put("msg", desc);
			model.put("pageTitle", "Home - Welcome");
			model.put("currentTime", LocalDateTime.now().format(timeFormatter));
			model.put("metadata", helloWorldService.getAppMetadata());
			
			logger.debug("[DEBUG] Model attributes added successfully");
			logger.info("[INFO] Index page loaded successfully");
			logger.info("========== INDEX REQUEST END ==========\n");
			
			return "index";
		} catch (Exception e) {
			logger.error("[ERROR] Exception occurred in index() method", e);
			logger.error("[ERROR] Exception message: {}", e.getMessage());
			logger.error("[ERROR] Exception stack trace:", e);
			throw new RuntimeException("Error loading index page", e);
		}
	}

	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public ModelAndView helloPost(@RequestParam("userName") String name, HttpServletRequest request) {
		logger.info("[INFO] HTTP POST /hello received, rendering greeting via POST");
		try {
			ModelAndView model = new ModelAndView();
			model.setViewName("index");
			if (name == null || name.trim().isEmpty()) {
				logger.warn("[WARNING] Empty userName submitted; returning index with error flag");
				model.addObject("error", "1");
				// Populate defaults so page renders consistently
				model.addObject("title", helloWorldService.getTitle(""));
				model.addObject("msg", helloWorldService.getDesc());
				model.addObject("pageTitle", "Home - Welcome");
				model.addObject("currentTime", LocalDateTime.now().format(timeFormatter));
				model.addObject("metadata", helloWorldService.getAppMetadata());
				return model;
			}
			String trimmed = name.trim();
			String title = helloWorldService.getTitle(trimmed);
			String desc = helloWorldService.getDesc();
			String greeting = helloWorldService.getGreeting(trimmed);

			model.addObject("title", title);
			model.addObject("msg", desc);
			model.addObject("greeting", greeting);
			model.addObject("userName", trimmed);
			model.addObject("pageTitle", "Hello - " + trimmed);
			model.addObject("currentTime", LocalDateTime.now().format(timeFormatter));
			model.addObject("metadata", helloWorldService.getAppMetadata());

			logger.info("[INFO] Personalized greeting rendered via POST for user: {}", trimmed);
			return model;
		} catch (Exception e) {
			logger.error("[ERROR] Exception during POST /hello processing", e);
			throw new RuntimeException("Error processing hello post", e);
		}
	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView helloGet(@PathVariable("name") String name, HttpServletRequest request) {
		long start = System.currentTimeMillis();
		logger.info("\n\n========== HELLO GET REQUEST START ==========");
		logger.info("[INFO] HTTP GET request to /hello/{name} endpoint received");
		logger.debug("[DEBUG] helloGet() method is being executed");
		logger.debug("[DEBUG] Path variable 'name' received: {}", name);

		try {
			String remoteAddr = request.getRemoteAddr();
			String userAgent = request.getHeader("User-Agent");
			String requestURI = request.getRequestURI();
			String queryString = request.getQueryString();

			logger.info("[INFO] Request URI: {}", requestURI);
			logger.info("[INFO] Remote Address: {}", remoteAddr);
			logger.debug("[DEBUG] User-Agent: {}", userAgent);
			logger.debug("[DEBUG] Query String: {}", queryString);

			ModelAndView model = new ModelAndView();
			model.setViewName("index");

			String title = helloWorldService.getTitle(name);
			String desc = helloWorldService.getDesc();
			String greeting = helloWorldService.getGreeting(name);

			model.addObject("title", title);
			model.addObject("msg", desc);
			model.addObject("greeting", greeting);
			model.addObject("userName", name);
			model.addObject("pageTitle", "Hello - " + name);
			model.addObject("currentTime", LocalDateTime.now().format(timeFormatter));
			model.addObject("metadata", helloWorldService.getAppMetadata());

			logger.info("[INFO] Personalized greeting page loaded successfully for user: {}", name);
			long duration = System.currentTimeMillis() - start;
			logger.info("[INFO] Request processing time: {} ms", duration);
			logger.info("========== HELLO GET REQUEST END ==========");

			return model;
		} catch (Exception e) {
			logger.error("[ERROR] Exception occurred in helloGet() method for name: {}", name, e);
			throw new RuntimeException("Error loading hello page", e);
		}
	}

}