package com.mygdx.Zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class BackButton extends Actor {
	public BackButton(){
		region=new TextureRegion(new Texture("strelka-1.png"));
		setSize(800, 480);
		setPosition(0, 0);
		addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				boolean inRange=(x>725&&x<790)&&(y>15&&y<70);
				if (inRange){
					region=new TextureRegion(new Texture("strelka-2.png"));
					return true;
				}
				else return false;
			}	
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				region=new TextureRegion(new Texture("strelka-1.png"));
				Zombies.getInstance().gotoMain();
			}
		});
	}
	
	TextureRegion region;
	
	public void draw(Batch batch, float parentAlpha){
		batch.draw(region, 0, 0);
	}
}
