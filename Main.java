package main;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		int[] positions = new int[] { 0, 1, 2, 3, 4, 5, 8 };
		int c = 4;
		int t = 2;

		Solver s = new Solver(positions.length, c + 1, t + 1);

		ArrayList<Character> config = new ArrayList<Character>();

		Result best = s.getConfig(c, t, positions.length - 1, positions, Solver.INF, Solver.INF, config);

		if (best != null) {
			System.out.println(best.getConfig().toString());
			System.out.println(best.getMin());
		} else {
			System.out.println("No possible configuration");
		}
	}

}
