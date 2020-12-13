package com.angoti.crud.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControle {

	@GetMapping("/")
	public String home() {
		return "home";
	}
}
