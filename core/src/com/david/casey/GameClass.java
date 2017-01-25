package com.david.casey;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Game;
import com.david.casey.screens.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;





public class GameClass extends Game {
    public SpriteBatch batch;
    public static final int MENU_STATE_WIDTH = 512;
    public static final int MENU_STATE_HEIGHT = 288;
    public static final int SHITTY_PETES_WHARF_WIDTH = 512;
    public static final int SHITTY_PETES_WHARF_HEIGHT = 288;
    public static final float PPM = 100;


    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MenuScreen(this));

    }

    public GameClass() {
        super();
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

    @Override
    public Screen getScreen() {
        return super.getScreen();
    }

}
