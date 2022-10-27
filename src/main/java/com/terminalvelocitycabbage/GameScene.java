package com.terminalvelocitycabbage;

import com.terminalvelocitycabbage.engine.client.renderer.components.FirstPersonCamera;
import com.terminalvelocitycabbage.engine.client.renderer.scenes.Scene;

public class GameScene extends Scene {

    public GameScene() {
        super(new FirstPersonCamera(60, 0.1f, 6000f), new GameInputHandler());
    }

    @Override
    public void tick(float deltaTime) {

    }

    @Override
    public void destroy() {

    }
}
