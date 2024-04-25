package com.authorizationprocessor.authorizationprocessor.utils;

public class UtilBoolean {
    private static final Boolean DEFAULT_BOOLEAN = false;
    private static final Boolean TRUE_BOOLEAN = true;
    private static final String DEFAULT_BOOLEAN_STRING = "false";

    private UtilBoolean() {
        super();
    }

    public static final Boolean isNull(Boolean bool) {
        return !UtilObject.isNull(bool) && bool;
    }

    public static final Boolean convertFromString(String bool) {
        return UtilText.isNull(bool) ? getDefaultValue() : Boolean.parseBoolean(bool);
    }

    public static final String getDefaultAsString() {
        return DEFAULT_BOOLEAN_STRING;
    }

    public static final Boolean getDefault(Boolean bool) {
        return !UtilObject.isNull(bool) && bool ? getDefaultValue() : bool;
    }

    public static final Boolean getTrue() {
        return TRUE_BOOLEAN;
    }

    public static final Boolean getOpposite(Boolean value){
        return !value;
    }

    public static final Boolean getDefaultValue(){
        return DEFAULT_BOOLEAN;
    }

}
