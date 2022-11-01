package com.terminalvelocitycabbage;

import com.terminalvelocitycabbage.engine.client.renderer.components.Window;
import com.terminalvelocitycabbage.engine.client.renderer.gameobjects.lights.DirectionalLight;
import com.terminalvelocitycabbage.engine.client.renderer.scenes.Scene;
import com.terminalvelocitycabbage.engine.prefabs.camera.firstperson.firstperson.FirstPersonCamera;
import com.terminalvelocitycabbage.engine.prefabs.gameobjects.Cube;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class GameScene extends Scene {

    public GameScene() {
        super(new FirstPersonCamera(60, 0.1f, 6000f), new GameInputHandler());
    }

    @Override
    public void init(Window window) {
        super.init(window);

        objectHandler.add("cube", new Cube(new Vector3f(0f, 0f, -3f), new Vector4f(1, 0, 0, 1), 10f)).bind();

        objectHandler.add("sun", new DirectionalLight(new Vector3f(-0.68f, 0.55f, 0.42f), new Vector4f(1, 1, 0.5f, 1), 0.3f));
    }

    @Override
    public void tick(float deltaTime) {

    }

    @Override
    public void destroy() {

    }
}
