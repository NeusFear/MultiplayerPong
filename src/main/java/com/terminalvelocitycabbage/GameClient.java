package com.terminalvelocitycabbage;

import com.terminalvelocitycabbage.engine.client.ClientBase;
import com.terminalvelocitycabbage.engine.debug.Logger;

public class GameClient extends ClientBase {

    public static final String ID = "example";

    public GameClient() {
        super(new Logger(ID), new GameRenderer(1900, 1000, "Pong Client", 60, false));

        //assign client context to this class
        instance = this;

        init();
        start();
    }

    public static void main(String[] args) {
        new GameClient();
    }

    @Override
    public void init() {
        super.init();
        getRenderer().init();
    }

    @Override
    public void start() {
        super.start();
        getRenderer().run();
    }
}