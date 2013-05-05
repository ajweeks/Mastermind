import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mastermind {
	
	/*
	 * Mastermind is a code-breaking game where one player chooses four coloured pegs and the other player(s) must guess all four peg's colour and position in
	 * 12 guesses or less. The person who chooses the colours must tell the guesser how many pegs they guessed correctly in both colour and position, and how
	 * many pegs were the correct colour, but in the wrong place.
	 * 
	 * For example:
	 * 
	 * Four correct colours: (R) (O) (Y) (G)
	 * 
	 * My guess: (O) (B) (W) (G) I guessed one peg which is both the right colour and place, and one is the correct colour, but in the wrong place.
	 * 
	 * In this version, the computer will always chose the four colours and the player will always be the "guesser".
	 * 
	 * In the example above, the computer's output would be
	 * 
	 * correct colour, wrong place: 1
	 * 
	 * correct colour, right place: 1
	 * 
	 * wrong colour: 2
	 */
	
	private static final int VERSION = 1;
	private static final String VERSION_STRING = "Mastermind version " + VERSION;
	private static final int MAX_ROUNDS = 12;
	
	private static int round;
	
	private static final char RED = 'R', ORANGE = 'O', YELLOW = 'Y', GREEN = 'G', VIOLET = 'V', PINK = 'P',
			BLACK = 'B', WHITE = 'W';
	
	private static char[] chrAnswer = { ' ', ' ', ' ', ' ' }; // Four character array that holds four colour letters ex. 'R', 'O', 'Y', 'G'
	private static int[] intAnswer = { 0, 0, 0, 0 }; // Holds four integers that correspond to four colours ex. 1, 5, 0, 4
	private static int[] intCurrentGuess = { 0, 0, 0, 0 };
	
	private static int cC; // Correct colour, Correct pos
	private static int cI; // Correct colour, Incorrect pos
	private static int iI; // Incorrect colour
	// cR + cW + wW should = 4
	
	private static boolean gameOver = false;
	private static int playerScore;
	
	public static void main(String[] args) {
		System.out.println(VERSION_STRING);
		try {
			startGameLoop();
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
		}
	}
	
	private static void startGameLoop() {
		playerScore = 0;
		
		// Generate Answer
		generateAnswer();
		toChar(intAnswer);
		System.out.println(chrAnswer); // ~~~~~~~~~~~~~TEMP~~~~~~~~~~~~~
		
		while (gameOver != true) {
			round++;
			System.out.println("Round: " + round);
			
			// Get and check input
			getInput();
			
			// Return feedback on guess
			
			// Check for a win
			checkWin();
			
			if (round == MAX_ROUNDS) gameOver = true;
			
		}
		System.out.println("Rounds: " + round);
		System.out.println("Your score: " + playerScore);
	}
	
	private static void getInput() {
		System.out.println("Enter four different numbers between 1 and 8: ");
		for (int i = 0; i < 4; i++) {
			intCurrentGuess[i] = returnInput();
			if (!isInputValid(i)) {
				System.out.println("Invalid input. (Not in range)");
				getInput(i); // Just get this number again
				i--; // Re-iterate until we have valid input
			} else {
				System.out.println("Valid input.");
			}
		}
	}
	
	private static void getInput(int i) { // For just getting one new number
		System.out.println("Please re-enter guess number " + (i + 1));
	}
	
	private static int returnInput() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			return Integer.parseInt(reader.readLine());
		} catch (IOException ioe) {
			throw new RuntimeException("An I/O error occured.", ioe);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException("User input an invalid number.", nfe);
		}
	}
	
	private static boolean isInputValid(int i) {
		return intCurrentGuess[i] <= 8 && intCurrentGuess[i] >= 1 ? true : false; // Guess must be between 1 and 8
	}
	
	private static void generateAnswer() {
		for (int i = 0; i < 4; i++) {
			intAnswer[i] = (int) (1 + (Math.random() * 8)); // Random number between 1 and 8
		}
		checkAnswer(); // Check if we have four different numbers
	}
	
	private static void checkAnswer() {
		for (int i = 0; i < 4; i++) {
			if (i != 0 && intAnswer[0] == intAnswer[i]) reGenerateAnswer(0);
			if (i != 1 && intAnswer[1] == intAnswer[i]) reGenerateAnswer(1);
			if (i != 2 && intAnswer[2] == intAnswer[i]) reGenerateAnswer(2);
			if (i != 3 && intAnswer[3] == intAnswer[i]) reGenerateAnswer(3);
		}
	}
	
	private static void reGenerateAnswer(int i) {
		intAnswer[i] = (int) (1 + (Math.random() * 8)); // Generate one random number between 1 and 8
		checkAnswer();
	}
	
	private static void toChar(int[] intAnswer) {
		for (int i = 0; i < 4; i++) {
			switch (intAnswer[i]) {
			case 1:
				chrAnswer[i] = 'R';
				break;
			case 2:
				chrAnswer[i] = 'O';
				break;
			case 3:
				chrAnswer[i] = 'Y';
				break;
			case 4:
				chrAnswer[i] = 'G';
				break;
			case 5:
				chrAnswer[i] = 'V';
				break;
			case 6:
				chrAnswer[i] = 'P';
				break;
			case 7:
				chrAnswer[i] = 'B';
				break;
			case 8:
				chrAnswer[i] = 'W';
				break;
			}
		}
	}
	
	private static void checkWin() {
		if (intAnswer == intCurrentGuess) {
			gameOver = true;
		}
	}
	
}