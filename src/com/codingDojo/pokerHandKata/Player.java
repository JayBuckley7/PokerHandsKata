package com.codingDojo.pokerHandKata;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Jay
 * 
 */
public final class Player {

	/*
	 * Private members --------------------------------------------------------
	 */

	private String name;
	private Card[] cards = new Card[5];
	private int score = 0;
	private String winCondition = "";
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	/** CONSTANTS**/
	private static int STRAIGHT_FLUSH = 9000000;
	private static int FOUR_KIND = 8000000;
	private static int FULL_HOUSE = 7000000;
	private static int FLUSH = 6000000;
	private static int STRAIGHT = 5000000;
	private static int THREE_KIND = 4000000;
	private static int TWO_PAIR = 3000000;
	private static int ONE_PAIR = 2000000;
	private static int HIGH_CARD = 10000;
	private static int SECOND_HIGH = 100;
	private static int LAST_HIGH = 10;
	/**card hand association constants **/
	private static int FullHouseInHand = 111;
	private static int TwoPairInHand = 222;
	
	 

	// 11=jack-12=queen-13=King-14=Ace

	/**
	 * Constructor.
	 * a player is a name and 5 cards of<int,String>
	 * sets up a map full of card combos for specific hand for easy calculations
	 * 
	 * @param name
	 * 			string assosciated with card
	 * @param card0
	 * 				card of int,suit
	 * @param card1
	 * 				card of int,suit
	 * @param card2
	 * 				card of int,suit
	 * @param card3
	 * 				card of int,suit
	 * @param card4
	 * 				card of int,suit
	 */
	public Player(String name, Card card0, Card card1, Card card2, Card card3, Card card4) {
		this.name = name;
		this.cards[0] = card0;
		this.cards[1] = card1;
		this.cards[2] = card2;
		this.cards[3] = card3;
		this.cards[4] = card4;

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();// init null//<cardnum,timesSeen>
		for (Card c : this.cards) {
			int tmpNum = c.getNum();
			if (map.containsKey(tmpNum)) {// weve got this add
				map.replace(tmpNum, map.get(tmpNum) + 1);// boost the val

			} else {
				map.put(tmpNum, 1);// put in in the map
			}
		}
		this.map = map;
	}

