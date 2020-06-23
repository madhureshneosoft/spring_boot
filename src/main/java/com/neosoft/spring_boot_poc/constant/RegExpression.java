package com.neosoft.spring_boot_poc.constant;

public enum RegExpression {
    USERNAME_REGEXP("([A-Za-z0-9]){4,12}"),
    PASSWORD_REGEXP("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,36}$"),
    EMAIL_REGEXP("^([A-Za-z0-9])(([.])?[0-9a-z])*[@]([a-z])+([.]([a-z])+){1,3}"),
    MOBILE_REGEXP("^[789](\\d){9}"),
    NAME_REGEXP("([a-zA-Z]){2,16}"),
    PINCODE_REGEXP("([\\d]){6}");

    private final String regExp;

    RegExpression(String expression) {
        this.regExp = expression;
    }

    public String getRegExp() {
        return regExp;
    }
}

