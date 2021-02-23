package org.seariver.dungeonbob;

import com.badlogic.gdx.Game;

public class GameInitializer extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }
}
