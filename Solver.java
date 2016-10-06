package main;

import java.util.ArrayList;
import java.util.List;

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
	public Result getConfig(int c, int t, int i, int[] p) {
		
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
		if (i == 0) {
			ArrayList<Character> config = new ArrayList<Character>();
			if (c == 1) {
				config.add('c');
				return new Result(config, 0, INF);
			} else if (t == 1) {
				config.add('t');
				return new Result(config, INF, INF);
			} else {
				config.add(' ');
				return new Result(config, INF, INF);
			}
		} else if (cache[i][c][t] != null) {
			// We've already computed the optimal solution for the given i, c,
			// t! So we just return it. This is where dynamic programming comes
			// in :)
			return cache[i][c][t];
		} else {
			// Recursive case. We have three options: add a chair at the current
			// position, add a table, or add nothing

			ArrayList<Character> config;
			int dist;
			int min;

			List<Result> results = new ArrayList<Result>();

			// add nothing
			Result before_nothing = getConfig(c, t, i - 1, p);
			Result after_nothing = null;
			if (before_nothing != null) {
				config = new ArrayList<Character>(before_nothing.getConfig());
				config.add(' ');
				dist = INF;
				if (before_nothing.getDist() != INF) {
					dist = before_nothing.getDist() + (p[i] - p[i - 1]);
				}
				after_nothing = new Result(config, dist, before_nothing.getMin());
			}
			if (after_nothing != null) {
				results.add(after_nothing);
			}

			// add a table
			Result before_table = getConfig(c, t - 1, i - 1, p);
			Result after_table = null;
			if (before_table != null) {
				config = new ArrayList<Character>(before_table.getConfig());
				config.add('t');
				after_table = new Result(config, INF, before_table.getMin());
			}
			if (after_table != null) {
				results.add(after_table);
			}

			// add a chair
			Result before_chair = getConfig(c - 1, t, i - 1, p);
			Result after_chair = null;
			if (before_chair != null) {
				config = new ArrayList<Character>(before_chair.getConfig());
				config.add('c');
				min = before_chair.getMin();
				if (before_chair.getDist() != INF) {
					min = Math.min(before_chair.getMin(), before_chair.getDist() + (p[i] - p[i - 1]));
				}
				after_chair = new Result(config, 0, min);
			}
			if (after_chair != null) {
				results.add(after_chair);
			}

			// Compare the three results. Store and return the result with
			// maximum `min` (maximize the minimum distance between chairs). If
			// two or more results have the same min, then take the one with
			// greater `dist`. If they have the same dist, then take the one
			// that places a chair, else take either
			Result retVal = getBest(results, i);
			
			cache[i][c][t] = retVal;
			
			return retVal;
		}
	}

	public Result getBest(List<Result> results, int i) {
		if (results.isEmpty()) {
			return null;
		} else if (results.size() == 0) {
			return results.get(0);
		} else {
			Result best = results.get(0);
			for (Result r : results) {
				if (r.getMin() > best.getMin()) {
					best = r;
				} else if (r.getMin() == best.getMin()) {
					if (r.getDist() > best.getDist()) {
						best = r;
					} else if (r.getDist() == best.getDist()) {
						ArrayList<Character> rConfig = r.getConfig();
						if (rConfig.get(i) == 'c') {
							best = r;
						}
					}
				}
			}
			return best;
		}
	}
}
