package model;
/**
 * The class represents a bet.
 * 
 * @author Artyom M. a.k.a. artiyom
 *
 */
public class Wager {
	private double wagerSize;
	public Wager() {
		this.wagerSize = 5;
		
	}
	public Wager(int wagerSize) {
		this.wagerSize = wagerSize;
	}
	public void setWagerSize(double n) {
		this.wagerSize = n;
	}
	public double getWagerSize() {
		return this.wagerSize;
	}
	

}
