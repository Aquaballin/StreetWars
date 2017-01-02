package com.david.casey;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import com.david.casey.sprites.*;

public class game2 extends ApplicationAdapter {

    SpriteBatch batch;
    Texture otherPlayerBug1Texture;
    Texture yourPlayerBug1Texture;
    private Socket socket;
    redBug1 yourRedBug;
    blueBug1 theirBlueBug;




    @Override
    public void create() {
        batch = new SpriteBatch();
        yourPlayerBug1Texture = new Texture("redBug0.png");
        otherPlayerBug1Texture = new Texture("blueBug0.png");
        connectSocket();
        configSocketEvents();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput(Gdx.graphics.getDeltaTime());
        batch.begin();
        if (yourRedBug != null) {
            yourRedBug.draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        yourPlayerBug1Texture.dispose();
        otherPlayerBug1Texture.dispose();
        super.dispose();
    }

    public void connectSocket() {
        try {
            socket = IO.socket("http://localhost:8080");
            socket.connect();
        } catch (Exception e) {
            System.out.println("failed");
        }
    }

    public void handleInput(float deltaTime) {
        if (yourRedBug != null) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                yourRedBug.setPosition(yourRedBug.getX() + (-200 * deltaTime), yourRedBug.getY());
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                yourRedBug.setPosition(yourRedBug.getX() + (+200 * deltaTime), yourRedBug.getY());
            }
        }
    }

    public void configSocketEvents() {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO", "Connected");
                yourRedBug = new redBug1(yourPlayerBug1);
            }
        }).on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    Gdx.app.log("SocketIO", "My ID: " + id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting ID");
                }
            }
        }).on("newPlayer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    Gdx.app.log("SocketIO", "New Player Connect: " + id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting new player id");
                }
            }


        });
    }
}
