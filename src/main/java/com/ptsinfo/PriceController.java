package com.ptsinfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@Controller
public class PriceController {

	@RequestMapping("/price")
	public String price(@RequestParam(value="code") String code, Model model) {
		model.addAttribute("code", code);
		model.addAttribute("transactionTime", now().format(ofPattern("yyyy/MM/dd HH:mm:ss")));
		switch (code.length()){
			case 4:
				return "price1";
			case 5:
			case 9:
				return "price2";
			default:
				return "price1";
		}
	}
}
