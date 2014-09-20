package week1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import stdlib.Stopwatch;

public class PercolationChart extends Application {

	// chart data, has to be static
	static int[] Ns;
	static double[] runtimes;

	/**
	 * test client
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: PercolationStats X (where X is the number of times to double N)");
			return;
		}
		int doublings = Integer.parseInt(args[0]);
		Ns = new int[doublings];
		runtimes = new double[doublings];
		new PercolationChart().run(doublings);
		launch();
	}

	private void run(int doublings) {
		int T = 100;
		int N = 50;
		for (int i=0; i < doublings; ++i) {
			System.out.println(String.format("For N = %d and T = %d...", N, T));
			Stopwatch stopwatch = new Stopwatch();
			PercolationStats perc = new PercolationStats(N, T);
			double secs = stopwatch.elapsedTime();
			System.out.println(String.format("%-30s = %f", "mean", perc.mean()));
			System.out.println(String.format("%-30s = %f", "stddev", perc.stddev()));
			System.out.println(String.format("%-30s = %f %f", "95% confidence interval", perc.confidenceLo(), perc.confidenceHi()));
			System.out.println(String.format("%-30s = %f", "running time (seconds)", secs));
			Ns[i] = N;
			runtimes[i] = secs;
			N = N * 2; 
		}
	}

	private XYChart.Series generateSeries(int[] Ns, double[] runtimes) {
		assert(Ns.length == runtimes.length);
		XYChart.Series series = new XYChart.Series();
		series.setName("Runtime (seconds)");
		//populating the series with data
		for (int i=0; i<Ns.length; ++i) {
			series.getData().add(new XYChart.Data<Integer, Double>(Ns[i], runtimes[i]));
		}
		return series;
	}

	@Override public void start(Stage stage) {
		stage.setTitle("Percolation using Weighted Quick Union Union-Find");
		//defining the axes
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("N");
		yAxis.setLabel("Seconds");
		//creating the chart
		final LineChart<Number,Number> lineChart = 
				new LineChart<Number,Number>(xAxis,yAxis);

		lineChart.setTitle("Running time versus Grid size (N) ");
		XYChart.Series series = generateSeries(Ns, runtimes);
		Scene scene  = new Scene(lineChart,800,600);
		lineChart.getData().add(series);
		stage.setScene(scene);
		stage.show();
	}

}
