package org.seariver.dungeonbob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.seariver.dungeonbob.managers.GameManager;

public class GameScreen implements Screen {

    private GameInitializer game;
    SpriteBatch batch; // spritebatch for drawing
    OrthographicCamera camera;

    public GameScreen(GameInitializer game) {
        this.game = game;
        // get window dimensions and set our viewport dimensions
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        // set our camera viewport to window dimensions
        camera = new OrthographicCamera(width, height);
        // center the camera at w/2,h/2
        camera.setToOrtho(false);

        batch = new SpriteBatch();
        //initialize the game
        GameManager.initialize(width, height);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // set the spritebatch's drawing view to the camera's view
        batch.setProjectionMatrix(camera.combined);

        // render the game objects
        batch.begin();
        GameManager.renderGame(batch);
        batch.end();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
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
        //dispose the batch and the textures
        batch.dispose();
        GameManager.dispose();
    }
}
