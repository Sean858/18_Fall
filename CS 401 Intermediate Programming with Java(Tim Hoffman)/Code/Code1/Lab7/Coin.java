/*
	Coin.java THIS IS THE ONLY FILE YOU HAND IN
	THERE IS NO MAIN METHOD IN THIS CLASS!
*/
import java.util.Random;
public class Coin
{

	private int numHeads;
	private int numTails;
	private int seed;
	private Random generate;

	public Coin() {
		this.seed = 0;
		this.numHeads = 0;
		this.numTails = 0;
	}

	public Coin(int n) {
		this.seed = n;
		this.generate = new Random(seed);
		this.numHeads = 0;
		this.numTails = 0;	
	}

	public void reset() {
		setNumHeads(0);
		setNumTails(0);
	}

	public void setNumHeads(int n) {
		this.numHeads = n;
	}

	public void setNumTails(int n) {
		this.numTails = n;
	}

	public int getNumHeads() {
		return numHeads;
	}

	public int getNumTails() {
		return numTails;
	}

	public String flip() {
		boolean b = generate.nextBoolean();
		if (b) {
			numHeads++;
			return "H";
		}
		else {
			numTails++;
			return "T";
		}
	}

} // END COIN CLASS