package com.codingdojo.pokerhandskata;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Player holds a name and hand of Cards[5].
 * 
 * @author Jay
 * 
 */
public final class Player {

  /*
   * Private members
   */

  private String name;
  private Card[] cards = new Card[5];
  int score = 0;
  String winCondition = "";
  Map<Integer, Integer> map = new HashMap<Integer, Integer>();
  /** CONSTANTS. **/
  private static int FullHouseInHand = 111;
  private static int TwoPairInHand = 222;

  // 11=jack-12=queen-13=King-14=Ace

  /**
   * Constructor. a player is a name and 5 cards of<int,String> sets up a map full of card combos.
   * for specific hand for easy calculations
   * 
   * @param name
   *          string assosciated with card
   * @param hand
   *          Array of Card for hand.
   */
  public Player(String name, Card[] hand) {
    this.name = name;
    this.cards[0] = hand[0];
    this.cards[1] = hand[1];
    this.cards[2] = hand[2];
    this.cards[3] = hand[3];
    this.cards[4] = hand[4];

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();// init null//<cardnum,timesSeen>
    for (Card c : this.cards) {
      int tmpNum = c.getNum();
      if (map.containsKey(tmpNum)) { // weve got this add
        map.replace(tmpNum, map.get(tmpNum) + 1); // boost the val

      } else {
        map.put(tmpNum, 1); // put in in the map
      }
    }
    this.map = map;
  }

  /*
   * Methods ----------------------------------------------------------------
   */
  /**
   * turns characters 10-14 back into string for easy reading.
   * 
   * @param k
   *          value of card
   * @return string pertaining to card value
   */
  private static String cardIntToString(int k) {
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
   * gives back the name of the player.
   * 
   * @return name of player
   */
  public String name() {
    return this.name;
  }

  /**
   * decides if n number of cards all share same suit.
   * 
   * @param cards
   *          (any number of cards to check)
   * @return true if all suits match false otherwise
   */
  private Boolean sameSuit() {
    Boolean tmp = true;
    String suit = cards[0].getSuit();
    for (Card c : cards) {
      if (!c.getSuit().equals(suit)) {
        tmp = false;
      }
    }

    return tmp;
  }

  /**
   * decides weather n number of cards are of consecutive values.
   * 
   * @param cards
   *          cards to check
   * @return true if cards are of consecutive value false otherwise
   */
  Boolean isStraight() {
    Boolean tmp = true;
    for (int i = cards.length - 1; i > 0; i--) {
      int now = i;
      int next = now - 1;
      if (cards[now].getNum() - cards[next].getNum() != 1) {
        tmp = false;
      }
    }

    return tmp;
  }

  /**
   * decides weather the play hand has a straightFlush.
   * 
   * @return true if true false otherwise
   */
  Boolean hasStraightFlush() {
    boolean tmp = false;
    // 5 cards of the same suit with consecutive values.
    // Ranked by the highest card in the hand.
    if (isStraight() && sameSuit()) {
      tmp = true;
    }

    return tmp;
  }

  /**
   * gets number of duplicate cards in the players hand from the constructor and returns and int.
   * based on the card with highest value in map
   * 
   * @return 1 if highest number no pairs, 2 one pair 3 if 3 of a kind 5 if full house 8 if 2 pair
   * 
   */
  int numDuplicates() {
    Map<Integer, Integer> tmpMap = new HashMap<Integer, Integer>();
    tmpMap.putAll(this.map);// alliasing

    int ret = (Collections.max(tmpMap.values())); // the number of duplciates in the map
    tmpMap.remove(Collections.max(tmpMap.entrySet(), Map.Entry.comparingByValue()).getKey());
    
    int check = Collections.max(tmpMap.values()); // now if this is 2 and the old max was 5 we had a
    
    if (check == 2 && ret == 3) {
      ret = FullHouseInHand;// this is not possible normally so this wont interfere with duplicates
    } else if (check == 2 && ret == 2) {
      ret = TwoPairInHand;// this is not possible normally so this wont interfere with duplicates
    }
    return (ret);
  }

  /**
   * gives number of highest card among n cards.
   * 
   * @return highest cards number
   */
  int highestCard() {
    Map<Integer, Integer> tmpMap = new HashMap<Integer, Integer>();// init null//
    for (Card c : this.cards) {
      int tmpNum = c.getNum();
      if (!tmpMap.containsKey(tmpNum)) { // weve got this add
        tmpMap.put(tmpNum, 1); // put in in the map
      }
    }
    int ret = (Collections.max(tmpMap.keySet())); // highest
    return (ret);
  }

  /**
   * checks for a four of a kind.
   * 
   * @return true if there is false otherwise
   */
  Boolean hasFourOfAKind() {
    boolean tmp = false;
    if (numDuplicates() == 4) {
      tmp = true;
    }
    return tmp;
  }

  /**
   * checks hand for full house.
   * 
   * @return true if present false otherwise
   */
  Boolean hasFullHouse() {
    boolean tmp = false;

    if (numDuplicates() == FullHouseInHand) { // duplicates will return 5 if their is duplciate 3
      // and duplicate 2
      tmp = true;
    }

    return tmp;
  }

  /**
   * checks hand for flush.
   * 
   * @return true if present false otherwise
   */
  Boolean hasFlush() {
    boolean tmp = false;
    if (sameSuit()) {
      tmp = true;
    }
    return tmp;
  }

  /**
   * checks hand for straight.
   * 
   * @return true if present false otherwise
   */
  Boolean hasStraight() {
    boolean tmp = false;
    if (isStraight()) {
      tmp = true;
    }
    return tmp;
  }

  /**
   * checks hand for 3 of a kind.
   * 
   * @return true if present false otherwise
   */
  Boolean hasThreeOfAKind() {
    boolean tmp = false;

    if (numDuplicates() == 3) {
      tmp = true;
    }

    return tmp;
  }

  /**
   * checks two pair.
   * 
   * @return true if present false otherwise
   */
  Boolean hasTwoPair() {
    boolean tmp = false;
    if (numDuplicates() == TwoPairInHand) { // duplicates will return this if their is 2 duplicates
     
      tmp = true;

    }

    return tmp;
  }

  /**
   * checks for a pair.
   * 
   * @return true if present false otherwise
   */
  Boolean hasPair() {
    boolean tmp = false;
    if (numDuplicates() == 2) {
      tmp = true;
    }

    return tmp;
  }

  /**
   * turn all cards into a int in the billions to rank high.
   * 
   * @return int value of highest cards number
   * 
   */
  int highCard() {
    return highestCard();
  }

  /**
   * returns a a win message along with the translated high card.
   * 
   * @return string with message for how this hand won
   */
  String getWinCondition() {
    String temp = this.winCondition;
    if (temp.equals("")) {
      temp = "HighCard: " + cardIntToString(this.highCard());
    }
    return temp;
  }

  /**
   *  gives card number of nth card in hand.
   * @param position
   *          5-1 of card in hand
   * @return number of card at nth position
   */
  int getCardNum(int n) {
    return this.cards[n].getNum();
  }

}
