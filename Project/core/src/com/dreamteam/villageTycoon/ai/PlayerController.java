package com.dreamteam.villageTycoon.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dreamteam.villageTycoon.buildings.BuildingPlacer;
import com.dreamteam.villageTycoon.buildings.City;
import com.dreamteam.villageTycoon.userInterface.OpenBuildingPlacerButton;

public class PlayerController extends CityController {
	private OpenBuildingPlacerButton openBuildingPlacerButton;

	private BuildingPlacer placer;
	
	public PlayerController() {
		openBuildingPlacerButton = new OpenBuildingPlacerButton(new Vector2(-750, 350), this);
	}
	
	public void update(float dt) {
		if (Gdx.input.isKeyJustPressed(Keys.B)) {
			openBuildingPlacer();
		}
		
		if (placer != null) {
			if (placer.done || Gdx.input.isKeyJustPressed(Keys.Q)) {
				placer = null;
			} else {
				placer.update(getCity());
			}
		}
	}
	
	public void drawUi(SpriteBatch batch) {
		if (placer != null) { 
			placer.draw(batch);
		} else {
			openBuildingPlacerButton.draw(batch);
			openBuildingPlacerButton.update();
		}
	} 
	
	public void openBuildingPlacer() {
		if (placer == null) placer = new BuildingPlacer(getCity().getScene());
		else placer = null;
	}
	
	public boolean getBuildingPlacerNull() {
		return placer != null;
	}
	
	public City getTargetCity() {
		return null;
	}
	
	@Override
	public boolean soldierIsAI() {
		return false;
	}
}
