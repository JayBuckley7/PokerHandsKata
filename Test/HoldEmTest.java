package com.codingDojo.pokerHandKata;
/**
 * JUnit test fixture for {@code HoldEm}'s kernal methods.
 *
 * @author Jay Buckley
 *
 */
import static org.junit.Assert.*;

import org.junit.Test;

import com.codingDojo.pokerHandKata.HoldEm;

public class HoldEmTest {

	@Test
	public void conventionHoldsPlayer() {
		String[] player1 = new String[6];
		String[] player2 = new String[6];
		player1[0] = "black";
		player1[1] = "AA";
		player1[2] = "AB";
		player1[3] = "AC";
		player1[4] = "AD";
		player1[5] = "AE";
		///////////////////
		player2[0] = "white";
		player2[1] = "F";
		player2[2] = "AG";
		player2[3] = "AI";
		player2[4] = "AH";
		player2[5] = "AJ";
		boolean chk = HoldEm.conventionHolds(player1,player2);
		assertEquals(chk, true);
		
		
	}

}
