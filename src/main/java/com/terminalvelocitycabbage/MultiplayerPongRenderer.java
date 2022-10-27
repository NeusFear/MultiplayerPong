package com.terminalvelocitycabbage;

import com.terminalvelocitycabbage.engine.client.renderer.Renderer;

public class MultiplayerPongRenderer extends Renderer {

    public MultiplayerPongRenderer(int width, int height, String title, float tickRate, boolean debugMode) {
        super(width, height, title, tickRate, debugMode);
    }

    @Override
    public void init() {
        super.init();

        //Register scene
        getSceneHandler().addScene("game_scene", new GameScene());

        //Load the scene
        getSceneHandler().loadScene("game_scene");
    }

    @Override
    public void loop() {
        super.loop();
        push();
    }
}
