package com.prueba.cev.prueba2.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//Clase de configuración creada por nosotros
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Notaciones para definir la clase de configuración
@Configuration //Define que es una clase de configuración
@EnableWebSecurity //Define que vamos a sobreescribir la configuración de seguridad.
@EnableGlobalMethodSecurity (prePostEnabled = true) //Habilitar el filtrado mediante notaciones sobre los métodos

//Extiende de la clase WebSecurity... ya que tiene que ser del mismo tipo que la clase que va a sustituir
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	//Sobreescribimos los métodos que vienen por defecto:
	   @Override
	      protected void configure(HttpSecurity httpSecurity) throws Exception {
	          httpSecurity.csrf().disable()
	                  .authorizeRequests().
	                  //Permitimos al usuario Admin y user  todo el acceso a cualquier sitio mientras sea una petición GET
	                  antMatchers(HttpMethod.GET,"/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
	                  //Permitiremos hacer cualquier petición sólo al usuario ADMIN
	                  .anyRequest().hasAuthority("ROLE_ADMIN")
	                  .and().httpBasic();
	      }   
	   
	   //Creando usuarios con diferentes roles, estamos sobrescribiendo cómo se crean los usuarios
	   
	   @Autowired // Con Autowired le estamos diciendo que el Authentication manager, lo va a obtener del contexto
	   
	   //AuthenticationManager es el que dice como se autentican los usuarios, (de donde saco el usuario y contraseña y el rol de cada usuario)
	   //Al sobrescribir el authentication manager, podremos hacer que ese usuario y pass se busque donde queramos (bases de datos, app de terceros...)
	      public void configureGlobal(AuthenticationManagerBuilder authentication) 
	             throws Exception
	      {
		   //Definimos una authentication en memoria y damos de alta estos dos usuarios y estos dos passwords con sus roles
	          authentication.inMemoryAuthentication()
	                  .withUser("admin")
	                  .password(passwordEncoder().encode("admin"))
	                  .authorities("ROLE_ADMIN")
	                  .and()
	                  .withUser("user")
	                  .password(passwordEncoder().encode("user"))
	                  .authorities("ROLE_USER");               
	    }    

	   //Password encoder lo que hace es codificar la contraseña para que el basic Auth la identifique correctamente
	      @Bean
	      public PasswordEncoder passwordEncoder() {
	          return new BCryptPasswordEncoder();
	      }
}
