package org.seariver.dungeonbob.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Bob {

    public Sprite bobSprite; //sprite to display Bob
    private static final float X_MOVE_UNITS = 3f;// units bob will move in x direction

    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    TextureRegion currentFrame;
    float stateTime;

    public static final float BOB_RESIZE_FACTOR = 750f;
    private static int ANIMATION_FRAME_SIZE = 8; // this specifies the number of frames(images) that we are using for animation
    private static float ANIMATION_TIME_PERIOD = 0.08f;// this specifies the time between two consecutive frames of animation

    enum Direction {LEFT, RIGHT}

    Direction direction = Direction.RIGHT; //denotes player's direction. defaulted to right

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = Direction.LEFT;
            move(-X_MOVE_UNITS, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = Direction.RIGHT;
            move(X_MOVE_UNITS, 0);
        } else {
            currentFrame = walkAnimation.getKeyFrame(0, true);
        }
    }

    public void render(SpriteBatch batch) {
        bobSprite.setRegion(currentFrame);
        if (direction == Direction.RIGHT) {
            bobSprite.setFlip(false, false);
        } else {
            bobSprite.setFlip(true, false);
        }
        bobSprite.draw(batch);
    }

    public void setPosition(float x, float y) {
        bobSprite.setPosition(x, y);
    }

    public void move(float x, float y) {
        setPosition(bobSprite.getX() + x, bobSprite.getY() + y);
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
    }

    public void initialize(float width, float height, Texture walkSheet) {

        this.walkSheet = walkSheet; // save the sprite sheet

        // instantiate bob sprite
        bobSprite = new Sprite();
        bobSprite.setSize(
                (walkSheet.getWidth() / ANIMATION_FRAME_SIZE) * (width / BOB_RESIZE_FACTOR),
                walkSheet.getHeight() * (height / BOB_RESIZE_FACTOR));
        setPosition(width / 2f, 0);

        //split the sprite sheet into different textures
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / ANIMATION_FRAME_SIZE, walkSheet.getHeight());
        Array<TextureRegion> textureArray = new Array<>();
        for (int c = 0; c < ANIMATION_FRAME_SIZE; c++) {
            textureArray.add(tmp[0][c]);
        }
        // create a new animation sequence with the walk frames and time period of specified seconds
        walkAnimation = new Animation(ANIMATION_TIME_PERIOD, textureArray, Animation.PlayMode.LOOP);
        // set the animation to loop
        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);
        // get initial frame
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
    }
}
