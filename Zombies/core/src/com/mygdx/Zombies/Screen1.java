package com.mygdx.Zombies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.Zombies.InvisibleActor;

public class Screen1 implements Screen {
	TextureRegion backgroundTexture;
	boolean showRacksack;
	
	public Screen1(SpriteBatch batch){
		stage=new Stage(new StretchViewport(800,480),batch);
		backgroundTexture=new TextureRegion(new Texture("1.jpg"));
		
		backgr=new Actor(){//фон
			public void draw(Batch batch, float parentAlpha){
				batch.draw(backgroundTexture, 0, 0);
				batch.draw(Zombies.getInstance().slotToDraw, 0, 0);
				if (showRacksack){
					Zombies.getInstance().racksack.draw(batch, parentAlpha);
				}
			}
		};
		stage.addActor(backgr);
		
		toBush=new InvisibleActor(64,64,420,140);
		toBush.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				Zombies.getInstance().gotoBushes();
				return true;
			}
		});
		stage.addActor(toBush);
		
		toMailbox=new InvisibleActor(45,45,145,335);
		toMailbox.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				Zombies.getInstance().gotoMailbox();
				return true;
			}
		});
		stage.addActor(toMailbox);
		
		stage.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				
				//предмет уже выбран, надо его использовать
				if (Zombies.getInstance().racksack.itemSelected){
					Zombies.getInstance().racksack.itemSelected=false;
					showRacksack=false;
					return true;
				}
				
				//слот
				boolean toSlot=(x>10&&x<100)&&(y>10&&y<100);
				if (toSlot){
					if (Zombies.getInstance().slotToDraw==Zombies.getInstance().oneSlot){ 
						Zombies.getInstance().slotToDraw=Zombies.getInstance().allSlots;
						showRacksack=true;
					}
					else {
						Zombies.getInstance().slotToDraw=Zombies.getInstance().oneSlot;
						showRacksack=false;
					}
				}
				
				//берём предмет из рюкзака
				if (showRacksack&&(y>10&&y<100&&x>115)){
					int index=(int) ((x-115)/85);
					if (index>=0&&index<Zombies.getInstance().racksack.content.length){
						boolean gotTheRightCell=!(Zombies.getInstance().racksack.content[index]==null);
						if (gotTheRightCell){
							Zombies.getInstance().racksack.currentItem=Zombies.getInstance().racksack.content[index];
							Zombies.getInstance().racksack.itemSelected=true;
							Zombies.getInstance().slotToDraw=Zombies.getInstance().oneSlot;
						}
					}
				}				
				return true;
			}
		});
	}
	
	Stage stage;
	Actor backgr;
	InvisibleActor toBush, toMailbox;
		
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		showRacksack=false;
		stage.draw();

	}

	@Override
	public void render(float delta) {
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		Zombies.getInstance().slotToDraw=Zombies.getInstance().oneSlot;

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
