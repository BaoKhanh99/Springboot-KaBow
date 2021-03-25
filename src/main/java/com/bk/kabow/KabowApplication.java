package com.bk.kabow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
public class KabowApplication {

	public static void main(String[] args) {
		SpringApplication.run(KabowApplication.class, args);
	}

}
