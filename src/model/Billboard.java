package model;

public class Billboard {
	
	private double weight,heigth;
	private boolean inUse;
	private String brand;

	public Billboard(double w, double h, boolean iu, String b) {
		 weight=w;
		 heigth=h;
		 inUse=iu;
		 brand=b;
	}

	public double getWeight() {
		return weight;
	}

	public double getHeigth() {
		return heigth;
	}

	public boolean isInUse() {
		return inUse;
	}

	public String getBrand() {
		return brand;
	}
	
	public double calculateArea() {
		return weight*heigth;
	}
}
