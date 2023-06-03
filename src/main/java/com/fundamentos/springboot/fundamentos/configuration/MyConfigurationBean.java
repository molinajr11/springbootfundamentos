package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanImplement;
import com.fundamentos.springboot.fundamentos.bean.MyOperation;
import com.fundamentos.springboot.fundamentos.bean.MyOperationImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
   @Bean
    public MyBean beanOperation(){
        return new MyBeanImplement();
    }

    @Bean
    public MyOperation sumaOperation(){
       return new MyOperationImplement();
    }
}
