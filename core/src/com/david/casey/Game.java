package com.david.casey;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import com.badlogic.gdx.math.Vector2;
import com.david.casey.sprites.*;

import java.util.HashMap;

/*
BUM FIGHTING GAME THAT HAS A PVE MODE AND PVP ARENA MODE?
SQL LITE DATA BASE FOR PVE ACHEIVES



 */

public class Game extends ApplicationAdapter {


    private final float UPDATE_TIME = 1/60f;
    float timer;
    SpriteBatch batch;
    Texture otherPlayerTexture;
    Texture yourPlayerTexture;
    private Socket socket;
    yourHomelessGuy yourRedBug;

    HashMap<String, theirHomelessGuy> otherPlayers;





    @Override
    public void create() {
        batch = new SpriteBatch();
        yourPlayerTexture = new Texture("homelessGuy1.png");
        otherPlayerTexture = new Texture("homelessGuy2.png");
        otherPlayers = new HashMap<String, theirHomelessGuy>();
        connectSocket();
        configSocketEvents();

    }

    @Override
    public void render() {

        handleInput(Gdx.graphics.getDeltaTime());
        updateServer(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        if (yourRedBug != null) {
            yourRedBug.draw(batch);
        }
        for (HashMap.Entry<String, theirHomelessGuy> entry : otherPlayers.entrySet()) {
            entry.getValue().draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        yourPlayerTexture.dispose();
        otherPlayerTexture.dispose();
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

    public void updateServer(float deltaTime) {
        timer += deltaTime;
        if (timer >= UPDATE_TIME && yourRedBug != null && yourRedBug.hasMoved()) {
            JSONObject data = new JSONObject();
            try {
                data.put("x",yourRedBug.getX());
                data.put("y",yourRedBug.getY());
                socket.emit("playerMoved",data);
            } catch (JSONException e) {
                Gdx.app.log("SOCKET.IO", "Error sending update data");
            }
        }
    }

    public void configSocketEvents() {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO", "Connected");
                yourRedBug = new yourHomelessGuy(yourPlayerTexture);
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
                    String playerId = data.getString("id");
                    Gdx.app.log("SocketIO", "New Player Connect: " + playerId);
                    otherPlayers.put(playerId,new theirHomelessGuy(otherPlayerTexture));
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting new player id");
                }
            }


        }).on("playerDisconnected", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    otherPlayers.remove(id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting new player id");
                }
            }
        }).on("getPlayers", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray objects = (JSONArray) args[0];
                try {
                    for (int i = 0; i < objects.length(); i++) {
                        theirHomelessGuy otherPlayer = new theirHomelessGuy(otherPlayerTexture);
                        Vector2 position = new Vector2();
                        position.x = ((Double) objects.getJSONObject(i).getDouble("x")).floatValue();
                        position.y = ((Double) objects.getJSONObject(i).getDouble("y")).floatValue();
                        otherPlayer.setPosition(position.x,position.y);
                        otherPlayers.put(objects.getJSONObject(i).getString("id"),otherPlayer);
                    }
                } catch (Exception e) {
                    Gdx.app.log("SocketIO", "something is wrong");
                    e.printStackTrace();

                }
            }
        }).on("playerMoved", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String playerId = data.getString("id");
                    Double x = data.getDouble("x");
                    Double y = data.getDouble("y");
                    if (otherPlayers.get(playerId) != null) {
                        otherPlayers.get(playerId).setPosition(x.floatValue(),y.floatValue());
                    }
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting player movements");
                }
            }
        });
    }
}
