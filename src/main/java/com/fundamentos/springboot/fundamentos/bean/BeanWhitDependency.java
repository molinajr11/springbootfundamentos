package com.fundamentos.springboot.fundamentos.bean;

public class BeanWhitDependency {
    // inyeccion de dependencia en otra dependencia
    private MyOperation myOperation;

    public BeanWhitDependency(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    public void printDepencency(){
        int numero=10;
        System.out.println(myOperation.suma(numero));
    }
}
