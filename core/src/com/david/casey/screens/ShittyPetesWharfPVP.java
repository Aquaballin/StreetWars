package com.david.casey.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.david.casey.GameClass;

/**
 * Created by David on 1/4/2017.
 */

class ShittyPetesWharfPVP implements Screen {
    //reference the game
    private GameClass gameClass;
    private TextureAtlas yourTextureAtlas;

    //variables for this screen
    private OrthographicCamera orthographicCamera;
    private Viewport viewport;
    // add this -> private Hud hud;

    //tiled map variables
    private TmxMapLoader tmxMapLoader;
    private TiledMap tiledMap;
    OrthoCachedTiledMapRenderer orthoCachedTiledMapRenderer;

    //box2d physics variables
    private World world;
    private Box2DDebugRenderer b2dr;
    // add this -> private B2





    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    }
}
