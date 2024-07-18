package hello.kcs_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class KcsAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(KcsAssignmentApplication.class, args);
	}

}
