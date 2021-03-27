package my.self.demo.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

	public Map<String, String> getOperationsMap() {
		Map<String, String> op = new HashMap<>();
		op.put("+", "plus");
		op.put("-", "minus");
		return op;
	}
	
	public Integer getResult(Integer a, Integer b, String op) {
		Integer res = null;
		
		switch (op) {
		case "plus":
			res = a+b;
			break;

		case "minus":
			res = a - b;
			break;
		default:
			break;
		}
		return res;
	}
	
}
