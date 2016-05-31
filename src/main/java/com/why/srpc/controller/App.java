package com.why.srpc.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.why.srpc.core.Path;

/**
 * Hello world!
 *
 */
@Controller
public class App {
	private static final Logger log = LoggerFactory.getLogger(App.class);

	@Path("/hello")
	public String hello(Map<String, String> params) {
		return "HELLO";
	}
}
