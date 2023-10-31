package it.dedagroup.project_cea.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {
	
	public static double waterCostCalculation(double mcLiter) {
		double totalCost = 0.0;
		if(mcLiter <= 30) totalCost = mcLiter * 1.37;
		else if(mcLiter > 30 && mcLiter <= 90) totalCost = 30 * 1.37 + (mcLiter - 30) * 2.88;
		else if(mcLiter > 90) totalCost = 30 * 1.37 + 60 * 2.88 + (mcLiter - 90) * 3.61;
		BigDecimal bd = new BigDecimal(totalCost).setScale(2, RoundingMode.DOWN);
		return totalCost = bd.doubleValue();
	}



}
