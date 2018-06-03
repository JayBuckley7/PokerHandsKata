package com.codingDojo.pokerHandKata;

import static org.junit.Assert.*;

import org.junit.Test;

import com.codingDojo.pokerHandKata.Player;
/**
 * JUnit test fixture for {@code Player}'s kernel methods.
 *
 * @author Jay Buckley
 *
 */
public class player1Test {

	@Test
	public void testInput() {
		Card one = new Card(2,"H");
    	Card two = new Card(3,"D");
    	Card three = new Card(5,"S");
    	Card four = new Card(9,"C");
    	Card five = new Card(13,"H");
    	
        Player jay = new Player("jay", one, two, three, four, five);
        int check = jay.evaluateHand();        /*
         * Assert that values of variables match expectations
         */
        assertEquals(check, 13);
	}
	@Test
	public void testStraightFlush() {
		Card one = new Card(1,"C");
    	Card two = new Card(2,"C");
    	Card three = new Card(3,"C");
    	Card four = new Card(4,"C");
    	Card five = new Card(5,"C");
    	
        Player jay = new Player("jay", one, two, three, four, five);
        int check = jay.evaluateHand();        /*
         * Assert that values of variables match expectations
         */
        assertEquals(check, 9050005);
	}
	@Test
	public void test4Kind() {
		Card one = new Card(4,"C");
    	Card two = new Card(4,"C");
    	Card three = new Card(4,"C");
    	Card four = new Card(4,"C");
    	Card five = new Card(6,"C");
    	
        Player jay = new Player("jay", one, two, three, four, five);
        int check = jay.evaluateHand();        /*
         * Assert that values of variables match expectations
         */
        assertEquals(check, 8004006);
	}
	
@Test
	public void testFullHouse() {
		Card one = new Card(13,"A");
    	Card two = new Card(13,"B");
    	Card three = new Card(13,"C");
    	Card four = new Card(14,"D");
    	Card five = new Card(14,"E");
    	
        Player jay = new Player("jay", one, two, three, four, five);
        int check = jay.evaluateHand();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(check, 7027014);
	}
	
	@Test
	public void isFlush() {
		Card one = new Card(4,"C");
    	Card two = new Card(4,"C");
    	Card three = new Card(11,"C");
    	Card four = new Card(2,"C");
    	Card five = new Card(3,"C");
    	
        Player jay = new Player("jay", one, two, three, four, five);
        int check = jay.evaluateHand();        /*
         * Assert that values of variables match expectations
         */
        assertEquals(check,6110011 );
	}

	@Test
	public void hasStraight() {
		Card one = new Card(1,"A");
    	Card two = new Card(2,"C");
    	Card three = new Card(3,"C");
    	Card four = new Card(4,"C");
    	Card five = new Card(5,"C");
    	
        Player jay = new Player("jay", one, two, three, four, five);
        int check = jay.evaluateHand();        /*
         * Assert that values of variables match expectations
         */
        assertEquals(check, 5050005);
	}
	

	@Test
	public void test3Kind() {
		Card one = new Card(4,"A");
    	Card two = new Card(4,"B");
    	Card three = new Card(4,"C");
    	Card four = new Card(1,"D");
    	Card five = new Card(6,"E");
    	
        Player jay = new Player("jay", one, two, three, four, five);
        int check = jay.evaluateHand();        /*
         * Assert that values of variables match expectations
         */
        assertEquals(check, 4040006);
	
	
	}
	@Test
	public void test2Pair() {
		Card one = new Card(4,"A");
    	Card two = new Card(4,"B");
    	Card three = new Card(6,"C");
    	Card four = new Card(6,"D");
    	Card five = new Card(3,"E");
    	
        Player jay = new Player("jay", one, two, three, four, five);
        int check = jay.evaluateHand();        /*
         * Assert that values of variables match expectations
         */
        assertEquals(check, 3040666);
	}

	@Test
	public void testPair() {
		Card one = new Card(4,"A");
    	Card two = new Card(4,"B");
    	Card three = new Card(5,"C");
    	Card four = new Card(6,"D");
    	Card five = new Card(3,"E");
    	
        Player jay = new Player("jay", one, two, three, four, five);
        int check = jay.evaluateHand();        /*
         * Assert that values of variables match expectations
         */
        assertEquals(check, 2043336);
	}

	@Test
	public void testHighCard() {
		Card one = new Card(3,"A");
    	Card two = new Card(4,"C");
    	Card three = new Card(5,"C");
    	Card four = new Card(10,"C");
    	Card five = new Card(11,"C");
    	
        Player jay = new Player("jay", one, two, three, four, five);
        int check = jay.evaluateHand();        /*
         * Assert that values of variables match expectations
         */
        assertEquals(check, 11);
	}
}

