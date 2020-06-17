package com.neosoft.spring_boot_poc;

public enum RegExpression {
    MOBILE_REGEXP("^[789]([\\d]){9}"),
    EMAIL_REGEXP("^([A-Za-z0-9])(([.])?[0-9a-z])*[@]([a-z])+([.]([a-z])+){2,3}"),
    PINCODE_REGEXP("(\\d){6}"),
    DATE_REGEXP("(\\d){4}[-](\\d){2}[-](\\d){2}");

    private final String regExp;

    RegExpression(String expression) {
        this.regExp = expression;
    }

    public String getRegExp() {
        return regExp;
    }
}
