package com.codingDojo.pokerHandKata;
import java.util.Comparator;
/**
 * Implementation for Card methods.
 * Card provides a suit and an Int digit;
**/
public class Card implements Comparable<Card> { 

	private int num;
	private String suit;
	
	/**
	 * Constructor.
	 */
	public Card(int num, String suit) {
		this.num = num;
		this.suit = suit;
	}
	/**
	 * gives the number for the card
	 * 
	 * @return int number attached to card
	 */
	public int getNum() {
		return this.num;
	}
/**
 * gives the suit for the card
 * @return suit String attached to card
 */
	public String getSuit() {
		return this.suit;
	}
 /**
  * comparable method tells Collection how to properly handle sorting for lists of card[]
  * @return value of higher cards int number
  */
	@Override
	public int compareTo(Card c) {
		// TODO Auto-generated method stub
		return Integer.valueOf(this.getNum()).compareTo(c.getNum());
	}

	

}
