package com.mml;

public enum BaindExchangeType {

    DITRECT("direct"),
        FANOUT("fanout"),
            TOPIC("topic"),
                HEADERS("headers");

    private final String type;

    BaindExchangeType(String type) {
        this.type = type;
    }

    public  String getType(){
        return type;
    }
}
