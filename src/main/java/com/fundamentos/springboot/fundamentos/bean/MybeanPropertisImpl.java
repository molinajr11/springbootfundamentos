package com.fundamentos.springboot.fundamentos.bean;

public class MybeanPropertisImpl implements  MybeanProperties{
    private  String name;
    private String apellido;

    public MybeanPropertisImpl(String name, String apellido) {
        this.name = name;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return name+""+apellido;
    }
}
