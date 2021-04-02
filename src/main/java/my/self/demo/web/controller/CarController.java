package my.self.demo.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import my.self.demo.data.cars.CarModelDataRepository;
import my.self.demo.domain.CarService;
import my.self.demo.domain.model.CarModelData;

@Controller
public class CarController {

	@Autowired
	private CarService carService;
	
	@Autowired
	private CarModelDataRepository carModelDataRepository;
	
	@GetMapping("car/list")
	public String list(Model model) {
		model.addAttribute("cars", carService.getCarsList());
		model.addAttribute("title", "Список автомобилей (демонстрация Spring JDBC)");
		
		return "car/list";
	}

	@GetMapping("car/list-data")
	public ModelAndView listData(ModelAndView model) {
		
		Optional<CarModelData> car = carModelDataRepository.findById(200);
		
		System.out.println( car.isPresent() ? car.get() : "Not found" );
		
		model.addObject("cars", carModelDataRepository.findAllByBrandName("Lada"));
		model.addObject("title", "Список автомобилей (демонстрация Spring Data)");
		
		model.setViewName("car/list-data");
		
		return model;
	}
	
}
