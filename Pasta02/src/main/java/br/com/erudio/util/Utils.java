package br.com.erudio.util;

import br.com.erudio.exception.UnsupportedMathOperationException;

public class Utils {

	public static boolean isNumeric(String strNumber) {
		if (strNumber == null)
			return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+"); // Verifica se é um número
	}
	
	public static void verifyNumbers(String number) {
		if (!isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
	}
	
}
