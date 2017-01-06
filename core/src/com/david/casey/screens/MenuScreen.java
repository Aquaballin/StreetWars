package com.david.casey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.david.casey.GameClass;

/**
 * Created by David on 1/5/2017.
 */

public class MenuScreen implements Screen {
    private Texture background;
    private SpriteBatch batch;
    TextButton quickMatchButton;
    TextButton myProfileButton;




    public MenuScreen(GameClass gameClass) {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("MenuScreen.png"));






    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background,0,0);
        //drawing goes here
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

    }
}
