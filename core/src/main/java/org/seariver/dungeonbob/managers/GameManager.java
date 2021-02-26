package org.seariver.dungeonbob.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import org.seariver.dungeonbob.GameConstants;
import org.seariver.dungeonbob.GameScreen;
import org.seariver.dungeonbob.gameobjects.Bob;

public class GameManager {

    static Bob bob; // bob instance
    static TextureRegion bobSpriteSheet; // texture spriteSheet for the bob.

    static Texture backgroundTexture; // texture image for the background
    static Sprite backgroundSprite; // background sprite

    static AssetManager assetManager;
    static TextureAtlas texturePack; // packed texture.

    static TiledMap map;
    public static OrthogonalTiledMapRenderer renderer; // map renderer

    public static int mapWidth;
    public static int mapHeight;

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

        // get the map instance loaded
        map = assetManager.get(GameConstants.level1);
        setMapDimensions();

        GameScreen.camera.setToOrtho(false, 15, 13); // show 35x20 map tiles on screen
        GameScreen.camera.update();

        // set the renderer's view to the game's main camera
        renderer = new OrthogonalTiledMapRenderer(map, GameConstants.unitScale);
        renderer.setView(GameScreen.camera);
    }

    public static void renderGame(SpriteBatch batch) {
        // draw the background
        backgroundSprite.draw(batch);

        // update and render (draw) the bob
        bob.update();
        bob.render(batch);

        //update the camera's x position to Bob's x position
        GameScreen.camera.position.x = bob.sprite.getX();

        // if the viewport goes outside the map's dimensions update the camera's position correctly
        GameScreen.camera.position.x = MathUtils.clamp(
                GameScreen.camera.position.x,
                GameScreen.camera.viewportWidth / 2,
                mapWidth - GameScreen.camera.viewportWidth / 2);

        GameScreen.camera.update();
        renderer.setView(GameScreen.camera);
    }

    public static void dispose() {
        //dispose the background texture
        assetManager.clear();
    }

    public static void loadAssets() {
        // queue the assets for loading
        assetManager.load(GameConstants.backGroundImage, Texture.class);

        assetManager.load(GameConstants.texturePack, TextureAtlas.class);

        // set the tiled map loader for the AssetManager
        assetManager.setLoader(TiledMap.class, new TmxMapLoader());
        //load the tiled map
        assetManager.load(GameConstants.level1, TiledMap.class);

        //blocking method to load all assets
        assetManager.finishLoading();
    }

    static void setMapDimensions() {
        MapProperties properties = map.getProperties();
        mapHeight = Integer.parseInt(properties.get("height").toString());
        mapWidth = Integer.parseInt(properties.get("width").toString());
    }
}
