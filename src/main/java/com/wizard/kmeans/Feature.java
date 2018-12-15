package com.wizard.kmeans;

public class Feature {
	double x;
	double y;
	static int featureCount=2;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public static int getFeatureCount() {
		return featureCount;
	}
	public static void setFeatureCount(int featureCount) {
		Feature.featureCount = featureCount;
	}
	public Feature(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	

}
