package model;
/**
 * The class represents a bet.
 * 
 * @author Artyom M. a.k.a. artiyom
 *
 */
public class WagerModel {
	private double wagerSize;
	public WagerModel() {
		this.wagerSize = 5;
		
	}
	public WagerModel(int wagerSize) {
		this.wagerSize = wagerSize;
	}
	public void setWagerSize(double n) {
		this.wagerSize = n;
	}
	public double getWagerSize() {
		return this.wagerSize;
	}
	

}