	/*
	 * Methods ----------------------------------------------------------------
	 */
	/**
	 * returns an int score based on the hand and updates private winCondition 
	 * so that if this player is the winner it will be easy to tell how he won.
	 *  uses intervals of 10, 1 , 0 thousand so that there is 2 digits between each win category then tacks on high card value to score
	 *  
	 * 
	 * @return a scored hand.
	 */
	public int evaluateHand() {
		if (this.hasStraightFlush()) {
			int k = (Collections.max(this.map.keySet()));
			this.score = STRAIGHT_FLUSH + (HIGH_CARD * k);
			this.winCondition = "Straight flush: high card " + toEnglish(k);

		} else if (this.hasFourOfAKind()) {
			int k = Collections.max(this.map.entrySet(), Map.Entry.comparingByValue()).getKey();
			this.score = (FOUR_KIND + (HIGH_CARD * k));
			this.winCondition = "four of a kind: " + toEnglish(k) + "'s";

		} else if (this.hasFullHouse()) {
			int k = Collections.max(this.map.entrySet(), Map.Entry.comparingByValue()).getKey();
			map.remove(k);
			int k2 = Collections.max(this.map.entrySet(), Map.Entry.comparingByValue()).getKey();
			this.score = (FULL_HOUSE+ (HIGH_CARD * k) + (SECOND_HIGH * k2));
			this.winCondition = "Full house: " + toEnglish(k) + " over " + toEnglish(k2);

		} else if (this.hasFlush()) {
			int k = (Collections.max(this.map.keySet()));
			this.score = (FLUSH + (HIGH_CARD * k));
			this.winCondition = "flush: highcard " + toEnglish(k);

		} else if (this.hasStraight()) {
			int k = (Collections.max(this.map.keySet()));
			this.score = (STRAIGHT + (HIGH_CARD * k));
			this.winCondition = "straight: high card " + toEnglish(k);

		} else if (this.hasThreeOfAKind()) {
			int k = Collections.max(this.map.entrySet(), Map.Entry.comparingByValue()).getKey();
			this.score = (THREE_KIND + (HIGH_CARD * k));
			this.winCondition = "three of a kind:" + toEnglish(k) + "'s";

		} else if (this.hasTwoPair()) {
			int k = Collections.max(this.map.entrySet(), Map.Entry.comparingByValue()).getKey();
			map.remove(k);
			int k2 = Collections.max(this.map.entrySet(), Map.Entry.comparingByValue()).getKey();
			map.remove(k);
			int k3 = Collections.max(this.map.entrySet(), Map.Entry.comparingByValue()).getKey();
			this.score = (TWO_PAIR + (HIGH_CARD * k) + (SECOND_HIGH * k2) + (LAST_HIGH * k3));
			this.winCondition = "two pair: " + toEnglish(k) + "'s and " + toEnglish(k2) + 's';

		} else if (this.hasPair()) {
			int k = Collections.max(this.map.entrySet(), Map.Entry.comparingByValue()).getKey();
			map.remove(k);
			int k2 = Collections.max(this.map.entrySet(), Map.Entry.comparingByValue()).getKey();
			map.remove(k);
			int k3 = Collections.max(this.map.entrySet(), Map.Entry.comparingByValue()).getKey();
			map.remove(k);
			int k4 = Collections.max(this.map.entrySet(), Map.Entry.comparingByValue()).getKey();
			this.score = (ONE_PAIR + (HIGH_CARD * k)+(SECOND_HIGH*k2)+(SECOND_HIGH*k3) +(LAST_HIGH*k4));
			this.winCondition = "one pair: " + toEnglish(k) + "'s";

		}
		int k = (Collections.max(this.map.keySet()));
		this.score += k;
		return this.score;
	}
/**
 * gives back the name of the player
 * @return name of player
 */
	public String name() {
		return this.name;
	}
/**
 * decides if n number of cards all share same suit
 * @param cards (any number of cards to check)
 * @return true if all suits match
 * false otherwise
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
/**decides weather n number of cards are of consecutive values
 * 
 * @param cards 
 * cards to check
 * @return true if cards are of consecutive value
 * false otherwise
 */
	private Boolean isStraight() {
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
 * decides weather the play hand has a straightFlush
 * @return true if true
 * false otherwise
 */
	private Boolean hasStraightFlush() {
		boolean tmp = false;
		// 5 cards of the same suit with consecutive values.
		// Ranked by the highest card in the hand.
		if (isStraight() && sameSuit()) {
			tmp = true;
		}

		return tmp;
	}
/**
 * gets the number of duplicate cards in the players hand
 *  from the constructor and returns and int based on the card with highest value in map
 * @return 
 * 1 if highest number no pairs,
 * 2 one pair
 * 3 if 3 of a kind
 * 5 if full house
 * 8 if 2 pair
 * 
 */
	private int numDuplicates() {
		Map<Integer, Integer> tmpMap = new HashMap<Integer, Integer>();
		tmpMap.putAll(this.map);// alliasing

		int ret = (Collections.max(tmpMap.values())); // the number of duplciates in the map
		tmpMap.remove(Collections.max(tmpMap.entrySet(), Map.Entry.comparingByValue()).getKey());// remove that old max
		int check = Collections.max(tmpMap.values());// now if this is 2 and the old max was 5 we had a full house
		if (check == 2 && ret == 3) {
			ret = FullHouseInHand;// this is not possible normally so this wont interfere with duplicates but it
					// is cheating
		} else if (check == 2 && ret == 2) {
			ret = TwoPairInHand;// this is not possible normally so this wont interfere with duplicates but it
					// is cheating
		}
		return (ret);
	}
/**
 * gives number of highest card among n cards
 * 
 * @param cards
 * @return highest cards number
 */
	private int highestCard() {
		Map<Integer, Integer> tmpMap = new HashMap<Integer, Integer>();// init null//
		for (Card c : this.cards) {
			int tmpNum = c.getNum();
			if (!tmpMap.containsKey(tmpNum)) {// weve got this add
				tmpMap.put(tmpNum, 1);// put in in the map
			}
		}
		int ret = (Collections.max(tmpMap.keySet())); // highest
		return (ret);
	}
/**
 * checks for a four of a kind
 * 
 * @return true if there is
 * false otherwise
 */
	private Boolean hasFourOfAKind() {
		boolean tmp = false;
		if (numDuplicates() == 4) {
			tmp = true;
		}
		return tmp;
	}
/**
 * checks hand for full house
 * 
 * @return true if present
 * false otherwise
 */
	private Boolean hasFullHouse() {
		boolean tmp = false;

		if (numDuplicates() == 5) {// duplicates will return 5 if their is duplciate 3
									// and duplicate 2
			tmp = true;
		}

		return tmp;
	}
	/**
	 * checks hand for flush
	 * 
	 * @return true if present
	 * false otherwise
	 */
	private Boolean hasFlush() {
		boolean tmp = false;
		if (sameSuit()) {
			tmp = true;
		}
		return tmp;
	}
	/**
	 * checks hand for straight
	 * 
	 * @return true if present
	 * false otherwise
	 */
	private Boolean hasStraight() {
		boolean tmp = false;
		if (isStraight()) {
			tmp = true;
		}
		return tmp;
	}
	/**
	 * checks hand for 3 of a kind
	 * 
	 * @return true if present
	 * false otherwise
	 */
	public Boolean hasThreeOfAKind() {
		boolean tmp = false;

		if (numDuplicates() == 3) {
			tmp = true;
		}

		return tmp;
	}
	/**
	 * checks two pair
	 * 
	 * @return true if present
	 * false otherwise
	 */
	public Boolean hasTwoPair() {
		boolean tmp = false;
		if (numDuplicates() == 8) {// duplicates will return 8 if their is duplciate 2 // and duplicate 2
			tmp = true;

		}

		return tmp;
	}
	/**
	 * checks for a pair
	 * 
	 * @return true if present
	 * false otherwise
	 */
	public Boolean hasPair() {
		boolean tmp = false;
		if (numDuplicates() == 2) {
			tmp = true;
		}

		return tmp;
	}
	/**
	 * turn all cards into a int in the billions to rank high
	 * 
	 * @return int value of highest cards number
	 * 
	 */
	public int highCard() {
		return highestCard();
	}

/**
 * returns a a win message along with the translated high card.
 * @return string with message for how this hand won
 */
	public String getWinCondition() {
		String temp = this.winCondition;
		if (temp.equals("")) {
			temp = "HighCard: " + toEnglish(this.highCard());
		}
		return temp;
	}
	/**
	 * 
	 * @param position 5-1 of card in hand
	 * @return number of card at nth position
	 */
	public int getCardNum(int n) {
		return this.cards[n].getNum();
	}
/**
 * turns characters 10-14 back into string for easy reading
 * @param k value of card
 * @return string pertaining to card value
 */
	public String toEnglish(int k) {
		String temp = "";
		switch (k) {

		case 2:
			temp = "2";
			break;
		case 3:
			temp = "3";
			break;
		case 4:
			temp = "4";
			break;
		case 5:
			temp = "5";
			break;
		case 6:
			temp = "6";
			break;
		case 7:
			temp = "7";
			break;
		case 8:
			temp = "8";
			break;
		case 9:
			temp = "9";
			break;
		case 10:
			temp = "10";
			break;
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
			temp = "woops, something is wrong";
			break;
		}

		return temp;
	}

}
