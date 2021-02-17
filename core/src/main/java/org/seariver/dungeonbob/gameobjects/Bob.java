package org.seariver.dungeonbob.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bob {

    public Sprite bobSprite; //sprite to display Bob

    public void render(SpriteBatch batch) {
        bobSprite.draw(batch);
    }

    public void setPosition(float x, float y) {
        bobSprite.setPosition(x, y);
    }
}
