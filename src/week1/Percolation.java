package week1;

import algs4.QuickFindUF;

/**
 * 
 * @author Martin Charlesworth
 *
 */
public class Percolation {
	
	private final boolean[] grid;
	private QuickFindUF uf;
	final int N;

	public Percolation(int N) {               // create N-by-N grid, with all sites blocked
		this.N = N;
		if (N <= 1) {
			throw new IndexOutOfBoundsException("N must be > 1");
		}
		this.grid = new boolean[N*N];
        uf = new QuickFindUF(N*N);
	}
	
	public void open(int i, int j) {          // open site (row i, column j) if it is not already
		rangeCheck(i,j);
		int p = indexOf(i,j);
		if (grid[p] == false) {
			grid[p] = true;
		}
		int[] neighbours = neighboursOf(i, j);
		for (int k=0; k<neighbours.length; ++k) {
			int q = neighbours[k];
			if (q != -1 && grid[q] == true) {
				uf.union(p, q);
			}
		}
	}
	
	private int[] neighboursOf(int i, int j) {
		int[] n = new int[4];
		n[0] = inBounds(i, j-1) ? indexOf(i,j-1) : -1;
		n[1] = inBounds(i+1, j) ? indexOf(i+1,j) : -1;
		n[2] = inBounds(i, j+1) ? indexOf(i,j+1) : -1;
		n[3] = inBounds(i-1, j) ? indexOf(i-1,j) : -1;
		return n;
	}
	
	private int indexOf(int i, int j) {
		return (i-1) + (j-1)*N;
	}
	
	public boolean isOpen(int i, int j) {      // is site (row i, column j) open?
		rangeCheck(i,j);
		return grid[indexOf(i,j)];
	}
	
	public boolean isFull(int i, int j) {     // is site (row i, column j) full?
		return !isOpen(i, j);
	}
	
	public boolean percolates() {              // does the system percolate?
		for (int j=1; j<=N; ++j) {
			if (isOpen(1, j)) {
				for (int j1=1; j1<=N; ++j1) {
					if (isOpen(N, j1)) {
						int p = indexOf(1, j);
						int q = indexOf(N, j1);
						if (uf.connected(p, q))
							return true;
					}
				}
			}
		}
		return false;
	}
	
	private void rangeCheck(int i, int j) throws IndexOutOfBoundsException {
		if (!inBounds(i, j)) {
			throw new IndexOutOfBoundsException("Coordinates out of range!");
		}
	}

	private boolean inBounds(int i, int j) {
		return (i >= 1 && i <= N && j >= 1 && j <= N);
	}
	


}
