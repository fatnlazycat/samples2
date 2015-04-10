package com.mygdx.Zombies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Zombies extends Game {
	private Zombies(){}
	private static Zombies instance=new Zombies();
	public static Zombies getInstance(){return instance;}

	SpriteBatch batch;
	Screen1 scr1;
	ScreenBushes scrBushes;
	ScreenMailbox scrMailbox;
	Texture slots;
	TextureRegion oneSlot, allSlots, slotToDraw;
	Racksack racksack;
	

	public void gotoBushes(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		setScreen(scrBushes);
	}
	
	public void gotoMailbox(){
		setScreen(scrMailbox);
	}
	
	public void gotoMain(){
		setScreen(scr1);
	}
	
		
	@Override
	public void create () {
		batch=new SpriteBatch();
		
		slots=new Texture("slotu.png");
		oneSlot=new TextureRegion(slots, 0,380,100,100);
		allSlots=new TextureRegion(slots, 0,380,800,100);
		slotToDraw=oneSlot;
		racksack=new Racksack();

		scrBushes=new ScreenBushes(batch);
		scrMailbox=new ScreenMailbox(batch);
		
		scr1=new Screen1(batch);
		setScreen(scr1);
	}

	@Override
	public void render () {
		getScreen().render(Gdx.graphics.getDeltaTime());
	}
}
