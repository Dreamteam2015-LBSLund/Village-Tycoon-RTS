package com.dreamteam.villageTycoon.map;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dreamteam.villageTycoon.AssetManager;
import com.dreamteam.villageTycoon.framework.Rectangle;
import com.dreamteam.villageTycoon.utils.InventoryItem;
import com.dreamteam.villageTycoon.utils.ResourceReader;

public class Resource implements InventoryItem {
	private static HashMap<String, Resource> types;
	
	private TextureRegion icon;
	private String name;
	
	private Resource(ResourceReader r) {
		name = r.getObjectName();
		icon = AssetManager.getTexture(r.getString("icon"));
	}
	
	public TextureRegion getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}
	
	public void draw(Rectangle position, SpriteBatch batch) {
		batch.draw(icon, position.getX(), position.getY(), position.getWidth(), position.getHeight());
	}

	public static HashMap<String, Resource> getTypes() {
		if (types == null) {
			types = new HashMap<String, Resource>();
			for (ResourceReader r : ResourceReader.readObjectList(Gdx.files.internal("resources.gd"))) {
				types.put(r.getObjectName(), new Resource(r));
				System.out.println(r.getObjectName()); // to make a list of sprites for Carro
			}	
		}
		return types;
	}
	
	public static Resource get(String name) {
		Resource t = getTypes().get(name);
		if (t == null) System.out.println("WARNING: Resource " + name + " not recognized");
		return t;
	}
	
	public static Resource[] fromStringArray(String[] r) {
		Resource[] out = new Resource[r.length];
		for (int i = 0; i < r.length; i++) {
			out[i] = get(r[i]);
		}
		return out;
	}
	
	public static boolean isEnough(ArrayList<Resource> have, ArrayList<Resource> take) {
		ArrayList<Resource> h = (ArrayList<Resource>) have.clone();
		for (Resource r : take) {
			if (!h.remove(r)) return false;
		}
		return true;
	}
	
	public static ArrayList<Resource> constructList(Resource[] resources, int[] amounts) {
		ArrayList<Resource> ret = new ArrayList<Resource>();
		int ri = 0;
		for (Resource r : resources) {
			for (int i = 0; i < amounts[ri]; i++) {
				ret.add(r);
			}
			ri++;
		}
		return ret;
	}
}
