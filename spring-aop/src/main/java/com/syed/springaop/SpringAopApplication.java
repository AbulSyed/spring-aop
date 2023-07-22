package com.syed.springaop;

import com.syed.springaop.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountService accountService) {

		return runner -> {
			accountService.addAccount();
			accountService.getAccount();
			accountService.setAccount();
			accountService.getAllAccounts();

			try {
				System.out.println("commandLineRunner calling accountService.simulateException();");
				accountService.simulateException();
			} catch (Exception ex) {
				System.out.println("main program caught exception: " + ex.getMessage());
			}

			accountService.timeMethod();
		};
	}
}
