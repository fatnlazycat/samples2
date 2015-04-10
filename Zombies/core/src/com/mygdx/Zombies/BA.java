package com.mygdx.Zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BA extends Actor{
			public void draw(Batch batch, float parentAlpha){
				batch.draw(new TextureRegion(new Texture("1.jpg")), 0, 0);	
			}
		}

