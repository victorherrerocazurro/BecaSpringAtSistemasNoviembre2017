package com.atsistemas.persistencia.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.atsistemas.persistencia.core.servicios","com.atsistemas.persistencia.core.persistencia.hibernate"})
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
public class ConfiguracionHibernate {

	@Bean
	@Autowired
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}
	
	@Bean
	@Autowired
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		//Entidades
		lsfb.setPackagesToScan("com.atsistemas.persistencia.core.entidades");
		//Origen de datos
		lsfb.setDataSource(ds);
		//Otras propiedades
		Properties hibernateProperties = lsfb.getHibernateProperties();
		
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "true");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
		//hibernateProperties.setProperty("hibernate.current_session_context_class","thread");
		
		return lsfb;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
	
	@Bean
	public DataSource miDataSource(
			@Value("${database.url}") String url,
			@Value("${database.user}") String user,
			@Value("${database.password}") String password
			) {
		BasicDataSource ds = new BasicDataSource();
		
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		
		return ds;
	}
	
}
