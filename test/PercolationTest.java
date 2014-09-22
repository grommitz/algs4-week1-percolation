import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class PercolationTest {

	private Percolation perc;
	private static final int N = 100;
	
	@Before
	public void setUp() throws Exception {
		perc = new Percolation(N);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void should_initialise_all_cells_to_blocked() {
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= N; ++j) {
				assertThat(String.format("(%d,%d) should be empty!", i, j), 
						perc.isOpen(i, j), is(false));
			}
		}
	}

	@Test
	public void should_open() {
		assertThat(perc.isOpen(1, 1), is(false));
		perc.open(1, 1);
		assertThat(perc.isOpen(1, 1), is(true));	
	}
	
	@Test
	public void open_same_cell_on_every_row_makes_it_percolate() {
		for (int i = 1; i <= N; ++i) {
			assertThat(perc.percolates(), is(false));
			perc.open(i, 1);
		}
		assertThat(perc.percolates(), is(true));
	}
	
	@Test
	public void test_isFull() {
		for (int i = 1; i <= 5; ++i) {
			assertThat(perc.isFull(5, 1), is(false));
			perc.open(i, 1);
		}
		assertThat(perc.isFull(5, 1), is(true));
	}
	
	
}
