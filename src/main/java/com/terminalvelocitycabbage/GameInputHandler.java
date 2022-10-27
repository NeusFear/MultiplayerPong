package com.terminalvelocitycabbage;

import com.terminalvelocitycabbage.engine.client.input.InputHandler;
import com.terminalvelocitycabbage.engine.client.input.KeyBind;
import com.terminalvelocitycabbage.engine.client.renderer.components.Window;
import com.terminalvelocitycabbage.engine.debug.Log;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

public class GameInputHandler extends InputHandler {

    public static KeyBind CLOSE;

    @Override
    public void init(Window window) {
        super.init(window);

        CLOSE = new KeyBind(GLFW_KEY_ESCAPE);
    }

    @Override
    public void processInput(KeyBind keyBind) {
        if (CLOSE.isKeyPressed()) {
            keyBind.getWindow().queueClose();
        }
    }
}
