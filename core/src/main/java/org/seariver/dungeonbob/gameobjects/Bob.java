package org.seariver.dungeonbob.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import static org.seariver.dungeonbob.gameobjects.Bob.Direction.LEFT;
import static org.seariver.dungeonbob.gameobjects.Bob.Direction.RIGHT;

public class Bob {

    Sprite sprite; //sprite to display Bob
    Animation<TextureRegion> walkAnimation;
    TextureRegion walkSheet;   // sprite sheet
    TextureRegion currentFrame;
    float stateTime;

    public static final float BOB_RESIZE_FACTOR = 750f;
    private static final float X_MOVE_UNITS = 3f;// units bob will move in x direction
    private static int ANIMATION_FRAME_SIZE = 8; // this specifies the number of frames(images) that we are using for animation
    private static float ANIMATION_TIME_PERIOD = 0.08f;// this specifies the time between two consecutive frames of animation

    enum Direction {LEFT, RIGHT}

    Direction direction = RIGHT; //denotes player's direction. defaulted to right

    public Bob(float width, float height, TextureRegion walkSheet) {

        this.walkSheet = walkSheet;
        // instantiate bob sprite
        sprite = new Sprite();
        sprite.setSize(
                (walkSheet.getRegionWidth() / ANIMATION_FRAME_SIZE) * (width / BOB_RESIZE_FACTOR),
                walkSheet.getRegionHeight() * (height / BOB_RESIZE_FACTOR));
        setPosition(width / 2f, 0);

        //split the sprite sheet into different textures
        TextureRegion[][] tmp = walkSheet.split(walkSheet.getRegionWidth() / ANIMATION_FRAME_SIZE, walkSheet.getRegionHeight());
        Array<TextureRegion> textureArray = new Array<>();
        for (int c = 0; c < ANIMATION_FRAME_SIZE; c++) {
            textureArray.add(tmp[0][c]);
        }
        // create a new animation sequence with the walk frames and time period of specified seconds
        walkAnimation = new Animation(ANIMATION_TIME_PERIOD, textureArray, Animation.PlayMode.LOOP);
        // get initial frame
        currentFrame = walkAnimation.getKeyFrame(stateTime);
    }

    public void update() {

        currentFrame = walkAnimation.getKeyFrame(0);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = LEFT;
            move(-X_MOVE_UNITS, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = RIGHT;
            move(X_MOVE_UNITS, 0);
        }
    }

    public void render(SpriteBatch batch) {
        sprite.setRegion(currentFrame);
        sprite.setFlip((direction == LEFT), false);
        sprite.draw(batch);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public void move(float x, float y) {
        setPosition(sprite.getX() + x, sprite.getY() + y);
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
    }
}
