package my.self.demo.web.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.self.demo.domain.mail.MailClient;
import my.self.demo.domain.model.CarBrand;
import my.self.demo.domain.model.CarModel;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private MailClient mail;
	
	@GetMapping("/set")
	public String set(HttpServletResponse response) {
		Cookie cookie = new Cookie("ban", "hjsd#4");
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(3600);
		
		response.addCookie(cookie);
		return "Ok";
	}
	
	@GetMapping("/get")
	public String get(@CookieValue(name = "ban")String ban) {
		return "Get cookie ban=" + ban;
	}
	
	@GetMapping("/write")
	public String write(HttpSession session) {
		CarModel car = new CarModel(1, new CarBrand(1, "Lada", ""), "Vesta", new Date(), 1245.24);
		
		session.setAttribute("car", car);
		
		return "Ok";
	}
	
	@GetMapping("/read")
	public String read(HttpSession session ) {
		return "Read car " + (CarModel)session.getAttribute("car");
	}
	
	@GetMapping("/mail-send")
	public String mailSend() {
		mail.sendTestEmail("test@test.tt");
		return "Ok";
	}
}
