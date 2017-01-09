package com.david.casey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.david.casey.GameClass;


/**
 * Created by David on 1/5/2017.
 */

public class MenuScreen implements Screen {

    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera orthographicCamera;


    //to make the buttons as actors you can add a button that is an actor to a stage?
    Stage stage;
    TextButton quickMatchButton;
    //TextButton myProfileButton;    WILL ADD PROFILE FUNCTIONALITY LATER
    TextureAtlas quickMatchTextureAtlas;
    Skin skin;
    BitmapFont bitmapFont;
    TextButton.TextButtonStyle textButtonStyle;


    public MenuScreen(final GameClass gameClass) {
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("MenuScreen.png"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        bitmapFont = new BitmapFont();
        skin = new Skin();
        quickMatchTextureAtlas = new TextureAtlas(Gdx.files.internal("quickMatchButtons.atlas"));
        skin.addRegions(quickMatchTextureAtlas);
        textButtonStyle= new TextButton.TextButtonStyle();
        textButtonStyle.font = bitmapFont;

        textButtonStyle.up = skin.getDrawable("QuickmatchButton_UP");
        textButtonStyle.down = skin.getDrawable("QuickmatchButton_DOWN");
        quickMatchButton = new TextButton("", textButtonStyle);
        stage.addActor(quickMatchButton);
        quickMatchButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameClass.setScreen(new CharacterSelectionScreen());
            }
        });
    }






    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        orthographicCamera.update();
        batch.enableBlending();
        batch.begin();

        batch.draw(background,0,0);
        stage.draw();

        batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }
}
