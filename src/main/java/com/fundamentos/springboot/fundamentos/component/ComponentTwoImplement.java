package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency  {
    @Override
    public void hi() {
        System.out.println("Componente dos works");
    }
}
