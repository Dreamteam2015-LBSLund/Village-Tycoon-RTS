package com.dreamteam.villageTycoon.characters;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dreamteam.villageTycoon.AssetManager;
import com.dreamteam.villageTycoon.framework.Rectangle;
import com.dreamteam.villageTycoon.map.Resource;
import com.dreamteam.villageTycoon.utils.InventoryItem;

public class Inventory <T extends InventoryItem> {
	private HashMap<T, Integer> things;
	
	public Inventory() {
		things = new HashMap<T, Integer>();
	}
	
	public void add(T thing, int amount) {
		if (things.containsKey(thing)) {
			things.put(thing, things.get(thing).intValue() + amount);
		} else {
			things.put(thing, amount);
		}
		
		if (things.get(thing).intValue() <= 0) {
			things.remove(thing);
			//System.out.println("WARNING: Negative or 0 amount in inventory");
		}
	}
	
	public void add(T[] things) {
		for  (T t : things) add(t, 1);
	}
	
	public void remove(T[] things) {
		for  (T t : things) remove(t, 1);
	}
	
	public void remove(T thing, int amount) {
		add(thing, -amount);
	}
	
	public int count(T thing) {
		if (!things.containsKey(thing)) return 0;
		return things.get(thing).intValue();
	}
	
	public InventoryItem[] getList() {
		ArrayList<T> things = new ArrayList<T>();
		for (T t : this.things.keySet()) for (int i = 0; i < this.things.get(t).intValue(); i++) things.add(t);
		return things.toArray(new InventoryItem[things.size()]);
	}
	
	public void drawList(Vector2 position, SpriteBatch batch) {
		final float ICON_SIZE = 100f;
		Rectangle r = null;
		InventoryItem[] items = things.keySet().toArray(new InventoryItem[things.keySet().size()]);
		// TODO: remove prints
		//System.out.println(items);
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				System.out.println("OOPS Inventory line 64"); 
				continue;
			}
			r = new Rectangle(position.cpy().add(new Vector2(0, i * ICON_SIZE)), new Vector2(ICON_SIZE, ICON_SIZE));
			//System.out.println(items[i] + ", " + r + ", " + batch);
			items[i].draw(r, batch);
			if (items[i] instanceof Resource) {
				AssetManager.font.draw(batch, ((Resource)items[i]).getName() + count((T)items[i]), r.getX() + ICON_SIZE, r.getY() + ICON_SIZE);
			} else AssetManager.font.draw(batch, "" + count((T)items[i]), r.getX() + ICON_SIZE, r.getY() + ICON_SIZE);
		}
	}
}

