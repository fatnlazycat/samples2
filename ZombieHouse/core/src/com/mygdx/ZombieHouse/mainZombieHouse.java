package com.mygdx.ZombieHouse;	

import com.badlogic.gdx.Game; 
import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.Input.Keys; 
import com.badlogic.gdx.InputProcessor; 
import com.badlogic.gdx.graphics.GL20; 
import com.badlogic.gdx.graphics.Texture; 
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch; 
import com.badlogic.gdx.graphics.g2d.TextureRegion; 
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class mainZombieHouse extends Game{
	Stage stage;
	private mainZombieHouse(){
		};
	private static mainZombieHouse instance=new mainZombieHouse();
	public static mainZombieHouse getInstance(){return instance;}
	
	public void create(){
		SpriteBatch batch=new SpriteBatch();

		
		stage=new Stage(new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),batch);
		

		BA backgr=new BA();
		

		stage.addActor(backgr);
		stage.draw();
	
	}
	public void render(){
		stage.draw();
	}
}

class BA extends Actor{
	//TextureRegion region;
	public BA(){
        //setSize(100, 50);
        //setPosition(300, 50);
        //region = new TextureRegion(new Texture("1.jpg"));
	}
	
	public void draw(Batch batch, float parentAlpha){
        //batch.setColor(getColor());
		batch.draw(new TextureRegion(new Texture("1.jpg")), 0, 0);}
}