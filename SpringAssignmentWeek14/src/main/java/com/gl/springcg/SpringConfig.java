package com.gl.springcg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan("com.gl.controller")
public class SpringConfig {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		//to find the files or to locate the file
		SpringResourceTemplateResolver resolver= new SpringResourceTemplateResolver();
		//Extensions
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".html");
		//where to create bean 
		resolver.setApplicationContext(applicationContext);
		resolver.setTemplateMode(TemplateMode.HTML);
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		//load the file found by the resolver
		SpringTemplateEngine engine = new SpringTemplateEngine();
		//mapping the engine to the resolver
		engine.setTemplateResolver(templateResolver());
		//for accepting the expression language eg:${tmep.name}
		engine.setEnableSpringELCompiler(true);
		return engine;
		
	}
	@Bean
	public ThymeleafViewResolver viewResolver() {
		//thymeleaf object
		ThymeleafViewResolver resolver= new ThymeleafViewResolver();
		//loader-engine
		resolver.setTemplateEngine(templateEngine());
		return resolver;
	}
	
	

}
