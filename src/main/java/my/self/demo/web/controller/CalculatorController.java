package my.self.demo.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import my.self.demo.domain.CalculatorService;

@Controller
@RequestMapping("/calc")
public class CalculatorController {

	@Autowired
	private CalculatorService service;
	
	@GetMapping("/input")
	public String inputPage(Model model) {
		model.addAttribute("title", "Калькулятор");
		
		model.addAttribute("op", service.getOperationsMap());
		
		return "calc/input.html";
	}
	
	@GetMapping("/result")
	public String resultPage(
			@RequestParam(name = "aValue") Integer a,
			@RequestParam(name = "bValue") Integer b,
			@RequestParam(name = "op") String op,
			Model model) {
		model.addAttribute("title", "Калькулятор - результат");
		model.addAttribute("res", service.getResult(a, b, op));
		
		return "calc/result.html";
	}
	
	@GetMapping("/plus/{a}/{b}")
	public ModelAndView plusPage(
			@PathVariable("a") Integer a,
			@PathVariable("b") Integer b,
			ModelAndView model) {
		
		model.addObject("aValue", a);
		model.addObject("bValue", b);
		model.addObject("op", "plus");
		
		model.setViewName("redirect:/calc/result");
		
		return model;
	}
	
}
