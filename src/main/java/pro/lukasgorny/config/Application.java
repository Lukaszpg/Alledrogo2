package pro.lukasgorny.config;

import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pro.lukasgorny.enums.RoleEnum;
import pro.lukasgorny.model.Category;
import pro.lukasgorny.model.Role;
import pro.lukasgorny.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    private final String version = UUID.randomUUID().toString();
    private final Date compilationDate = new Date();

    @Autowired
    public Application(RoleRepository roleRepository, CategoryRepository categoryRepository) {
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
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
            Role admin = new Role(RoleEnum.ADMIN.name());
            Role user = new Role(RoleEnum.USER.name());

            roleRepository.save(admin);
            roleRepository.save(user);
        }
    }

    @PostConstruct
    private void insertExampleCategories() {
        Category electronics = new Category();
        electronics.setName("Elektronika");
        electronics.setIsLeaf(false);

        Category laptops = new Category();
        laptops.setName("Laptopy");
        laptops.setIsLeaf(false);

        electronics.getChildren().add(laptops);
        electronics.setIsLeaf(false);

        Category test = new Category();
        test.setName("test");
        test.setIsLeaf(true);
        laptops.getChildren().add(test);

        categoryRepository.save(test);
        categoryRepository.save(laptops);
        categoryRepository.save(electronics);
    }

    public String getVersion() {
        return version;
    }

    public Date getCompilationDate() {
        return compilationDate;
    }
}
