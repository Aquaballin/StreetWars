package com.david.casey.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by David on 12/31/2016.
 */

public class blueBug1 extends Sprite {
    Vector2 previousPosition;

    public blueBug1(Texture texture) {
        super(texture);
        previousPosition = new Vector2(getX(),getY());

    }
    public boolean hasMoved() {
        if (previousPosition.x != getX() || previousPosition.y != getY()) {
            previousPosition.x = getX();
            previousPosition.y = getY();
            return true;
        }
        return false;
    }
}
