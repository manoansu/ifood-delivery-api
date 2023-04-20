package pt.amane.ifooddeliveryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.DefaultRepositoryBaseClass;
import pt.amane.ifooddeliveryapi.infrestructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class IfoodDeliveryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IfoodDeliveryApiApplication.class, args);
    }

}
