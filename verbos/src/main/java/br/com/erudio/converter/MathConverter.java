package br.com.erudio.converter;

import br.com.erudio.util.Utils;

public class MathConverter {

	public static Double convertToDouble(String strNumber) {
		if (strNumber == null)
			return 0D;
		String number = strNumber.replaceAll(",", ".");
		if (Utils.isNumeric(number))
			return Double.parseDouble(number);
		return 0D;
	}
	
}
