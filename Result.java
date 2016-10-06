package main;

import java.util.ArrayList;

public class Result {

	// Distance from the last chair in the array to the end of the array; INF if
	// table in between
	private int dist;
	// Minimum distance between any two chairs in config
	private int min;
	ArrayList<Character> config;

	public Result(ArrayList<Character> config, int dist, int min) {
		this.config = config;
		this.dist = dist;
		this.min = min;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public ArrayList<Character> getConfig() {
		return config;
	}

	public void setConfig(ArrayList<Character> config) {
		this.config = config;
	}

}
