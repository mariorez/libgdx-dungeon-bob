package org.seariver.dungeonbob.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.seariver.dungeonbob.gameobjects.Bob;

public class GameManager {

    static Bob bob; // bob instance
    static Texture bobSpriteSheet; // texture sprite sheet for the bob
    public static Sprite backgroundSprite; // background sprite
    public static Texture backgroundTexture; // texture image for the background

    public static void initialize(float width, float height) {
        // instantiate the bob
        bobSpriteSheet = new Texture(Gdx.files.internal("bob_spritesheet.png"));
        bob = new Bob(width, height, bobSpriteSheet);

        //load background texture
        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(width, height);
    }

    public static void renderGame(SpriteBatch batch) {
        // draw the background
        backgroundSprite.draw(batch);

        // update and render (draw) the bob
        bob.update();
        bob.render(batch);
    }

    public static void dispose() {
        //dispose the background texture
        backgroundTexture.dispose();
        // dispose of the bob texture to ensure no memory leaks
        bobSpriteSheet.dispose();
    }
}
