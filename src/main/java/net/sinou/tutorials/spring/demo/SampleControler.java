package net.sinou.tutorials.spring.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleControler {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World from sample controler!";
	}

	@RequestMapping("/bsinou")
	@ResponseBody
	String bsinouPriv() {
		return "Hello World from BSinou's blog!";
	}
}
