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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.david.casey.GameClass;


/**
 * Created by David on 1/5/2017.
 */

public class MenuScreen implements Screen {

    private GameClass game;

    private Texture background;
    //private SpriteBatch batch;
    private OrthographicCamera orthographicCamera;
    private Viewport viewport;




    //to make the buttons as actors you can add a button that is an actor to a stage?
    Stage stage;
    TextButton quickMatchButton;
    //TextButton myProfileButton;    WILL ADD PROFILE FUNCTIONALITY LATER
    TextureAtlas quickMatchTextureAtlas;
    Skin skin;
    BitmapFont bitmapFont;
    TextButton.TextButtonStyle textButtonStyle;


    public MenuScreen(final GameClass game) {
        this.game = game;
        orthographicCamera = new OrthographicCamera();
        viewport = new FitViewport(512,1024,orthographicCamera);
        orthographicCamera.setToOrtho(false, 0, 0);
        orthographicCamera.position.set(0,0,0);
        game.batch = new SpriteBatch();
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
                game.setScreen(new CharacterSelectionScreen());
            }
        });
    }






    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glEnable(GL20.GL_TEXTURE_2D);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(orthographicCamera.combined);

        orthographicCamera.update();
        game.batch.enableBlending();


        game.batch.begin();

        game.batch.draw(background,0,0);
        stage.draw();

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
       viewport.update(width, height);
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
        game.batch.dispose();
        stage.dispose();
    }
}
