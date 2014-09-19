package algs4;

import java.util.Random;
import QuickFindUF;

public class Percolation {
	
	private final boolean[][] grid;
	
	
	public Percolation(int N) {               // create N-by-N grid, with all sites blocked
		if (N <= 1) {
			throw new IndexOutOfBoundsException("N must be > 1");
		}
		this.grid = new boolean[N][N];
	}
	
	public void open(int i, int j) {          // open site (row i, column j) if it is not already
		check(i,j);
		if (grid[i][j] == false) {
			grid[i][j] = true;
		}
	}
	
	public boolean isOpen(int i, int j) {      // is site (row i, column j) open?
		check(i,j);
		return grid[i][j];
	}
	
	public boolean isFull(int i, int j) {     // is site (row i, column j) full?
		check(i,j);
		
	}
	
	public boolean percolates() {              // does the system percolate?

	}
	
	public static void main(String[] args) {   // test client, optional
		int pass=0, fail=0;
		for (int n=0; n<10; ++n) {
			double result = runSimulation();
			System.out.println("Percolates at "+result);
		}
		System.out.println("Percolated "+(pass*100/(pass+fail))+"% of runs");
	}
	
	static final int N = 100;
	
	private static double runSimulation() {
		Percolation perc = new Percolation(N);
		Random rand = new Random();
		int openSites=0;

		while (!perc.percolates()) {
			boolean opened = false;
			do {
				int i = rand.nextInt(N);
				int j = rand.nextInt(N);
				if (!perc.isOpen(i, j)) {
					perc.open(i,j);
				}
			} while (!opened);
			++openSites;
		}
		return (double)openSites/(N^2);
	}

	private void check(int i, int j) throws IndexOutOfBoundsException {
		if (i < 0 || i >= N || j < 0 || j >= N) {
			throw new IndexOutOfBoundsException();
		}
	}

}
