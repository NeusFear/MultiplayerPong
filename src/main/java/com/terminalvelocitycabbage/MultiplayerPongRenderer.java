package com.terminalvelocitycabbage;

import com.terminalvelocitycabbage.engine.client.renderer.Renderer;
import com.terminalvelocitycabbage.engine.client.renderer.components.Camera;
import com.terminalvelocitycabbage.engine.client.renderer.gameobjects.ModeledGameObject;
import com.terminalvelocitycabbage.engine.client.renderer.shader.ShaderProgram;
import com.terminalvelocitycabbage.engine.prefabs.camera.firstperson.firstperson.FirstPersonCamera;
import com.terminalvelocitycabbage.engine.prefabs.camera.firstperson.firstperson.FirstPersonInputHandler;

import static com.terminalvelocitycabbage.GameResourceHandler.SHADER;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class MultiplayerPongRenderer extends Renderer {

    public MultiplayerPongRenderer(int width, int height, String title, float tickRate, boolean debugMode) {
        super(width, height, title, tickRate, debugMode);
    }

    @Override
    public void init() {
        super.init();
        getWindow().setvSync(true);

        //Setup Shaders
        shaderHandler.newProgram("default").queueDefaultShaders(SHADER, MultiplayerPongClient.ID).build();

        //Register scene
        getSceneHandler().addScene("game_scene", new GameScene());

        //Load the scene
        getSceneHandler().loadScene("game_scene");
    }

    private void setupShader(Camera camera, ShaderProgram shaderProgram) {
        //Camera
        shaderProgram.setUniform("projectionMatrix", camera.getProjectionMatrix());
        //Lighting
        shaderProgram.setUniform("ambientLight", 0.3f, 0.3f, 0.3f);
        shaderProgram.setUniform("specularPower", 10.0f); //Reflected light intensity
        shaderProgram.setUniform("directionalLight", sceneHandler.getActiveScene().objectHandler.getObject("sun"));
    }

    private void renderGameObjects(Camera camera, ShaderProgram shaderProgram) {

        shaderProgram.enable();
        setupShader(camera, shaderProgram);

        //Draw whatever changes were pushed
        for (ModeledGameObject gameObject : sceneHandler.getActiveScene().getObjectsOfType(ModeledGameObject.class)) {

            gameObject.update();

            shaderProgram.setUniform("modelViewMatrix", gameObject.getModelViewMatrix(camera.getViewMatrix()));
            shaderProgram.setUniform("normalTransformationMatrix", gameObject.getTransformationMatrix());
            shaderProgram.setUniform("material", gameObject.getModel().getMaterial());

            gameObject.render();
        }

        shaderProgram.disable();
    }

    @Override
    public void loop() {
        super.loop();

        //Setup the frame for drawing
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        //This is just a good measure to take since likely in the future you will have more than one scene
        if (sceneHandler.getActiveScene() instanceof GameScene scene) {

            //Get the input handler and the camera from the scene
            FirstPersonInputHandler inputHandler = (FirstPersonInputHandler) scene.getInputHandler();
            FirstPersonCamera camera = ((FirstPersonCamera) scene.getCamera());

            //Update the camera with events from the input handler
            camera.update(inputHandler, getDeltaTimeInMillis());

            //Render the scene
            renderGameObjects(getSceneHandler().getActiveScene().getCamera(), shaderHandler.getShader("default"));

            //Reset the input handler
            inputHandler.resetDeltas();
        }

        push();
    }
}
