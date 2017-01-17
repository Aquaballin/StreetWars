package com.david.casey.screens;

import com.badlogic.gdx.Game;
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
    private Viewport viewport, buttonsViewport;
    private OrthographicCamera orthographicCamera, buttonOrthographicCamera;
    SpriteBatch buttonBatch;
    //to make the buttons as actors you can add a button that is an actor to a stage?
    Stage stage;
    TextButton quickMatchButton;
    //TextButton myProfileButton;    WILL ADD PROFILE FUNCTIONALITY LATER
    TextureAtlas quickMatchTextureAtlas;
    Skin skin;
    BitmapFont bitmapFont;
    TextButton.TextButtonStyle textButtonStyle;


    /*****************************************************************************
     * 1/17/2017 Understanding Viewports and Aspect Ratios
     * Viewport is a window into the game, and how that looks on different devices
     * Viewports allow the game to be viewed on multiple screen sizes, FitViewport is best
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * buttons size is 104x58
     *
     *
     *
     *
     *
     *
     *******************************************************************************/
    public MenuScreen(final GameClass game) {
        //1. initialize game class
        this.game = game;
        //2. make a camera with an orthographic projection of a plane
        orthographicCamera = new OrthographicCamera();
        buttonOrthographicCamera = new OrthographicCamera();
        //3. create a FitViewport to maintain virtual aspect ratio despite screen size
        orthographicCamera.setToOrtho(false, GameClass.MENU_STATE_WIDTH , GameClass.MENU_STATE_HEIGHT );
        //3. create a FitViewport to maintain virtual aspect ratio despite screen size
        this.viewport = new FitViewport(GameClass.MENU_STATE_WIDTH , GameClass.MENU_STATE_HEIGHT, orthographicCamera);
        buttonsViewport = new FitViewport(GameClass.MENU_STATE_WIDTH, GameClass.MENU_STATE_HEIGHT, buttonOrthographicCamera);
        //orthographicCamera.position.set(0,0,0); does orthographic camera really matter for this section of the app?
        //4. add a sprite batch for the images that will be rendered for this screen
        game.batch = new SpriteBatch();
        buttonBatch = new SpriteBatch();
        //5. load the menuscreen picture
        background = new Texture(Gdx.files.internal("MenuScreen.png"));
        //quickmatchButton = new Texture(Gdx.files.internal("QuickmatchButtons/QuickmatchButton_DOWN.png"));
        //6. create a stage that the buttons can be a part of -- stage is a place for actors... the button will be an actor in this case
        stage = new Stage(buttonsViewport, buttonBatch );
        //7. set the stage to have have input capabilities
        Gdx.input.setInputProcessor(stage);
        //8. a font is required to make this goddamn button
        bitmapFont = new BitmapFont();
        //9. a skin is require for this complicated button, no idea what a skin is
        skin = new Skin();
        //10. make a texture atlas for the button when it is clicked. a texture atlas is a series of images
        quickMatchTextureAtlas = new TextureAtlas(Gdx.files.internal("quickMatchButtons.atlas"));
        //11. add the images to the button
        skin.addRegions(quickMatchTextureAtlas);
        //12. instantiate the button
        textButtonStyle= new TextButton.TextButtonStyle();
        //13. add the required bitmap font to the button
        textButtonStyle.font = bitmapFont;
        //14. these two lines add the pictures for the input actions
        textButtonStyle.up = skin.getDrawable("QuickmatchButton_UP");
        textButtonStyle.down = skin.getDrawable("QuickmatchButton_DOWN");
        //15. make the new text button
        quickMatchButton = new TextButton("", textButtonStyle);
        //16. add the actor to the screen
        quickMatchButton.setPosition(288/3,(512/5)*2);
        stage.addActor(quickMatchButton);
        //17. add a listener to the button
        quickMatchButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new CharacterSelectionScreen());
                dispose();
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //cryptic code that is required
        Gdx.gl.glEnable(GL20.GL_TEXTURE_2D);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(orthographicCamera.combined);

        orthographicCamera.update();
        //game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(background,0,0); //,GameClass.MENU_STATE_WIDTH,GameClass.MENU_STATE_HEIGHT);
        //game.batch.draw(quickmatchButton,GameClass.MENU_STATE_WIDTH/3,(GameClass.MENU_STATE_HEIGHT/5)*2);
        game.batch.end();


        /*
                This is super important and you need to pay attention to how the stage batch draw sits above the
                other batch.
         */
        buttonBatch.setProjectionMatrix(buttonOrthographicCamera.combined);
        stage.draw();


    }


    /**
     * This message is important because its adjusts the viewport based on the size of the screen
     *
     *
     *
     *
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height);
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
        buttonBatch.dispose();
    }
}
