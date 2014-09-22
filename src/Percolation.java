/**
 * 
 * @author Martin Charlesworth
 *
 */
public class Percolation {
	
	private final boolean[] grid;
	private final WeightedQuickUnionUF uf;
	private final int N;
	private final int initial;
	private final int terminal;

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		if (N <= 1) {
			throw new IllegalArgumentException("N must be > 1");
		}
		this.N = N;
		this.grid = new boolean[N*N];
		uf = new WeightedQuickUnionUF(N*N + 2);
		initial = N*N;
		terminal = N*N+1;
	}

	// open site (row i, column j) if it is not already
	public void open(int i, int j) {
		rangeCheck(i, j);
		int p = indexOf(i, j);
		if (grid[p] == false) {
			grid[p] = true;
		}
		unionWithOpenNeighbours(i, j, p);
	}

	public boolean isOpen(int i, int j) {      // is site (row i, column j) open?
		rangeCheck(i, j);
		return grid[indexOf(i, j)] == true;
	}

	public boolean isFull(int i, int j) {     // is site (row i, column j) full?
		rangeCheck(i, j);
		return grid[indexOf(i, j)] == false;
	}

	public boolean percolates() {              // does the system percolate?
		return uf.connected(initial, terminal);
	}

	private void unionWithOpenNeighbours(int i, int j, int p) {
		int[] neighbours = neighboursOf(i, j);
		for (int k = 0; k < neighbours.length; ++k) {
			int q = neighbours[k];
			if (q != -1 && grid[q] == true) {
				uf.union(p, q);
			}
		}
		if (i == 1) {
			uf.union(p, initial);
		} else if (i == N) {
			uf.union(p, terminal);
		}
	}

	private void rangeCheck(int i, int j) {
		if (!inBounds(i, j)) {
			throw new IndexOutOfBoundsException("Coordinates out of range!");
		}
	}

	private boolean inBounds(int i, int j) {
		return (i >= 1 && i <= N && j >= 1 && j <= N);
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

}
