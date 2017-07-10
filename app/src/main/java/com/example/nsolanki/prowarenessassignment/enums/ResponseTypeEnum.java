package com.example.nsolanki.prowarenessassignment.enums;

/**
 * Created by nsolanki on 7/10/2017.
 */

public enum ResponseTypeEnum {
    JSON_OBJECT(0), JSON_ARRAY(1), STRING(2), IMAGE(3);

    private int responseTypeValue;


    ResponseTypeEnum(final int responseTypeValue) {
        this.responseTypeValue = responseTypeValue;
    }

    ResponseTypeEnum getEnum(int value) {
        for (ResponseTypeEnum e : ResponseTypeEnum.values()) {
            if (e.responseTypeValue == value) {
                return e;
            }
        }
        return null;// not found
    }

    public static int getValue(ResponseTypeEnum responseTypeEnum) {
        for (ResponseTypeEnum e : ResponseTypeEnum.values()) {
            if (e == responseTypeEnum) {
                return e.responseTypeValue;
            }
        }
        return ResponseTypeEnum.getValue(ResponseTypeEnum.STRING);// not found
    }
}
