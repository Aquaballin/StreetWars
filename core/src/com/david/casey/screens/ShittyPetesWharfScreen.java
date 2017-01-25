package com.david.casey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.david.casey.GameClass;

/**
 * Created by David on 1/4/2017.
 */

public class ShittyPetesWharfScreen implements Screen {
    private GameClass game;
    private TextureAtlas yourTextureAtlas;
    private OrthographicCamera orthographicCamera;
    private Viewport viewport;
    private Texture backgroundTexture;
    private World world;
    private Box2DDebugRenderer b2dr;
    public ShittyPetesWharfScreen(GameClass game) {
        //yourTextureAtlas = new TextureAtlas()
        this.game = game;
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false,GameClass.SHITTY_PETES_WHARF_WIDTH,GameClass.SHITTY_PETES_WHARF_HEIGHT);
        this.viewport = new FitViewport(GameClass.SHITTY_PETES_WHARF_WIDTH, GameClass.SHITTY_PETES_WHARF_HEIGHT,orthographicCamera);
        game.batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("shittyPetesWharfDemo.png"));




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
        //game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(backgroundTexture,0,0); //,GameClass.MENU_STATE_WIDTH,GameClass.MENU_STATE_HEIGHT);
        //game.batch.draw(quickmatchButton,GameClass.MENU_STATE_WIDTH/3,(GameClass.MENU_STATE_HEIGHT/5)*2);
        game.batch.end();

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
        game.batch.dispose();
    }
}


