package com.mygdx.Zombies;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Racksack extends Actor {
	TextureRegion[] content;
	TextureRegion currentItem;
	boolean itemSelected;
	
	public Racksack(){
		content=new TextureRegion[7];
		itemSelected=false;
	}
	
	int next(){
		int i;
		for (i=0; !(content[i]==null); i++) {if (i==6) return 6;}
		return i;
		
	}
	
	public void draw(Batch batch, float parentAlpha){
		if (itemSelected) batch.draw(currentItem, 10, 10);
		else{
			int numberOfItems=this.next();
			for (int i=0; i<numberOfItems; i++){
				batch.draw(content[i], 120+80*i, 10);
			}
		}
	}
}
