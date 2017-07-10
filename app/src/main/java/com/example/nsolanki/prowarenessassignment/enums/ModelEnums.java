package com.example.nsolanki.prowarenessassignment.enums;

/**
 * Created by nsolanki on 2/10/2017.
 */

public enum ModelEnums {

    NAMES_LIST(0);


    private final int modelEnum;

    ModelEnums(int modelEnum) {
        this.modelEnum = modelEnum;
    }

    public int getModelEnum() {
        return this.modelEnum;
    }

}
