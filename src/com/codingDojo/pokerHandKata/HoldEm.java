package com.codingDojo.pokerHandKata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import junit.framework.AssertionFailedError;

/**
 * Scores poker hands based on syntactical txt input files.
 * displays winners name and victory method in console.
 * 
 * @author Jay Buckley
 */
public final class HoldEm {
	public static final String SEPARATORS = " \t\n\r:";
	static String[] player1 = new String[6];
	static String[] player2 = new String[6];
	public static int lineCounter = 1;

	/**
	 * Private constructor.
	 */
	private HoldEm() {
		// empty constructors use java's default
	}

	/**
	 * Returns the first "word" (maximal length string of characters not in
	 * {@code separators}) or "separator string" (maximal length string of
	 * characters in {@code separators}) in the given {@code text} starting at the
	 * given {@code position}.
	 *
	 * @param text
	 *            the {@code String} from which to get the word or separator string
	 * @param position
	 *            the starting index
	 * @param separators
	 *            the {@code Set} of separator characters
	 * @return the first word or separator string found in {@code text} starting at
	 *         index {@code position}
	 * @requires
	 *
	 *           <pre>
	 * {@code 0 <= position < |text|}
	 * 			</pre>
	 *
	 * @ensures
	 *
	 *          <pre>
	 * {@code nextWordOrSeparator =
	 *   text[position, position + |nextWordOrSeparator|)  and
	 * if entries(text[position, position + 1)) intersection separators = {}
	 * then
	 *   entries(nextWordOrSeparator) intersection separators = {}  and
	 *   (position + |nextWordOrSeparator| = |text|  or
	 *    entries(text[position, position + |nextWordOrSeparator| + 1))
	 *      intersection separators /= {})
	 * else
	 *   entries(nextWordOrSeparator) is subset of separators  and
	 *   (position + |nextWordOrSeparator| = |text|  or
	 *    entries(text[position, position + |nextWordOrSeparator| + 1))
	 *      is not subset of separators)}
	 *          </pre>
	 * 
	 *          /** Returns the first "word" (maximal length string of characters
	 *          not in {@code separators}) or "separator string" (maximal length
	 *          string of characters in {@code separators}) in the given
	 *          {@code text} starting at the given {@code position}.
	 *
	 * @param text
	 *            the {@code String} from which to get the word or separator string
	 * @param position
	 *            the starting index
	 * @param separators
	 *            the {@code Set} of separator characters
	 * @return the first word or separator string found in {@code text} starting at
	 *         index {@code position}
	 * @requires
	 * 
	 *           <pre>
	 * {@code 0 <= position < |text|}
	 * 			</pre>
	 * 
	 * @ensures
	 * 
	 *          <pre>
	 * {@code nextWordOrSeparator =
	 *   text[ position .. position + |nextWordOrSeparator| )  and
	 * if elements(text[ position .. position + 1 )) intersection separators = {}
	 * then
	 *   elements(nextWordOrSeparator) intersection separators = {}  and
	 *   (position + |nextWordOrSeparator| = |text|  or
	 *    elements(text[ position .. position + |nextWordOrSeparator| + 1 ))
	 *      intersection separators /= {})
	 * else
	 *   elements(nextWordOrSeparator) is subset of separators  and
	 *   (position + |nextWordOrSeparator| = |text|  or
	 *    elements(text[ position .. position + |nextWordOrSeparator| + 1 ))
	 *      is not subset of separators)}
	 *          </pre>
	 */

