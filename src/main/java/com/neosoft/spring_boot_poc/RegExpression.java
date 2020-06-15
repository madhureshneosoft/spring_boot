package com.neosoft.spring_boot_poc;

public enum RegExpression {
    MOBILE_REGEXP("^[789][\\\\d]{9}"),
    EMAIL_REGEXP("^([a-z])(([.])?[0-9a-z])*[@]([a-z])+[.]([a-z]){2,3}");

    private final String regExp;

    RegExpression(String expression) {
        this.regExp = expression;
    }

    public String getRegExp() {
        return regExp;
    }
}
