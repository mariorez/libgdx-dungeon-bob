package org.seariver.dungeonbob.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.seariver.dungeonbob.gameobjects.Bob;

public class GameManager {

    static Bob bob; // bob instance
    static Texture bobTexture; // texture image for the bob
    public static Sprite backgroundSprite; // background sprite
    public static Texture backgroundTexture; // texture image for the background
    public static final float BOB_RESIZE_FACTOR = 800f;

    public static void initialize(float width, float height) {
        // instantiate the bob
        bob = new Bob();
        // load the bob texture with image from file
        bobTexture = new Texture(Gdx.files.internal("bob.png"));
        // instantiate bob sprite
        bob.bobSprite = new Sprite(bobTexture);
        //set the size of the bob
        bob.bobSprite.setSize(bob.bobSprite.getWidth() * (width / BOB_RESIZE_FACTOR), bob.bobSprite.getHeight() * (width / BOB_RESIZE_FACTOR)); // set the position of bob to bottom - center
        bob.setPosition(width / 2f, 0);
        //load background texture
        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        //set background sprite with the texture
        backgroundSprite = new Sprite(backgroundTexture);
        // set the background to completely fill the screen
        backgroundSprite.setSize(width, height);
    }

    public static void renderGame(SpriteBatch batch) {
        // draw the background
        backgroundSprite.draw(batch);

        bob.update();
        // Render(draw) the bob
        bob.render(batch);
    }

    public static void dispose() {
        //dispose the background texture
        backgroundTexture.dispose();
        // dispose of the bob texture to ensure no memory leaks
        bobTexture.dispose();
    }
}
