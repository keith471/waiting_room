package main;

import java.util.ArrayList;

public class Result {

	private int min;
	private ArrayList<Character> config;

	public Result(ArrayList<Character> config, int min) {
		this.config = config;
		this.min = min;
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
