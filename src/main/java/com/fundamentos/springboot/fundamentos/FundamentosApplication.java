package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MybeanProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	//inyeccion de dependencias
	private final Log logger= LogFactory.getLog(FundamentosApplication.class);
	private final ComponentDependency componentDependency;
	private final MyBean myBean;

	private final MybeanProperties mybeanProperties;
	private final UserPojo userPojo;

	private final UserRepository userRepository;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,MyBean
			myBean ,MybeanProperties mybeanProperties,UserPojo userPojo,UserRepository userRepository) {
		this.componentDependency = componentDependency;
		this.myBean=myBean;
		this.mybeanProperties=mybeanProperties;
		this.userPojo=userPojo;
		this.userRepository= userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {// ejemplosAnteriores();
	saveUsersInDataBase();
	getInformationJpqlFromUser();

	}

	private  void ejemplosAnteriores(){
		componentDependency.hi();
		myBean.print();
		System.out.println(mybeanProperties.function());
		System.out.println(userPojo.getEmail());
		try {

		}catch (Exception e){
			logger.debug("debug");
		}
	}
	private void saveUsersInDataBase(){

		User usuarioUno= new User("juan","juan@gmail.com", LocalDate.of(2023,06,06));
		User usuariodos= new User("esteban","esteban@gmail.com", LocalDate.of(2023,06,26));
		User usuariotres= new User("usuariotresn","usuariotres@gmail.com", LocalDate.of(2023,05,27));
		User usuariocuatro= new User("usuariocuatro","usuariocuatro@gmail.com", LocalDate.of(2023,01,28));
		User usuariocinco= new User("usuariocinco","usuariocinco@gmail.com", LocalDate.of(2023,07,29));
		User usuariseis= new User("usuariseis","usuariseis@gmail.com", LocalDate.of(2023,12,30));
		List<User>list= Arrays.asList(usuarioUno,usuariodos,usuariotres,usuariocuatro,usuariocinco,usuariseis);
		list.stream().forEach(userRepository::save);
	}

	private  void getInformationJpqlFromUser(){
		logger.info(userRepository.findByEmail
				("juan@gmail.com").orElseThrow(()->new RuntimeException("Email no encontrado")));
		userRepository.findAndSort("user", Sort.by("id").descending())
				.forEach(user -> logger.info("usuario con metodo sort"+user));

		userRepository.findByName("juan")
				.forEach(user -> logger.info("User con quety method "+user));
	}
}
