package com.mygdx.Zombies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

public class ScreenMailbox implements Screen {
	public ScreenMailbox(SpriteBatch batch){
		
		this.batch=batch;
		stage=new Stage(new StretchViewport(800,480),batch);
		
		backgr=new Actor(){//фон
			TextureRegion region=new TextureRegion(new Texture("2.jpg"));
			TextureRegion openCover=new TextureRegion(new Texture("4.png"));
			TextureRegion letter=new TextureRegion(new Texture("3.png"));
			public void draw(Batch batch, float parentAlpha){
				batch.draw(region, 0, 0);
				if (status>0) batch.draw(openCover, 0, 0);
				if (status==1) batch.draw(letter, 0, 0);
				batch.draw(Zombies.getInstance().slotToDraw, 0, 0);
				if (showRacksack){
					Zombies.getInstance().racksack.draw(batch, parentAlpha);
				}
			}
		};
		stage.addActor(backgr);
		
		oknoDlyaInventarja=new Actor(){
			TextureRegion regionOKNO=new TextureRegion(new Texture("OKNO dlja inventarja.png"));
			TextureRegion region=new TextureRegion(new Texture("bigLetter.png"));
			public void draw(Batch batch, float parentAlpha){
				if (status==2) {
					batch.draw(regionOKNO, 0, 0);
					batch.draw(region, 210, 170, 350, 190);
				}
			}			
		};
		oknoDlyaInventarja.setSize(800, 480);
		oknoDlyaInventarja.setPosition(0, 0);
		oknoDlyaInventarja.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				status=3;
				return true;
			}
		});
		stage.addActor(oknoDlyaInventarja);
		
		//stage.addActor(Zombies.getInstance().racksack);
		
		bb=new BackButton();
		stage.addActor(bb);
		
		stage.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				
				//предмет уже выбран, надо его использовать
				if (Zombies.getInstance().racksack.itemSelected){
					Zombies.getInstance().racksack.itemSelected=false;
					showRacksack=false;
					return true;
				}
				
				//тык на ящик
				boolean inRange=(x>193&&x<514)&&(y>220&&y<460);
				if (status==2) status=3;
				if (((status==0)||(status==1))&&inRange) pick();
				
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

	SpriteBatch batch;
	Stage stage;
	Actor backgr, letter, oknoDlyaInventarja;
	BackButton bb;
	int status;
	boolean showRacksack;
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
	
	public void pick(){
		if (status==1){
			TextureRegion smallLetter=new TextureRegion(new Texture("3.png"),250,85,100,130);
			Zombies.getInstance().racksack.content[Zombies.getInstance().racksack.next()]=smallLetter;
		}
		status++;
	}
	
}