package com.etsm.ETSM;

import com.etsm.ETSM.Repositories.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class)
@EnableJpaRepositories(basePackageClasses = ProductRepository.class)
public class EtsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtsmApplication.class, args);
	}
}
