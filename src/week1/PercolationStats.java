package week1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class PercolationStats {
	
	public PercolationStats(int N, int T) {   // perform T independent computational experiments on an N-by-N grid
		if (N<=0 || T<=0) {
			throw new IllegalArgumentException("Bad N or T");
		}
		double sum = 0.0;
		for (int t=0; t<T; ++t) {
			Stopwatch stopwatch = Stopwatch.createStarted();
			double result = runSimulation(N);
			long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
			System.out.println("Percolates at "+result+", runtime = " + millis + " ms");
			sum += result;
		}
		System.out.println("Avg p = "+(sum/T));
	}
	
	public double mean() {                     // sample mean of percolation threshold
		return 0.0;
	}
	
	public double stddev() {                   // sample standard deviation of percolation threshold
		return 0.0;
	}
	
	public double confidenceLo() {             // returns lower bound of the 95% confidence interval
		return 0.0;
	}
	
	public double confidenceHi() {             // returns upper bound of the 95% confidence interval
		return 0.0;
	}
	
	public static void main(String[] args) {   // test client, described below
		if (args.length != 2) {
			System.err.println("Usage: PercolationStats N T");
			return;
		}
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		new PercolationStats(N, T);
	}

	private static Random rand = new Random();

	private static double runSimulation(int N) {
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

	private static void openASite(Percolation perc) {
		boolean opened = false;
		do {
			int i = rand.nextInt(perc.N)+1;
			int j = rand.nextInt(perc.N)+1;
			if (!perc.isOpen(i, j)) {
				perc.open(i,j);
				opened = true;
			}
		} while (!opened);
	}

}


