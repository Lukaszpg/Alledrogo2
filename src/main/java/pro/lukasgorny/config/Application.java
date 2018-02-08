package pro.lukasgorny.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import pro.lukasgorny.dto.UserSaveDto;
import pro.lukasgorny.enums.RoleEnum;
import pro.lukasgorny.model.Category;
import pro.lukasgorny.model.Role;
import pro.lukasgorny.model.User;
import pro.lukasgorny.repository.CategoryRepository;
import pro.lukasgorny.repository.RoleRepository;
import pro.lukasgorny.service.auction.CreateAuctionService;
import pro.lukasgorny.service.registration.RegistrationService;
import pro.lukasgorny.service.storage.StorageProperties;
import pro.lukasgorny.service.storage.exception.StorageException;
import pro.lukasgorny.service.user.UserService;

/**
 * Created by lukaszgo on 2017-05-25.
 */

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pro.lukasgorny")
@EntityScan(basePackages = "pro.lukasgorny")
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
@EnableAsync
public class Application extends SpringBootServletInitializer {

    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final RegistrationService registrationService;
    private final UserService userService;
    private final CreateAuctionService createAuctionService;
    private final StorageProperties storageProperties;

    private final String version = UUID.randomUUID().toString();
    private final Date compilationDate = new Date();

    @Autowired
    public Application(RoleRepository roleRepository, CategoryRepository categoryRepository, RegistrationService registrationService, UserService userService, CreateAuctionService createAuctionService,
            StorageProperties storageProperties) {
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
        this.registrationService = registrationService;
        this.userService = userService;
        this.createAuctionService = createAuctionService;
        this.storageProperties = storageProperties;
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
    private void createElectronicsCategories() {
        if(categoryRepository.countAll() == 0) {
            Category electronics = new Category();
            electronics.setName("Elektronika");
            electronics.setIsLeaf(false);

            Category phonesGeneral = new Category();
            phonesGeneral.setName("Telefony i akcesoria");
            phonesGeneral.setIsLeaf(false);
            electronics.getChildren().add(phonesGeneral);

            Category smartphones = new Category();
            smartphones.setName("Smartfony i telefony komórkowe");
            smartphones.setIsLeaf(false);
            phonesGeneral.getChildren().add(smartphones);

            Category etuis = new Category();
            etuis.setName("Etui i pokrowce");
            etuis.setIsLeaf(false);
            phonesGeneral.getChildren().add(etuis);

            Category powerbanks = new Category();
            powerbanks.setName("Powerbanki");
            powerbanks.setIsLeaf(false);
            phonesGeneral.getChildren().add(powerbanks);

            Category smartwatches = new Category();
            smartwatches.setName("Smartwatche");
            smartwatches.setIsLeaf(false);
            phonesGeneral.getChildren().add(smartwatches);

            Category pcsAndTablets = new Category();
            pcsAndTablets.setName("Komputery i tablety");
            pcsAndTablets.setIsLeaf(false);
            electronics.getChildren().add(pcsAndTablets);

            categoryRepository.save(smartwatches);
            categoryRepository.save(etuis);
            categoryRepository.save(smartphones);
            categoryRepository.save(powerbanks);
            categoryRepository.save(phonesGeneral);
            categoryRepository.save(pcsAndTablets);
            categoryRepository.save(electronics);
        }
    }

    @PostConstruct
    private void createAdminAccount() {
        if(userService.getByEmail("admin@auctionify.pl") == null) {
            UserSaveDto userSaveDto = new UserSaveDto();
            userSaveDto.setEmail("admin@auctionify.pl");
            userSaveDto.setPassword("admin");
            userSaveDto.getRoles().add(RoleEnum.ADMIN);
            userSaveDto.setBirthdayDay("20");
            userSaveDto.setBirthdayMonth("4");
            userSaveDto.setBirthdayYear("1992");
            registrationService.setUserSaveDto(userSaveDto);
            registrationService.register();

            User user = userService.getByEmail("admin@auctionify.pl");
            user.setEnabled(true);
            user.setSellingBlocked(false);
            user.setBlocked(false);

            userService.save(user);
        }
    }

    @PostConstruct
    private void createUserDemoAccount() {
        if(userService.getByEmail("user@auctionify.pl") == null) {
            UserSaveDto userSaveDto = new UserSaveDto();
            userSaveDto.setEmail("user@auctionify.pl");
            userSaveDto.setPassword("user");
            userSaveDto.getRoles().add(RoleEnum.USER);
            userSaveDto.setBirthdayDay("20");
            userSaveDto.setBirthdayMonth("4");
            userSaveDto.setBirthdayYear("1992");
            registrationService.setUserSaveDto(userSaveDto);
            registrationService.register();

            User user = userService.getByEmail("user@auctionify.pl");
            user.setEnabled(true);
            user.setSellingBlocked(false);
            user.setBlocked(false);

            userService.save(user);
        }
    }

    @PostConstruct
    private void createPhotoSaveDirectory() {
        try {
            Files.createDirectories(Paths.get(storageProperties.getLocation()));
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    public String getVersion() {
        return version;
    }

    public Date getCompilationDate() {
        return compilationDate;
    }
}
