package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MybeanProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
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

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	//inyeccion de dependencias
	private final Log logger= LogFactory.getLog(FundamentosApplication.class);
	private final ComponentDependency componentDependency;
	private final MyBean myBean;

	private final MybeanProperties mybeanProperties;
	private final UserPojo userPojo;

	private final UserRepository userRepository;

	private final UserService userService;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,MyBean
			myBean ,MybeanProperties mybeanProperties,UserPojo userPojo,UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean=myBean;
		this.mybeanProperties=mybeanProperties;
		this.userPojo=userPojo;
		this.userRepository= userRepository;
		this.userService= userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {// ejemplosAnteriores();
	saveUsersInDataBase();
	getInformationJpqlFromUser();
	saveWithErrorTransaction();

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
				.forEach(user -> logger.info("User con query method "+user));

		userRepository.findbyNameLike("%user%")
				.forEach(user -> logger.info("User find by name like"+user));

		userRepository.findByNameOrEmail(null,"juan@gmail.com")
				.forEach(user -> logger.info("usuario por email o nombre"+user));

		userRepository.findByBrithDateBetween(LocalDate.of(2023,1,1),LocalDate.of(2023,12,31))
		.forEach(user->logger.info("usuario con intervalo de fechas: "+user));

		userRepository.findByNameLikeOrderByIdDesc("%user%")
				.forEach(user-> logger.info("usuario ordeado desendiente: "+user));

		userRepository.getAllByBirthDateAndEmail(LocalDate.of(2023,03,1),"molinajunior10@gmail.com")
				.orElseThrow(()-> new RuntimeException("No found user with parameters	"));
	}
	private void saveWithErrorTransaction () {
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());
		List<User> users = Arrays.asList(test1, test2, test3, test4);
		userService.saveTransactional(users);
	}
}
