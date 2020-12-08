package br.com.erudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.service.OperationService;
import br.com.erudio.util.Utils;

@RestController
public class MathController {

	@Autowired
	private OperationService service;
	
	@RequestMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo
			) 
	{
		Utils.verifyNumbers(numberOne);
		Utils.verifyNumbers(numberTwo);
		return service.sum(numberOne, numberTwo);
	}
	
	@RequestMapping("/subtraction/{numberOne}/{numberTwo}")
	public Double subtraction(
			@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo
			)
	{
		Utils.verifyNumbers(numberOne);
		Utils.verifyNumbers(numberTwo);
		return service.subtraction(numberOne, numberTwo);
	}

	@RequestMapping("/multiplication/{numberOne}/{numberTwo}")
	public Double multiplication(
			@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo
			)
	{
		Utils.verifyNumbers(numberOne);
		Utils.verifyNumbers(numberTwo);
		return service.multiplication(numberOne, numberTwo);
	}
	
	@RequestMapping("/division/{numberOne}/{numberTwo}")
	public Double division(
			@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo
			) 
	{
		Utils.verifyNumbers(numberOne);
		Utils.verifyNumbers(numberTwo);
		return service.division(numberOne, numberTwo);
	}
	
	@RequestMapping("/mean/{numberOne}/{numberTwo}")
	public Double mean(
			@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo
			) 
	{
		Utils.verifyNumbers(numberOne);
		Utils.verifyNumbers(numberTwo);
		return service.mean(numberOne, numberTwo);
	}
	
	@RequestMapping("/squareRoot/{numberOne}")
	public Double squareRoot(
			@PathVariable("numberOne") String numberOne
			) 
	{
		Utils.verifyNumbers(numberOne);
		return service.squareRoot(numberOne);
	}

}
