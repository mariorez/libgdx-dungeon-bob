package org.seariver.dungeonbob.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.seariver.dungeonbob.GameConstants;
import org.seariver.dungeonbob.gameobjects.Bob;

public class GameManager {

    static Bob bob; // bob instance
    static TextureRegion bobSpriteSheet; // texture spriteSheet for the bob.

    static Texture backgroundTexture; // texture image for the background
    static Sprite backgroundSprite; // background sprite

    static AssetManager assetManager;
    static TextureAtlas texturePack; // packed texture.

    public static void loadAssets() {
        // queue the assets for loading
        assetManager.load(GameConstants.backGroundImage, Texture.class);

        assetManager.load(GameConstants.texturePack, TextureAtlas.class);

        //blocking method to load all assets
        assetManager.finishLoading();
    }

    public static void initialize(float width, float height) {
        assetManager = new AssetManager();
        loadAssets();
        texturePack = assetManager.get(GameConstants.texturePack); // get the packed texture from asset manager

        // load the bob sprite sheet from the packed image
        bobSpriteSheet = texturePack.findRegion(GameConstants.bobSpriteSheet);
        bob = new Bob(width, height, bobSpriteSheet);

        //load background texture
        //backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        backgroundTexture = assetManager.get(GameConstants.backGroundImage);
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
        assetManager.clear();
    }
}
