package org.seariver.dungeonbob.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bob {

    public Sprite bobSprite; //sprite to display Bob
    private static final float X_MOVE_UNITS = 3f;// units bob will move in x direction

    public void update() {
        // move specified units to left if left key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            move(-X_MOVE_UNITS, 0);
        }

        // move specified units to right if right key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            move(X_MOVE_UNITS, 0);
        }
    }

    public void render(SpriteBatch batch) {
        bobSprite.draw(batch);
    }

    public void setPosition(float x, float y) {
        bobSprite.setPosition(x, y);
    }

    public void move(float x, float y) {
        setPosition(bobSprite.getX() + x, bobSprite.getY() + y);
    }
}
