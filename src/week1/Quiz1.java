package week1;

import algs4.QuickFindUF;
import algs4.WeightedQuickUnionUF;

public class Quiz1 {

	void question1() {
		QuickFindUF uf = new QuickFindUF(10);
		uf.union(4, 0);
		uf.union(1, 7);
		uf.union(7, 4);
		uf.union(7, 8);
		uf.union(3, 6);
		uf.union(9, 0);
		printId(1, uf.getId());
	}
	
	void question2() {
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
		uf.union(1, 8);
		uf.union(5, 0);
		uf.union(9, 8);
		uf.union(2, 3);
		uf.union(6, 8);
		uf.union(2, 5);
		uf.union(1, 7);
		uf.union(0, 7);
		uf.union(7, 4);
		printId(2, uf.getId());
	}
	
	private void printId(int question, int[] id) {
		System.out.print("Question "+question+" = ");
		for (int i=0; i < id.length; ++i) {
			System.out.print(id[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		new Quiz1().question1();
		new Quiz1().question2();
	}
	
}