	private static String nextWordOrSeparator(String text, int position) {
		assert text != null : "Violation of: text is not null";
		assert 0 <= position : "Violation of: 0 <= position";
		assert position < text.length() : "Violation of: position < |text|";

		Set<Character> separatorSet = new HashSet<Character>();

		for (char c : SEPARATORS.toCharArray()) {
			separatorSet.add(c);
		}
		StringBuilder sb = new StringBuilder();
		int count = position;
		char slice = text.charAt(position);// take the first letter as a "slice"
		char nxtSlice = ' ';
		if (count != text.length() - 1) {
			nxtSlice = text.charAt(position + 1);
		}

		boolean isSep = separatorSet.contains(slice);
		boolean nxtSep = separatorSet.contains(nxtSlice);

		while (((isSep && nxtSep) || (!isSep && !nxtSep)) && (count < text.length() - 1)) {// yeah, i know i over
																							// complicated it

			sb.append(slice);
			count++;

			slice = text.charAt(count);// take the first letter as a "slice"
			if (count != text.length() - 1) {
				nxtSlice = text.charAt(count + 1);
			}
			isSep = separatorSet.contains(slice);
			nxtSep = separatorSet.contains(nxtSlice);

		}
		sb.append(slice);
		return sb.toString();

	}

	/**	 
	 * populates arrays of name:card,card,card,card,card
	 * for player 1 and 2 for line n
	 *
	 * @param in
	 *            the name of the file to read from
	 * @param line
	 * 			   name of the line to read names and cards from
	 * @ensures
	 * 			static variable Player1[] and player2[] are  string arrays of size 6	
	 */
	private static void ParseLine(BufferedReader in, String line) {
		try {
			assert in.ready() : "Violation of: out.is_open";
		} catch (IOException e) {
			e.printStackTrace();
		}

		int position = 0;
		
		try {

			for (int i = 0; i < 6; i++) {
				String token = nextWordOrSeparator(line, position);
				if (!SEPARATORS.contains(String.valueOf(token.charAt(0)))) {
					player1[i] = token;
					if(i==0) {
						char x = line.charAt(token.length());
						if(!(":").equals(""+x)) {
							throw new AssertionFailedError("expected token (:) after playerName1 found: ("+ x +") on line "+ lineCounter +".");
						}
					}else {
						if(token.length()!=2) {
							throw new AssertionFailedError("invalid card "+token+"  on line "+ lineCounter +". try name: #x #x #x #x #x name: #x #x #x #x #x");
							
						}
					}
				} else {
					i--;
				}
				position += token.length();// move pos to the end of the word

			}
		} catch (IndexOutOfBoundsException e) {
			throw new AssertionFailedError("there must be be 6 tokens in each hand. try name: #x #x #x #x #x name: #x #x #x #x #x");
		}
		try {
			for (int i = 0; i < 6; i++) {
				String token = nextWordOrSeparator(line, position);
				if (!SEPARATORS.contains(String.valueOf(token.charAt(0)))) {
					player2[i] = token;
					if(i==0) {
						char x = line.charAt(token.length()+position);
						if(!(":").equals(""+x)) {
							throw new AssertionFailedError("expected token (:) after playerName1 found: ("+ x +") on line "+ lineCounter +"." );
						}
					}else {
						if(token.length()!=2) {
							throw new AssertionFailedError("invalid card "+token+"  on line "+ lineCounter +". try name: #x #x #x #x #x name: #x #x #x #x #x");
							
						}
					}
				} else {
					i--;
				}
				position += token.length();// move pos to the end of the word

			}

		} catch (IndexOutOfBoundsException e) {
			throw new AssertionFailedError("there must be be 6 tokens in each hand. try name: #x #x #x #x #x name: #x #x #x #x #x");
		}
		if (!conventionHolds(player1, player2)) {
			throw new AssertionFailedError("Cards may not be used multiple time at input line "+ lineCounter +".");
		}
		
		lineCounter++;
	}

	/**	 
	 * checks to insure that there are no cards being used multiple times in a round.
	 *
	 * @param player1
	 *            the name the first hand to look through
	 * @param line
	 * 			   the name the first hand to look through
	 * @return
	 * 			false if the length of the set of each x in player1[]+player2[] != 12
	 * 			true otherwise	
	 */
	static boolean conventionHolds(String[] player1, String[] player2) {
		boolean status = true;
		Set<String> assertions = new HashSet<String>();
		for(String s : player1) {
			if(!assertions.contains(s)) {
				assertions.add(s);
			}else {
				status=false;
			}
			
		}
		for(String s : player2) {
			if(!assertions.contains(s)) {
				assertions.add(s);
			}else {
				status=false;
			}
		}
		return status;
	}
	
