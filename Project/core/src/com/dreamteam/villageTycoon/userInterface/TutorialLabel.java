package com.dreamteam.villageTycoon.userInterface;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TutorialLabel {
	final float NEXT_PART_TIME = 1;
	
	private Vector2 position;
	
	private String[] parts;
	
	private int currentPart;
	
	private float changePartCount;
	
	public TutorialLabel(Vector2 position) {
		
	}
	
	public void update(float deltaTime) {
		if(currentPart < parts.length-1) {
			changePartCount += 1 * deltaTime;
		
			if(changePartCount >= NEXT_PART_TIME) {
				currentPart += 1;
				changePartCount = 0;
			}
		}
	}
	
	public void draw(SpriteBatch batch) {
		
	}
}