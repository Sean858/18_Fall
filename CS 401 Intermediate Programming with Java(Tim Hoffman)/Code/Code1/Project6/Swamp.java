import java.io.*;
import java.util.*;

// DO NOT!! IMPORT JAVA.LANG

public class Swamp
{
	static int[][] swamp;  // NOW YOU DON'T HAVE PASS THE REF IN/OUT METHODS

 	public static void main(String[] args) throws Exception
	{
		int[] dropInPt = new int[2]; // row and col will be on the 2nd line of input file;
		swamp = loadSwamp( args[0], dropInPt );
		int row=dropInPt[0], col = dropInPt[1];
		String path = ""; // with each step grows to => "[2,3][3,4][3,5][4,6]" etc
		dfs( row, col, path );
	} // END MAIN
	
	// --YOU-- WRITE THIS METHOD (LOOK AT PRINTSWAMP FOR CLUES)
   	// ----------------------------------------------------------------
	private static int[][] loadSwamp( String infileName, int[] dropInPt  ) throws Exception
	{
		Scanner infile = new Scanner(new File(infileName));
		int n = Integer.parseInt(infile.next());
		int[][] matrix = new int[n][n];
		dropInPt[0] = Integer.parseInt(infile.next());
		dropInPt[1] = Integer.parseInt(infile.next());
		for(int row = 0; row < matrix.length; row++) {
			for(int col = 0; col < matrix[row].length; col++) {
				matrix[row][col] = Integer.parseInt(infile.next());
			}
		}
		infile.close();
		return matrix;  // JUST TO MAKE IT COMPILE
	}

	static void dfs( int row, int col, String path ) // dfs = DEPTH FIRST SEARCH
	{
		// IMPLEMENT THE DFS ALGORITHM IN HERE
		path += "[" + row + "," + col + "]";
		if(row == 0 || row == swamp.length - 1 || col == 0 || col == swamp.length - 1) {
			System.out.println(path);
			path = "";
			return;
		}
		swamp[row][col] = -1;
		if(swamp[row - 1][col] == 1) {
			dfs(row - 1, col, path);
		}
		if(swamp[row - 1][col + 1] == 1) {
			dfs(row - 1 , col + 1, path);
		}
		if(swamp[row][col + 1] == 1) {
			dfs(row , col + 1, path);
		}
		if(swamp[row + 1][col + 1] == 1) {
			dfs(row + 1, col + 1, path);
		}
		if(swamp[row + 1][col] == 1) {
			dfs(row + 1, col, path);
		}
		if(swamp[row + 1][col - 1] == 1) {
			dfs(row + 1, col - 1, path);
		}
		if(swamp[row][col - 1] == 1) {
			dfs(row , col - 1, path);
		}
		if(swamp[row - 1][col - 1] == 1) {
			dfs(row - 1, col - 1, path);
		}
		swamp[row][col] = 1;
		return;
	}	
}
