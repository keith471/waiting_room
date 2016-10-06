package main;

public class Main {

	public static void main(String[] args) {
		int[] positions = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		int c = 5;
		int t = 2;

		Solver s = new Solver(positions.length, c + 1, t + 1);

		Result best = s.getConfig(c, t, positions.length - 1, positions);

		if (best != null) {
			System.out.println(best.getConfig().toString());
		} else {
			System.out.println("No possible configuration");
		}
	}

}
