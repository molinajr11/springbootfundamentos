package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MybeanProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	//inyeccion de dependencias
	private ComponentDependency componentDependency;
	private MyBean myBean;

	private MybeanProperties mybeanProperties;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,MyBean myBean ,MybeanProperties mybeanProperties) {
		this.componentDependency = componentDependency;
		this.myBean=myBean;
		this.mybeanProperties=mybeanProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		componentDependency.hi();
		myBean.print();
		System.out.println(mybeanProperties.function());
	}
}
