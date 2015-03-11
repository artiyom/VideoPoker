package model;
import java.lang.IllegalStateException;
/**
 * This class represents the amount of money a player is given.
 * @author Artyom M. a.k.a. artiyom
 *
 */
public class Credit {
	private double money;
	/**
	 * Default constructor creates 100 units of money
	 */
	public Credit() {
		this.money = 100;
	}
	/**
	 * This constructor creates object with specified amount of money
	 * @param money
	 */
	public Credit(double money) {
		this.money = money;
	}
	
	/**
	 * 
	 * @param money
	 */
	public void setAmount(double money) {
		this.money = money;
	}
	/**
	 * 
	 * @return money;
	 */
	public double getAmount() {
		return this.money;
	}
	
	/**
	 * Withdrawing money for bets, as long as there is money.
	 * Otherwise, throws an exception
	 * @param amount
	 */
	public void withdrawMoney(double amount) {
		if (this.money - amount >= 0) {
			this.money-=amount;
		}
		else {
			throw new IllegalStateException("No enough money.");
			
		}
			
	}
	
	/**
	 * Adds money when winning situation occurs
	 * @param amount
	 */
	public void addMoney(double amount) {
			this.money+=amount;
	}
	
	//debug
	public void printMoney() {
		System.out.println(this.money);
	}
}
