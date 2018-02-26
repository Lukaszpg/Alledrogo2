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

import pro.lukasgorny.dto.user.UserSaveDto;
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
    private void createCosmeticsCategories() {
        Category cosmeticsFromDB = categoryRepository.findByName("Uroda i zdrowie");

        if(cosmeticsFromDB == null) {
            Category cosmetics = new Category();
            cosmetics.setName("Uroda i zdrowie");
            cosmetics.setIsLeaf(false);

            Category cosmeticsMen = new Category();
            cosmeticsMen.setName("Perfumy męskie");
            cosmeticsMen.setIsLeaf(true);
            cosmetics.getChildren().add(cosmeticsMen);

            Category cosmeticsWomen = new Category();
            cosmeticsWomen.setName("Perfumy damskie");
            cosmeticsWomen.setIsLeaf(true);
            cosmetics.getChildren().add(cosmeticsWomen);

            Category makeup = new Category();
            makeup.setName("Makijaż");
            makeup.setIsLeaf(true);
            cosmetics.getChildren().add(makeup);

            Category devices = new Category();
            devices.setName("Urządzenia");
            devices.setIsLeaf(true);
            cosmetics.getChildren().add(devices);

            Category diets = new Category();
            diets.setName("Suplementy diety");
            diets.setIsLeaf(true);
            cosmetics.getChildren().add(diets);

            Category hair = new Category();
            hair.setName("Pielęgnacja włosów");
            hair.setIsLeaf(true);
            cosmetics.getChildren().add(hair);

            Category body = new Category();
            body.setName("Pielęgnacja ciała");
            body.setIsLeaf(true);
            cosmetics.getChildren().add(body);

            categoryRepository.save(makeup);
            categoryRepository.save(cosmeticsWomen);
            categoryRepository.save(cosmeticsMen);
            categoryRepository.save(hair);
            categoryRepository.save(body);
            categoryRepository.save(devices);
            categoryRepository.save(diets);
            categoryRepository.save(cosmetics);
        }
    }

    @PostConstruct
    private void createEntertainmentCategories() {
        Category entertainmentFromDB = categoryRepository.findByName("Rozrywka i kultura");

        if(entertainmentFromDB == null) {
            Category entertainment = new Category();
            entertainment.setName("Rozrywka i kultura");
            entertainment.setIsLeaf(false);

            Category books = new Category();
            books.setName("Książki");
            books.setIsLeaf(true);
            entertainment.getChildren().add(books);

            Category comics = new Category();
            comics.setName("Komiksy");
            comics.setIsLeaf(true);
            entertainment.getChildren().add(comics);

            Category games = new Category();
            games.setName("Gry");
            games.setIsLeaf(true);
            entertainment.getChildren().add(games);

            Category music = new Category();
            music.setName("Muzyka");
            music.setIsLeaf(true);
            entertainment.getChildren().add(music);

            Category movies = new Category();
            movies.setName("Filmy");
            movies.setIsLeaf(true);
            entertainment.getChildren().add(movies);

            Category ebooks = new Category();
            ebooks.setName("Ebooki");
            ebooks.setIsLeaf(true);
            entertainment.getChildren().add(ebooks);

            Category instruments = new Category();
            instruments.setName("Instrumenty");
            instruments.setIsLeaf(true);
            entertainment.getChildren().add(instruments);

            categoryRepository.save(ebooks);
            categoryRepository.save(movies);
            categoryRepository.save(games);
            categoryRepository.save(instruments);
            categoryRepository.save(comics);
            categoryRepository.save(books);
            categoryRepository.save(music);
            categoryRepository.save(entertainment);
        }
    }

    @PostConstruct
    private void createCollectionsCategories() {
        Category collectionsFromDB = categoryRepository.findByName("Kolekcje i sztuka");

        if(collectionsFromDB == null) {
            Category collections = new Category();
            collections.setName("Kolekcje i sztuka");
            collections.setIsLeaf(false);

            Category antics = new Category();
            antics.setName("Antyki");
            antics.setIsLeaf(true);
            collections.getChildren().add(antics);

            Category paintings = new Category();
            paintings.setName("Malarstwo");
            paintings.setIsLeaf(true);
            collections.getChildren().add(paintings);

            Category militarias = new Category();
            militarias.setName("Militaria");
            militarias.setIsLeaf(true);
            collections.getChildren().add(militarias);

            Category models = new Category();
            models.setName("Modelarstwo");
            models.setIsLeaf(true);
            collections.getChildren().add(models);

            Category nimuzm = new Category();
            nimuzm.setName("Numizmatyka");
            nimuzm.setIsLeaf(true);
            collections.getChildren().add(nimuzm);

            categoryRepository.save(antics);
            categoryRepository.save(paintings);
            categoryRepository.save(militarias);
            categoryRepository.save(models);
            categoryRepository.save(nimuzm);
            categoryRepository.save(collections);
        }
    }

    @PostConstruct
    private void createSportCategories() {
        Category sportsFromDB = categoryRepository.findByName("Sport i wypoczynek");

        if(sportsFromDB == null) {
            Category sports = new Category();
            sports.setName("Sport i wypoczynek");
            sports.setIsLeaf(false);

            Category bikes = new Category();
            bikes.setName("Rowery");
            bikes.setIsLeaf(true);
            sports.getChildren().add(bikes);

            Category fitness = new Category();
            fitness.setName("Siłownia i fitness");
            fitness.setIsLeaf(true);
            sports.getChildren().add(fitness);

            Category rybki = new Category();
            rybki.setName("Wędkarstwo");
            rybki.setIsLeaf(true);
            sports.getChildren().add(rybki);

            Category turistics = new Category();
            turistics.setName("Turystyka");
            turistics.setIsLeaf(true);
            sports.getChildren().add(turistics);

            Category teamSports = new Category();
            teamSports.setName("Sporty drużynowe");
            teamSports.setIsLeaf(true);
            sports.getChildren().add(teamSports);

            Category army = new Category();
            army.setName("Militaria");
            army.setIsLeaf(true);
            sports.getChildren().add(army);

            Category running = new Category();
            running.setName("Bieganie");
            running.setIsLeaf(true);
            sports.getChildren().add(running);

            Category holidays = new Category();
            holidays.setName("Wakacje");
            holidays.setIsLeaf(true);
            sports.getChildren().add(holidays);

            categoryRepository.save(bikes);
            categoryRepository.save(fitness);
            categoryRepository.save(rybki);
            categoryRepository.save(turistics);
            categoryRepository.save(teamSports);
            categoryRepository.save(army);
            categoryRepository.save(running);
            categoryRepository.save(holidays);
            categoryRepository.save(sports);
        }
    }

    @PostConstruct
    private void createHomeCategories() {
        Category homeFromDB = categoryRepository.findByName("Dom i ogród");

        if(homeFromDB == null) {
            Category home = new Category();
            home.setName("Dom i ogród");
            home.setIsLeaf(false);

            Category furniture = new Category();
            furniture.setName("Meble");
            furniture.setIsLeaf(true);
            home.getChildren().add(furniture);

            Category garden = new Category();
            garden.setName("Ogród");
            garden.setIsLeaf(true);
            home.getChildren().add(garden);

            Category lightning = new Category();
            lightning.setName("Oświetlenie");
            lightning.setIsLeaf(true);
            home.getChildren().add(lightning);

            Category arcitecture = new Category();
            arcitecture.setName("Budownictwo");
            arcitecture.setIsLeaf(true);
            home.getChildren().add(arcitecture);

            Category kitchenUtilities = new Category();
            kitchenUtilities.setName("Przybory kuchenne");
            kitchenUtilities.setIsLeaf(true);
            home.getChildren().add(kitchenUtilities);

            Category utilities = new Category();
            utilities.setName("Wyposażenie");
            utilities.setIsLeaf(true);
            home.getChildren().add(utilities);

            categoryRepository.save(arcitecture);
            categoryRepository.save(furniture);
            categoryRepository.save(garden);
            categoryRepository.save(lightning);
            categoryRepository.save(kitchenUtilities);
            categoryRepository.save(utilities);
            categoryRepository.save(home);
        }
    }

    @PostConstruct
    private void createTrendsCategories() {
        Category trendsFromDB = categoryRepository.findByName("Moda");

        if(trendsFromDB == null) {
            Category trends = new Category();
            trends.setName("Moda");
            trends.setIsLeaf(false);

            Category underwear = new Category();
            underwear.setName("Bielizna");
            underwear.setIsLeaf(true);
            trends.getChildren().add(underwear);

            Category cloths = new Category();
            cloths.setName("Odzież");
            cloths.setIsLeaf(true);
            trends.getChildren().add(cloths);

            Category boots = new Category();
            boots.setName("Obuwie");
            boots.setIsLeaf(true);
            trends.getChildren().add(boots);

            Category jewelery = new Category();
            jewelery.setName("Biżuteria");
            jewelery.setIsLeaf(true);
            trends.getChildren().add(jewelery);

            Category watches = new Category();
            watches.setName("Zegarki");
            watches.setIsLeaf(true);
            trends.getChildren().add(watches);

            Category backpacks = new Category();
            backpacks.setName("Plecaki");
            backpacks.setIsLeaf(true);
            trends.getChildren().add(backpacks);

            Category wallets = new Category();
            wallets.setName("Portfele");
            wallets.setIsLeaf(true);
            trends.getChildren().add(wallets);

            Category addons = new Category();
            addons.setName("Dodatki");
            addons.setIsLeaf(true);
            trends.getChildren().add(addons);

            categoryRepository.save(underwear);
            categoryRepository.save(jewelery);
            categoryRepository.save(addons);
            categoryRepository.save(boots);
            categoryRepository.save(cloths);
            categoryRepository.save(backpacks);
            categoryRepository.save(wallets);
            categoryRepository.save(watches);
            categoryRepository.save(trends);
        }
    }

    @PostConstruct
    private void createElectronicsCategories() {
        Category electronicsFromDb = categoryRepository.findByName("Elektronika");

        if(electronicsFromDb == null) {
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
            etuis.setIsLeaf(true);
            phonesGeneral.getChildren().add(etuis);

            Category powerbanks = new Category();
            powerbanks.setName("Powerbanki");
            powerbanks.setIsLeaf(true);
            phonesGeneral.getChildren().add(powerbanks);

            Category smartwatches = new Category();
            smartwatches.setName("Smartwatche");
            smartwatches.setIsLeaf(false);
            phonesGeneral.getChildren().add(smartwatches);

            Category applePhones = new Category();
            applePhones.setName("Apple");
            applePhones.setIsLeaf(true);
            smartphones.getChildren().add(applePhones);

            Category huaweiPhones = new Category();
            huaweiPhones.setName("Huawei");
            huaweiPhones.setIsLeaf(true);
            smartphones.getChildren().add(huaweiPhones);

            Category htcPhones = new Category();
            htcPhones.setName("HTC");
            htcPhones.setIsLeaf(true);
            smartphones.getChildren().add(htcPhones);

            Category lgPhones = new Category();
            lgPhones.setName("LG");
            lgPhones.setIsLeaf(true);
            smartphones.getChildren().add(lgPhones);

            Category xiaomiPhones = new Category();
            xiaomiPhones.setName("Xiaomi");
            xiaomiPhones.setIsLeaf(true);
            smartphones.getChildren().add(xiaomiPhones);

            Category otherPhones = new Category();
            otherPhones.setName("Inne");
            otherPhones.setIsLeaf(true);
            smartphones.getChildren().add(otherPhones);

            Category appleSmartwatch = new Category();
            appleSmartwatch.setName("Apple");
            appleSmartwatch.setIsLeaf(true);
            smartwatches.getChildren().add(appleSmartwatch);

            Category asusSmartwatch = new Category();
            asusSmartwatch.setName("Asus");
            asusSmartwatch.setIsLeaf(true);
            smartwatches.getChildren().add(asusSmartwatch);

            Category lgSmartwatch = new Category();
            lgSmartwatch.setName("LG");
            lgSmartwatch.setIsLeaf(true);
            smartwatches.getChildren().add(lgSmartwatch);

            Category samsungSmartwatch = new Category();
            samsungSmartwatch.setName("Samsung");
            samsungSmartwatch.setIsLeaf(true);
            smartwatches.getChildren().add(samsungSmartwatch);

            Category xiaomiSmartwatch = new Category();
            xiaomiSmartwatch.setName("Xiaomi");
            xiaomiSmartwatch.setIsLeaf(true);
            smartwatches.getChildren().add(xiaomiSmartwatch);

            Category otherSmartwatch = new Category();
            otherSmartwatch.setName("Inne");
            otherSmartwatch.setIsLeaf(true);
            smartwatches.getChildren().add(otherSmartwatch);

            Category laptops = new Category();
            laptops.setName("Laptopy");
            laptops.setIsLeaf(false);
            electronics.getChildren().add(laptops);

            Category asusLaptops = new Category();
            asusLaptops.setName("Asus");
            asusLaptops.setIsLeaf(true);
            laptops.getChildren().add(asusLaptops);

            Category dellLaptops = new Category();
            dellLaptops.setName("Dell");
            dellLaptops.setIsLeaf(true);
            laptops.getChildren().add(dellLaptops);

            Category hpLaptops = new Category();
            hpLaptops.setName("HP");
            hpLaptops.setIsLeaf(true);
            laptops.getChildren().add(hpLaptops);

            Category lenovoLaptops = new Category();
            lenovoLaptops.setName("Lenovo");
            lenovoLaptops.setIsLeaf(true);
            laptops.getChildren().add(lenovoLaptops);

            Category msiLaptops = new Category();
            msiLaptops.setName("MSI");
            msiLaptops.setIsLeaf(true);
            laptops.getChildren().add(msiLaptops);

            Category otherLaptops = new Category();
            otherLaptops.setName("Inne");
            otherLaptops.setIsLeaf(true);
            laptops.getChildren().add(otherLaptops);

            Category rtvagd = new Category();
            rtvagd.setName("RTV i AGD");
            rtvagd.setIsLeaf(false);
            electronics.getChildren().add(rtvagd);

            Category refrigerators = new Category();
            refrigerators.setName("Lodówki");
            refrigerators.setIsLeaf(true);
            rtvagd.getChildren().add(refrigerators);

            Category dischwashers = new Category();
            dischwashers.setName("Zmywarki");
            dischwashers.setIsLeaf(true);
            rtvagd.getChildren().add(dischwashers);

            Category cameras = new Category();
            cameras.setName("Aparaty");
            cameras.setIsLeaf(true);
            rtvagd.getChildren().add(cameras);

            Category videoCameras = new Category();
            videoCameras.setName("Kamery");
            videoCameras.setIsLeaf(true);
            rtvagd.getChildren().add(videoCameras);

            Category TV = new Category();
            TV.setName("Telewizory");
            TV.setIsLeaf(true);
            rtvagd.getChildren().add(TV);

            Category radio = new Category();
            radio.setName("Radia");
            radio.setIsLeaf(true);
            rtvagd.getChildren().add(radio);

            Category microwaves = new Category();
            microwaves.setName("Mikrofalówki");
            microwaves.setIsLeaf(true);
            rtvagd.getChildren().add(microwaves);

            Category DVD = new Category();
            DVD.setName("Kina domowe");
            DVD.setIsLeaf(true);
            rtvagd.getChildren().add(DVD);

            Category computers = new Category();
            computers.setName("Komputery i podzespoły");
            computers.setIsLeaf(false);
            electronics.getChildren().add(computers);

            Category rams = new Category();
            rams.setName("Pamięci RAM");
            rams.setIsLeaf(true);
            computers.getChildren().add(rams);

            Category gpu = new Category();
            gpu.setName("Karty graficzne");
            gpu.setIsLeaf(true);
            computers.getChildren().add(gpu);

            Category cpu = new Category();
            cpu.setName("Procesory");
            cpu.setIsLeaf(true);
            computers.getChildren().add(cpu);

            Category monitors = new Category();
            monitors.setName("Monitory");
            monitors.setIsLeaf(true);
            computers.getChildren().add(monitors);

            Category power = new Category();
            power.setName("Zasilacze");
            power.setIsLeaf(true);
            computers.getChildren().add(power);

            Category motherboards = new Category();
            motherboards.setName("Płyty główne");
            motherboards.setIsLeaf(true);
            computers.getChildren().add(motherboards);

            Category keyboards = new Category();
            keyboards.setName("Klawiatury");
            keyboards.setIsLeaf(true);
            computers.getChildren().add(keyboards);

            Category mouse = new Category();
            mouse.setName("Myszki");
            mouse.setIsLeaf(true);
            computers.getChildren().add(mouse);

            Category obudowas = new Category();
            obudowas.setName("Obudowy");
            obudowas.setIsLeaf(true);
            computers.getChildren().add(obudowas);

            Category consoles = new Category();
            consoles.setName("Konsole");
            consoles.setIsLeaf(true);
            electronics.getChildren().add(consoles);

            categoryRepository.save(consoles);
            categoryRepository.save(gpu);
            categoryRepository.save(keyboards);
            categoryRepository.save(monitors);
            categoryRepository.save(mouse);
            categoryRepository.save(obudowas);
            categoryRepository.save(rams);
            categoryRepository.save(motherboards);
            categoryRepository.save(cpu);
            categoryRepository.save(power);
            categoryRepository.save(computers);
            categoryRepository.save(asusLaptops);
            categoryRepository.save(dellLaptops);
            categoryRepository.save(hpLaptops);
            categoryRepository.save(lenovoLaptops);
            categoryRepository.save(msiLaptops);
            categoryRepository.save(otherLaptops);
            categoryRepository.save(laptops);
            categoryRepository.save(cameras);
            categoryRepository.save(videoCameras);
            categoryRepository.save(DVD);
            categoryRepository.save(refrigerators);
            categoryRepository.save(microwaves);
            categoryRepository.save(radio);
            categoryRepository.save(TV);
            categoryRepository.save(dischwashers);
            categoryRepository.save(rtvagd);
            categoryRepository.save(appleSmartwatch);
            categoryRepository.save(asusSmartwatch);
            categoryRepository.save(lgSmartwatch);
            categoryRepository.save(samsungSmartwatch);
            categoryRepository.save(xiaomiSmartwatch);
            categoryRepository.save(otherSmartwatch);
            categoryRepository.save(applePhones);
            categoryRepository.save(huaweiPhones);
            categoryRepository.save(htcPhones);
            categoryRepository.save(lgPhones);
            categoryRepository.save(xiaomiPhones);
            categoryRepository.save(otherPhones);
            categoryRepository.save(etuis);
            categoryRepository.save(smartwatches);
            categoryRepository.save(smartphones);
            categoryRepository.save(powerbanks);
            categoryRepository.save(phonesGeneral);
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
            registrationService.register(userSaveDto);

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
            registrationService.register(userSaveDto);

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
