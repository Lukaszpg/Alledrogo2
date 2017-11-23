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
import pro.lukasgorny.dto.UserDto;
import pro.lukasgorny.enums.RoleEnum;
import pro.lukasgorny.model.Category;
import pro.lukasgorny.model.Role;
import pro.lukasgorny.model.User;
import pro.lukasgorny.repository.CategoryRepository;
import pro.lukasgorny.repository.RoleRepository;
import pro.lukasgorny.service.registration.RegistrationService;
import pro.lukasgorny.service.user.UserService;

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
    private final RegistrationService registrationService;
    private final UserService userService;

    private final String version = UUID.randomUUID().toString();
    private final Date compilationDate = new Date();

    @Autowired
    public Application(RoleRepository roleRepository, CategoryRepository categoryRepository, RegistrationService registrationService, UserService userService) {
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
        this.registrationService = registrationService;
        this.userService = userService;
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
            for(int i = 0; i < RoleEnum.values().length; i++) {
                Role toInsert = new Role(RoleEnum.values()[i].name());
                toInsert.setDeleted(false);
                roleRepository.save(toInsert);
            }
        }
    }

    @PostConstruct
    private void createExampleCategories() {
        Category electronics = new Category();
        electronics.setName("Elektronika");
        electronics.setIsLeaf(false);

        Category laptops = new Category();
        laptops.setName("Laptopy");
        laptops.setIsLeaf(false);

        electronics.getChildren().add(laptops);
        electronics.setIsLeaf(false);

        Category test = new Category();
        test.setName("Lenovo");
        test.setIsLeaf(true);
        laptops.getChildren().add(test);

        categoryRepository.save(test);
        categoryRepository.save(laptops);
        categoryRepository.save(electronics);
    }

    @PostConstruct
    private void createAdminAccount() {
        UserDto userDto = new UserDto();
        userDto.setEmail("lukasz.p.gorny@gmail.com");
        userDto.setPassword("adminadmin");
        userDto.getRoles().add(RoleEnum.ADMIN);
        userDto.setBirthdayDay("20");
        userDto.setBirthdayMonth("4");
        userDto.setBirthdayYear("1992");
        registrationService.setUserDto(userDto);
        registrationService.register();

        User user = userService.getByEmail("lukasz.p.gorny@gmail.com");
        user.setEnabled(true);
        user.setSellingBlocked(false);
        user.setBlocked(false);

        userService.save(user);
    }

    public String getVersion() {
        return version;
    }

    public Date getCompilationDate() {
        return compilationDate;
    }
}
