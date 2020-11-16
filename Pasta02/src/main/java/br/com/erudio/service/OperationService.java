package br.com.erudio.service;

import org.springframework.stereotype.Service;

import br.com.erudio.converter.MathConverter;

@Service
public class OperationService {

	public Double sum(String numberOne, String numberTwo) {
		// Converte os valores para Double e já faz a soma
		Double sum = MathConverter.convertToDouble(numberOne) + MathConverter.convertToDouble(numberTwo);
		return sum;
	}
	
	public Double subtraction(String numberOne, String numberTwo) {
		Double subtraction = MathConverter.convertToDouble(numberOne) - MathConverter.convertToDouble(numberTwo);
		return subtraction;
	}

	public Double multiplication(String numberOne, String numberTwo) {
		Double multiplication = MathConverter.convertToDouble(numberOne) * MathConverter.convertToDouble(numberTwo);
		return multiplication;
	}
	
	public Double division(String numberOne, String numberTwo) {
		Double division = MathConverter.convertToDouble(numberOne) / MathConverter.convertToDouble(numberTwo);
		return division;
	}
	
	public Double mean(String numberOne, String numberTwo) {
		Double mean = (MathConverter.convertToDouble(numberOne) + MathConverter.convertToDouble(numberTwo)) / 2;
		return mean;
	}
	
	public Double squareRoot(String numberOne) {
		Double squareRoot = (Double) Math.sqrt(MathConverter.convertToDouble(numberOne));
		return squareRoot;
	}
	
}
