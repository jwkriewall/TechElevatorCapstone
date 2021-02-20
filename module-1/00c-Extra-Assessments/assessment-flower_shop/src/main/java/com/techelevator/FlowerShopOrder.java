package com.techelevator;

public class FlowerShopOrder {
	
	String bouquetType;
	int numberOfRoses;
	double subtotal;
	
	public void order(String bouquetType, int numberOfRoses) {
		this.numberOfRoses = numberOfRoses;
		this.bouquetType = bouquetType;
	}
	
	
	
	public double deliveryCost(boolean sameDayDelivery, String zipCode) {
		double sameDayDeliveryCost = 5.99;
		
		double lesserDeliveryFee = 3.99;
		double greaterDeliveryFee = 6.99;
		double waivedFee = 0.00;
		double fullFee = 19.99;
		
		double fees = 0;
		
		int zipAsInt = Integer.parseInt(zipCode);
		
		if (zipAsInt >= 20000 && zipAsInt <=29999) {
			fees += lesserDeliveryFee;
		}
		if (zipAsInt > 30000 && zipAsInt <= 39999) {
			fees += greaterDeliveryFee;
		}
		if (zipAsInt >= 10000 && zipAsInt < 20000) {
			fees += waivedFee;
		}
		if (zipAsInt < 10000 || zipAsInt > 40000) {
			fees += fullFee;
			sameDayDelivery = false;
		}
		if (sameDayDelivery = true) {
			fees += sameDayDeliveryCost;
		}
		
		return fees;
	}
	
	public double subtotal(String bouquetType, int numberOfRoses) {
		double standardBouquetFee = 19.99;
		
		subtotal += numberOfRoses * 2.99;
		
		if (bouquetType == "Standard") {
			subtotal += standardBouquetFee;
		}
		
		
		return subtotal;
	}
	
	@Override
	public String toString(){
		return "ORDER - " + bouquetType + " - " + numberOfRoses + "roses - ";
	}

}
