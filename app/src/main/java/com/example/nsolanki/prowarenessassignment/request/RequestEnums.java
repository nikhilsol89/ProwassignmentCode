package com.example.nsolanki.prowarenessassignment.request;


/*This enum is basically used to define wether it's a network request
or a DB request i.e. case of offline feature if needed at later  stages*/
public enum RequestEnums {

    ASSET(0),
    DATABASE(1),
    NETWORK(2);

    private final int dispatcher;

    RequestEnums(int dispatcher) {
        this.dispatcher = dispatcher;
    }

    public int getRequestEnum() {
        return this.dispatcher;
    }
}
