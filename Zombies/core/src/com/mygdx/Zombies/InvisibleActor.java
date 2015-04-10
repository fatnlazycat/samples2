package com.mygdx.Zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

class InvisibleActor extends Actor{
	private InvisibleActor(){}
	int id;
	TextureRegion region;
	
	public InvisibleActor(int width, int height, int Xposition, int Yposition){
		setSize(width, height);
		setPosition(Xposition, Yposition);
		region=new TextureRegion(new Texture("empty.png"));
	}
	
	public void draw(Batch batch, float parentAlpha){
		batch.draw(region, getX(), getY());
	}
}
