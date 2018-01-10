package pro.lukasgorny.config;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import pro.lukasgorny.dto.UserSaveDto;
import pro.lukasgorny.dto.auction.AuctionSaveDto;
import pro.lukasgorny.enums.RoleEnum;
import pro.lukasgorny.model.Category;
import pro.lukasgorny.model.Role;
import pro.lukasgorny.model.User;
import pro.lukasgorny.repository.CategoryRepository;
import pro.lukasgorny.repository.RoleRepository;
import pro.lukasgorny.service.auction.CreateAuctionService;
import pro.lukasgorny.service.registration.RegistrationService;
import pro.lukasgorny.service.user.UserService;

import javax.annotation.PostConstruct;

/**
 * Created by lukaszgo on 2017-05-25.
 */

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pro.lukasgorny")
@EntityScan(basePackages = "pro.lukasgorny")
@EnableScheduling
public class Application extends SpringBootServletInitializer {

    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final RegistrationService registrationService;
    private final UserService userService;
    private final CreateAuctionService createAuctionService;

    private final String version = UUID.randomUUID().toString();
    private final Date compilationDate = new Date();

    @Autowired
    public Application(RoleRepository roleRepository, CategoryRepository categoryRepository, RegistrationService registrationService, UserService userService, CreateAuctionService createAuctionService) {
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
        this.registrationService = registrationService;
        this.userService = userService;
        this.createAuctionService = createAuctionService;
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
            for (int i = 0; i < RoleEnum.values().length; i++) {
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

        Category lenovo = new Category();
        lenovo.setName("Lenovo");
        lenovo.setIsLeaf(true);
        laptops.getChildren().add(lenovo);

        Category dell = new Category();
        dell.setName("Dell");
        dell.setIsLeaf(true);
        laptops.getChildren().add(dell);

        Category cars = new Category();
        cars.setName("Samochody");
        cars.setIsLeaf(false);

        Category audi = new Category();
        audi.setName("Audi");
        audi.setIsLeaf(true);
        cars.getChildren().add(audi);

        categoryRepository.save(audi);
        categoryRepository.save(dell);
        categoryRepository.save(lenovo);
        categoryRepository.save(laptops);
        categoryRepository.save(electronics);
        categoryRepository.save(cars);
    }

    @PostConstruct
    private void createAdminAccount() {
        UserSaveDto userSaveDto = new UserSaveDto();
        userSaveDto.setEmail("admin@alledrogo.pl");
        userSaveDto.setPassword("admin");
        userSaveDto.getRoles().add(RoleEnum.ADMIN);
        userSaveDto.setBirthdayDay("20");
        userSaveDto.setBirthdayMonth("4");
        userSaveDto.setBirthdayYear("1992");
        registrationService.setUserSaveDto(userSaveDto);
        registrationService.register();

        User user = userService.getByEmail("admin@alledrogo.pl");
        user.setEnabled(true);
        user.setSellingBlocked(false);
        user.setBlocked(false);

        userService.save(user);
    }

    @PostConstruct
    private void createUserDemoAccount() {
        UserSaveDto userSaveDto = new UserSaveDto();
        userSaveDto.setEmail("user@alledrogo.pl");
        userSaveDto.setPassword("user");
        userSaveDto.getRoles().add(RoleEnum.USER);
        userSaveDto.setBirthdayDay("20");
        userSaveDto.setBirthdayMonth("4");
        userSaveDto.setBirthdayYear("1992");
        registrationService.setUserSaveDto(userSaveDto);
        registrationService.register();

        User user = userService.getByEmail("user@alledrogo.pl");
        user.setEnabled(true);
        user.setSellingBlocked(false);
        user.setBlocked(false);

        userService.save(user);
    }

    /*@PostConstruct
    private void createTestAuction() {
        User seller = userService.getByEmail("admin@alledrogo.pl");
        AuctionSaveDto auctionSaveDto = new AuctionSaveDto();
        auctionSaveDto.setCategoryId("1");
        auctionSaveDto.setTitle("Test");
        auctionSaveDto.setIsNew(true);
        auctionSaveDto.setEditorContent("Test");
        auctionSaveDto.setIsBid(true);
        auctionSaveDto.setIsBuyout(true);
        auctionSaveDto.setPrice(BigDecimal.ONE);
        auctionSaveDto.setAmount(10);
        auctionSaveDto.setBidMinimalPrice(BigDecimal.TEN);
        auctionSaveDto.setBidStartingPrice(BigDecimal.ONE);
        auctionSaveDto.setSeller(seller);
        auctionSaveDto.setAuctionDuration(7L);

        createAuctionService.setAuctionSaveDto(auctionSaveDto);
        createAuctionService.create();
    }*/

    public String getVersion() {
        return version;
    }

    public Date getCompilationDate() {
        return compilationDate;
    }
}
