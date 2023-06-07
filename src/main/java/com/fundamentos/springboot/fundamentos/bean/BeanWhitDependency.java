package com.fundamentos.springboot.fundamentos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class BeanWhitDependency {
    // inyeccion de dependencia en otra dependencia
    Log LOGGER = LogFactory.getLog(BeanWhitDependency.class);
    private MyOperation myOperation;

    public BeanWhitDependency(MyOperation myOperation) {
        this.myOperation = myOperation;
    }


    public void printDepencency(){
        int numero=10;
        System.out.println(myOperation.suma(numero));
    }
}
