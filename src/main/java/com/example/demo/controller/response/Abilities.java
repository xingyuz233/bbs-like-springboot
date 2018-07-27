package com.example.demo.controller.response;

public class Abilities {
    private boolean update;

    private boolean destroy;

    public Abilities(boolean update, boolean destroy) {
        this.update = update;
        this.destroy = destroy;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean getUpdate() {
        return this.update;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    public boolean getDestroy() {
        return this.destroy;
    }
}
