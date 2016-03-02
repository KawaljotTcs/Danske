package com.dessapi.util;


import java.text.DecimalFormat;

import com.dessapi.bean.EMICalcBean;


public class EMCalc {



	public Double calcEmi(double p, double r, double n) {
		double R = (r / 12) / 100;
		double e = (p * R * (Math.pow((1 + R), n)) / ((Math.pow((1 + R), n)) - 1));

		return e;
	}

	public EMICalcBean calculateEmi(double p, double r, double n) {

		DecimalFormat df = new DecimalFormat("#.00");
		double R = (r / 12) / 100;
		double P = p;
		double e = calcEmi(P, r, n);
		double totalInt = Math.round((e * n) - p);
		double totalAmt = Math.round((e * n));
		EMICalcBean ecb = new EMICalcBean();
		
		

		ecb.setEmi(Double.parseDouble(df.format(Math.round(e))));
		ecb.setPrincipal(Double.parseDouble(df.format(P)));
		ecb.setNumber_of_months(Double.parseDouble(df.format(n)));
		ecb.setTotal_amount(Double.parseDouble(df.format(totalAmt)));
		ecb.setRate_of_interest(Double.parseDouble(df.format(totalInt)));
		ecb.setRate_of_interest(Double.parseDouble(df.format(R)));
		
		return ecb;

	}

}