	/**	 
	 * checks to insure that a Suit acronymn is valid
	 *
	 * @param c
	 *        suit to check    
	 * @return
	 * 			false c is not C/D/H/S
	 * 			true otherwise	
	 */
	static boolean conventionHolds(char c) {
		boolean status = true;

		switch (c) {
		case 'S':
			break;
		case 'H':
			break;
		case 'D':
			break;
		case 'C':
			break;
		default:
			status=false;
			break;
		}
		return status;
	}
	
	/**	 
	 * changes Card number letters into numbers for math purposes
	 *
	 * @param c
	 *        card to check    
	 * @return
	 * 			integer value of c as a card
	 */
	private static int asInt(char c) {
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
			throw new AssertionFailedError("Invalid Character "+ c + " in card number column: Expected 1-9,J,Q,K,A");
			
		}
		return temp;
	}
	
	/**	 
	 * creates Player and card objects out of valid arrays
	 *
	 * @param player
	 * array to pull player and card data from.   
	 * @return
	 * 			returns a player with cards and helpful math functions
	 */
	private static Player createPlayer(String[] player) {
		Card[] cards = new Card[5];
		
		
		for (int i = 0; i < cards.length; i++) {
			char c = player[i + 1].charAt(0);
			char check = player[i + 1].charAt(1);
			if(!conventionHolds(check))
			{
				throw new AssertionFailedError("Invalid Character "+ check + " in card suit column: Expected H,D,S,C - at line: "+ (lineCounter-1));
			}
				
				
			String c2 = "" + player[i + 1].charAt(1);
			cards[i] = new Card(asInt(c), c2);
			
			
			
		}
		Arrays.sort(cards);// puts cards in low to high order

		Player p = new Player(player[0], cards[0], cards[1], cards[2], cards[3], cards[4]);
		return p;

	}

	/**	 
	 * takes 2 valid players, scores their hands,determines winner 
	 * and displays appropriate victory message
	 *
	 * @param p1
	 * 	first player to check   
	 *  @param p2
	 * 	second player to check 
	 * @ensures
	 * 			a console message is displayed with the winner and victory method
	 */
	private static void scoreHands(Player p1, Player p2) {
		Player tmp = p2;
		int tieBreaker = 0;
		String winner = p2.name();
		String name1 = p1.name();

		int score1 = p1.evaluateHand();
		int score2 = p2.evaluateHand();

		if (score1 == score2) {
			int i=4;
			while(i!=0) {
				score1 = p1.getCardNum(i);
				score2 = p2.getCardNum(i);				
				if(score1!=score2) {
					if(score1>score2) {
						winner=name1;
						tieBreaker = score1;
					}
					else {
						tieBreaker = score2;
					}
					winner = winner +" wins. - with HighCard: " + p1.toEnglish(tieBreaker);
					i=0; score1=score2;
				}else{
					winner = "Tie.";
					i--;
				}
				
			}	
		}

		
		if (score1 != score2) {
			if (score1 > score2) {
				winner = name1;
				tmp = p1;
			}
			winner = (winner + " wins. - with " + tmp.getWinCondition());
		}

		System.out.println(winner);
	}
    
	/**
     * Main method.
     *
     * @param args
     *            can be file name
     */
	public static void main(String[] args) {

		/*
		 * Take input file.
		 */
		 System.out.print("Enter the path for the input file: ");

		Scanner in = new Scanner(System.in);
		String inputFileName = in.nextLine();
		/*
		 * Name output file.
		 */
		BufferedReader file;
		try {
			file = new BufferedReader(new FileReader(inputFileName));
			String line = file.readLine();
			while(line.equals("")) {
				line = file.readLine();
			}
			while (line != null) {// until end of stream
				ParseLine(file, line);// write hands from line to arrays
				Player p1 = createPlayer(player1);
				Player p2 = createPlayer(player2);
				scoreHands(p1, p2);
				
				line = file.readLine();
				
			}
			file.close();

		} catch (IOException e) {
			System.err.println("Error reading file");
			in.close();
			return;
		}

		in.close();
	}

}