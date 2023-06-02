package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplement implements ComponentDependency {

    @Override
    public void hi() {
        System.out.println("implementacion de dependencia desde el componente");
    }
}
