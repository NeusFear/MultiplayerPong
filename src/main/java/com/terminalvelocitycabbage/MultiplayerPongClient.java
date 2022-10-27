package com.terminalvelocitycabbage;

import com.terminalvelocitycabbage.engine.client.ClientBase;
import com.terminalvelocitycabbage.engine.debug.Logger;

public class MultiplayerPongClient extends ClientBase {

    public static final String ID = "pong";

    public MultiplayerPongClient() {
        super(new Logger(ID), new MultiplayerPongRenderer(1900, 1000, "Pong Client", 60, false));

        //assign client context to this class
        instance = this;

        init();
        start();
    }

    public static void main(String[] args) {
        new MultiplayerPongClient();
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