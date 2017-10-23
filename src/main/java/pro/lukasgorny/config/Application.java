package pro.lukasgorny.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pro.lukasgorny.model.Role;
import pro.lukasgorny.repository.RoleRepository;

import javax.annotation.PostConstruct;

/**
 * Created by lukaszgo on 2017-05-25.
 */

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pro.lukasgorny")
@EntityScan(basePackages = "pro.lukasgorny")
public class Application extends SpringBootServletInitializer {

    private final RoleRepository roleRepository;

    @Autowired
    public Application(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    private void insertRoles() {
        if (roleRepository.countAll() == 0) {
            Role admin = new Role("ADMIN");
            Role user = new Role("USER");

            roleRepository.save(admin);
            roleRepository.save(user);
        }
    }
}
