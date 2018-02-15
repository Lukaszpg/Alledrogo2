package pro.lukasgorny.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.user.ChangeEmailDto;
import pro.lukasgorny.dto.user.ChangePasswordDto;
import pro.lukasgorny.dto.user.UserExtendedDto;
import pro.lukasgorny.dto.user.UserResultDto;
import pro.lukasgorny.model.User;
import pro.lukasgorny.model.VerificationToken;
import pro.lukasgorny.repository.VerificationTokenRepository;
import pro.lukasgorny.repository.UserRepository;
import pro.lukasgorny.service.hash.HashService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.StringJoiner;

/**
 * Created by lukaszgo on 2017-05-25.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MessageSource messageSource;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final HashService hashService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, MessageSource messageSource,
            BCryptPasswordEncoder bCryptPasswordEncoder, HashService hashService) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.messageSource = messageSource;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.hashService = hashService;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return verificationTokenRepository.findByToken(VerificationToken);
    }

    @Override
    public UserResultDto createDtoFromEntity(User user) {
        UserResultDto userResultDto = new UserResultDto();
        userResultDto.setId(hashService.encode(user.getId()));
        userResultDto.setEmail(user.getEmail());
        userResultDto.setRegisteredSince(calculateAndFormatDateDifference(user));
        userResultDto.setUserExtendedDto(createExtendedDtoFromEntity(user));
        return userResultDto;
    }

    @Override
    public UserExtendedDto getUserData(String email) {
        User user = getByEmail(email);
        return createExtendedDtoFromEntity(user);
    }

    @Override
    public void setAndSaveUserData(UserExtendedDto userExtendedDto) {
        User user = getByEmail(userExtendedDto.getEmail());
        user.setName(userExtendedDto.getFirstName());
        user.setSurname(userExtendedDto.getSurname());
        user.setCompanyName(userExtendedDto.getCompanyName());
        user.setAddress(userExtendedDto.getAddress());
        user.setZipCode(userExtendedDto.getZipCode());
        user.setCity(userExtendedDto.getCity());
        user.setVoivodeship(userExtendedDto.getVoivodeship());
        user.setFirstPhoneNumber(userExtendedDto.getFirstPhoneNumber());
        user.setSecondPhoneNumber(userExtendedDto.getSecondPhoneNumber());
        save(user);
    }

    @Override
    public boolean isUserPasswordMatchWithInput(String inputPassword, String email) {
        User user = getByEmail(email);
        return bCryptPasswordEncoder.matches(inputPassword, user.getPassword());
    }

    @Override
    public boolean isNewPasswordSameAsOldPassword(ChangePasswordDto changePasswordDto) {
        User user = getByEmail(changePasswordDto.getEmail());
        return bCryptPasswordEncoder.matches(changePasswordDto.getNewPassword(), user.getPassword());
    }

    @Override
    public void changeUserEmail(ChangeEmailDto changeEmailDto) {
        User user = getByEmail(changeEmailDto.getEmail());
        user.setEmail(changeEmailDto.getNewEmail());
        save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getById(String id) {
        return userRepository.findOne(hashService.decode(id));
    }

    @Override
    public void changeUserPassword(ChangePasswordDto changePasswordDto) {
        User user = getByEmail(changePasswordDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword()));
        save(user);
    }

    private UserExtendedDto createExtendedDtoFromEntity(User user) {
        UserExtendedDto userExtendedDto = new UserExtendedDto();
        userExtendedDto.setFirstName(user.getName());
        userExtendedDto.setSurname(user.getSurname());
        userExtendedDto.setCompanyName(user.getCompanyName());
        userExtendedDto.setAddress(user.getAddress());
        userExtendedDto.setZipCode(user.getZipCode());
        userExtendedDto.setCity(user.getCity());
        userExtendedDto.setVoivodeship(user.getVoivodeship());
        userExtendedDto.setFirstPhoneNumber(user.getFirstPhoneNumber());
        userExtendedDto.setSecondPhoneNumber(user.getSecondPhoneNumber());
        return userExtendedDto;
    }

    private String calculateAndFormatDateDifference(User user) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tempDateTime = user.getCreateDate();

        Long years = tempDateTime.until(now, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);
        Long months = tempDateTime.until(now, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);
        Long days = tempDateTime.until(now, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);

        String yearsMessage = messageSource.getMessage("label.years", null, LocaleContextHolder.getLocale());
        String monthsMessage = messageSource.getMessage("label.months", null, LocaleContextHolder.getLocale());
        String daysMessage = messageSource.getMessage("label.days", null, LocaleContextHolder.getLocale());

        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner.add(years.toString()).add(yearsMessage).add(months.toString()).add(monthsMessage).add(days.toString()).add(daysMessage);

        return stringJoiner.toString();
    }
}
