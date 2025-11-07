package com.back;

public class Rq {
    private final String actionName;

    public Rq(String cmd) {
        actionName = cmd;
    }

    public String getParam(String name, String defaultValue) {
        return defaultValue;
    }

    public int getParamAsInt(String name, int defaultValue) {
        return defaultValue;
    }

    public String getActionName() {
        return actionName;
    }
}
