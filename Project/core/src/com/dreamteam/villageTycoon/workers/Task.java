package com.dreamteam.villageTycoon.workers;

public interface Task {
	// tries to carry out the task. Returns true when completed.
	public boolean work(Worker w);
	
	public void onCancel();

	public String getString();
}
