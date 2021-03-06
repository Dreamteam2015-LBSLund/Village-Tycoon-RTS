package com.dreamteam.villageTycoon.map;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dreamteam.villageTycoon.AssetManager;
import com.dreamteam.villageTycoon.buildings.Building;
import com.dreamteam.villageTycoon.framework.Scene;
import com.dreamteam.villageTycoon.game.GameScene;
import com.dreamteam.villageTycoon.utils.Debug;
import com.dreamteam.villageTycoon.characters.Character;

public class Tile {
	public final static float WIDTH = 1, HEIGHT = 1;
	
	private TileType type;
	private Vector2 position;
	private boolean hasBuilding() { return building != null; }
	private Building building;
	
	public Tile(Vector2 position, TileType type, Scene scene) {
		this.type = type;
		this.position = position;

		String prop = type.getProps();
		if (prop != null) {
			scene.addObject(new Prop(position.cpy().add(new Vector2(WIDTH / 2, HEIGHT / 2).add(new Vector2(MathUtils.random(-.2f, .2f), MathUtils.random(-.2f, .2f)))), PropType.getType(prop), this));
		}
	}
	
	public void build(Building b) {
//		//System.out.println("Building on " + getPosition());
		this.building = b;
	}
	
	public void unBuild() {
		this.building = null;
	}
	
	public void removeBuilding() {
		building = null;
	}
	
	public TileType getType() {
		return type;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public boolean isWalkable(Character c) {
		if (building != null && c != null) Debug.print(this,
				c.getCity() + ", "
		+ building.getCity() + "");
		return type.isWalkable() && (!hasBuilding() || !building.obstructs(c));
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(type.getSprite(), position.x, position.y, WIDTH, HEIGHT);
	}

	public Building getBuilding() {
		return building;
	}

	public boolean isBuildable() {
		return type.isBuildable() && !hasBuilding();
	}

	public ArrayList<Tile> getNeighbors(GameScene scene) {
		ArrayList<Tile> ret = new ArrayList<Tile>();
		for (int dx = -1; dx < 2; dx++) {
			for (int dy = -1; dy < 2; dy++) {
				if (dx != 0 || dy != 0) {
					ret.add(scene.getMap().tileAt(new Vector2(position.x + dx, position.y + dy)));
				}
			}
		}
		return ret;
	}
}
