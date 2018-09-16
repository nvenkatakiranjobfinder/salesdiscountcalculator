package com.comp.retailstore.sales.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("discountservice")
public class DiscountController {

	@RequestMapping(method = RequestMethod.POST, value = "/calculatediscount")
	@ResponseBody
	double calculateDiscount() {
		/*
		 * TODO
		 * 
		 * could be enhanced to RESt service.
		 */
		return 0;
	}
}
