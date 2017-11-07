package com.atsistemas.propiedades.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.atsistemas.propiedades.core.persistencia")
public class ConfiguracionPersistencia {

	@Bean
	public DataSource miDataSource() {
		BasicDataSource ds = new BasicDataSource();
		
		ds.setUrl("jdbc:mysql://localhost:3310/test");
		ds.setUsername("root");
		ds.setPassword("root");
		
		return ds;
	}
	
}
