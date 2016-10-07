package main;

import java.util.ArrayList;

public class Solver {
	
	// we assume the distance between the first and last position must be less
	// than this (inf for infinity)
	public static final int INF = Integer.MAX_VALUE;

	// indexed by i (the indicating a solution for indices 0 through i of p),
	// then c (the number of chairs), then t (the number of tables)
	private Result[][][] cache;
	
	public Solver(int i, int c, int t) {
		this.cache = new Result[i][c][t];
	}

	// Dynamic programming alg for solving the waiting room problem
	public Result getConfig(int c, int t, int i, int[] p, int currDist, int currMin, ArrayList<Character> currConfig) {
		
		// error checking
		if ((c + t) > (i + 1)) {
			// there is no way we can create an arrangement of tables and chairs
			// as we don't have enough positions to fit them all
			return null;
		}
		
		if (c < 0 || t < 0) {
			// we've used a chair or table that we don't have!
			return null;
		}

		// base case
		if (i == -1) {
			return new Result(currConfig, currMin);
		} else if (cache[i][c][t] != null) {
			// We've already computed the optimal solution for the given i, c,
			// t! So we just return it. This is where dynamic programming comes
			// in :)
			if (cache[i][c][t].getMin() > currMin) {
				return cache[i][c][t];
			}
			// Otherwise, we can have a chance to update it!
		}

		// Recursive case. We have three options: add a chair at the current
		// position, add a table, or add nothing
		ArrayList<Character> config;

		// add nothing
		if (currDist != INF) {
			currDist += (p[i + 1] - p[i]);
		}
		config = new ArrayList<Character>(currConfig);
		config.add(0, 'e');
		Result nothing = getConfig(c, t, i - 1, p, currDist, currMin, config);

		// add a table
		config = new ArrayList<Character>(currConfig);
		config.add(0, 't');
		Result table = getConfig(c, t - 1, i - 1, p, INF, currMin, config);

		// add a chair
		if (currDist < currMin) {
			currMin = currDist;
		}
		config = new ArrayList<Character>(currConfig);
		config.add(0, 'c');
		Result chair = getConfig(c - 1, t, i - 1, p, 0, currMin, config);

		Result best = maxAll(nothing, table, chair);

		cache[i][c][t] = best;

		return best;
	}

	public Result maxAll(Result one, Result two, Result three) {
		return maxTwo(maxTwo(one, two), three);
	}

	public Result maxTwo(Result one, Result two) {
		if (one == null && two == null) {
			return null;
		} else if (one == null) {
			return two;
		} else if (two == null) {
			return one;
		}
		if (one.getMin() > two.getMin()) {
			return one;
		}
		return two;
	}
}
