package com.dreamteam.villageTycoon.characters;

import com.badlogic.gdx.math.Vector2;
import com.dreamteam.villageTycoon.framework.Animation;
import com.dreamteam.villageTycoon.framework.GameObject;

public abstract class Soldier extends Character {
	enum AggressionState { ATTACKING_AND_MOVING, STEALTH, DEFENSIVE };
	
	private AggressionState  aggressionState;
	private Weapon weapon;
	private SoldierType soldierType;
	
	private float attackDistance;
	private float shootAngle;
	
	private float speed;
	private float accuracy;
	private float toughness;
	
	private boolean isMoving;
	private boolean enemy;
	
	public Soldier(Vector2 position, Animation sprite, Weapon weapon) {
		super(position, sprite);
		this.weapon = weapon;
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		attack();
	}
	
	public void attack() {
		
	}
	
	public void setSoldierType() {
		this.setSprite(soldierType.getTypeSprite());
		this.speed = soldierType.getSpeed();
		this.accuracy = soldierType.getAccuracy();
		this.toughness = soldierType.getToughness();
		
		this.enemy = soldierType.getEnemy();
	}
}
