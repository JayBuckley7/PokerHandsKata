package com.codingdojo.pokehandskata;

import junit.framework.AssertionFailedError;

/**
 * Implementation for Card methods. Card provides a suit and an Int digit.
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
   * gives the number for the card.
   * 
   * @return int number attached to card
   */
  public int getNum() {
    return this.num;
  }

  /**
   * turns characters 10-14 back into string for easy reading.
   * 
   * @param k
   *          value of card
   * @return string pertaining to card value
   */
  public static String cardIntToString(int k) {
    String temp = "";
    switch (k) {
      case 11:
        temp = "Jack";
        break;
      case 12:
        temp = "Queen";
        break;
      case 13:
        temp = "King";
        break;
      case 14:
        temp = "Ace";
        break;

      default:
        temp = String.valueOf(k);
        break;
    }
    return temp;
  }

  /**
   * changes Card number letters into numbers for math purposes.
   *
   * @param c
   *          card to check
   * @return integer value of c as a card
   */
  public static int asInt(char c) {
    int temp = 0;
    switch (c) {
      case '2':
        temp = 2;
        break;
      case '3':
        temp = 3;
        break;
      case '4':
        temp = 4;
        break;
      case '5':
        temp = 5;
        break;
      case '6':
        temp = 6;
        break;
      case '7':
        temp = 7;
        break;
      case '8':
        temp = 8;
        break;
      case '9':
        temp = 9;
        break;
      case 'T':
        temp = 10;
        break;
      case 'J':
        temp = 11;
        break;
      case 'Q':
        temp = 12;
        break;
      case 'K':
        temp = 13;
        break;
      case 'A':
        temp = 14;
        break;

      default:
        throw new AssertionFailedError(
          "Invalid Character " + c + " in card number column: Expected 1-9,J,Q,K,A");

    }
    return temp;
  }

  /**
   * gives the suit for the card.
   * 
   * @return suit String attached to card
   */
  public String getSuit() {
    return this.suit;
  }

  /**
   * comparable method tells Collection how to properly handle sorting for lists of card[].
   * 
   * @return value of higher cards int number
   */
  @Override
  public int compareTo(Card c) {
    // TODO Auto-generated method stub
    return Integer.valueOf(this.getNum()).compareTo(c.getNum());
  }

}
