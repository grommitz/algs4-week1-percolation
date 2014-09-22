import static java.lang.Math.*;

/**
 * 
 * @author Martin Charlesworth
 *
 */
public class PercolationStats {

	private final double[] results;
	private final int T;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
		if (N<=0 || T<=0) {
			throw new IllegalArgumentException("Bad N or T");
		}
		this.T = T;
		results = new double[T];
		for (int t=0; t<T; ++t) {
			results[t] = runSimulation(N);
		}
	}

	public double mean() {
		return StdStats.mean(results);
	}

	public double stddev() {
		return StdStats.stddev(results);
	}

	// returns lower bound of the 95% confidence interval
	public double confidenceLo() {
		return mean() - (1.96 * stddev() / sqrt(T));
	}

	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {             
		return mean() + (1.96 * stddev() / sqrt(T));
	}

	private double runSimulation(int N) {
		Percolation perc = new Percolation(N);
		int openSites=0;

		while (!perc.percolates()) {
			openASite(perc);
			if (++openSites>6000) {
				perc.percolates();
			}
		}
		return (double)openSites/(N*N);
	}

	private void openASite(Percolation perc) {
		boolean opened = false;
		do {
			int i = StdRandom.uniform(perc.N)+1;
			int j = StdRandom.uniform(perc.N)+1;
			if (!perc.isOpen(i, j)) {
				perc.open(i,j);
				opened = true;
			}
		} while (!opened);
	}

	/**
	 * test client
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Usage: PercolationStats N T");
			return;
		}
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		System.out.println(String.format("For N = %d and T = %d...", N, T));
		Stopwatch stopwatch = new Stopwatch();
		PercolationStats perc = new PercolationStats(N, T);
		double secs = stopwatch.elapsedTime();
		System.out.println(String.format("%-30s = %f", "mean", perc.mean()));
		System.out.println(String.format("%-30s = %f", "stddev", perc.stddev()));
		System.out.println(String.format("%-30s = %f %f", "95% confidence interval", perc.confidenceLo(), perc.confidenceHi()));
		System.out.println(String.format("%-30s = %f", "running time (seconds)", secs));
	}

}


