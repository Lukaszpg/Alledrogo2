package pro.lukasgorny.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pro.lukasgorny.enums.RoleEnum;
import pro.lukasgorny.model.Role;
import pro.lukasgorny.model.User;
import pro.lukasgorny.model.VerificationToken;
import pro.lukasgorny.repository.RoleRepository;
import pro.lukasgorny.repository.VerificationTokenRepository;
import pro.lukasgorny.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukaszgo on 2017-05-25.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, VerificationTokenRepository verificationTokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(prepareRoleCollection());
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private List<Role> prepareRoleCollection() {
        List<Role> results = new ArrayList<>();
        results.add(roleRepository.findByName(RoleEnum.ROLE_USER.name()));
        return results;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }
}
