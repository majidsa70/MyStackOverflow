package ir.majidsadeghi.stackoverflowlike.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import ir.majidsadeghi.stackoverflowlike.dto.SignUpDto;
import ir.majidsadeghi.stackoverflowlike.entity.User;
import ir.majidsadeghi.stackoverflowlike.entity.UserRole;
import ir.majidsadeghi.stackoverflowlike.exceptions.DuplicateException;
import ir.majidsadeghi.stackoverflowlike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = repository.findByMobile(username);
        return user;
    }

    public void signUp(SignUpDto data) throws JWTVerificationException {
        if (repository.findByMobile(data.mobile()) != null) {
            throw new DuplicateException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.mobile(), encryptedPassword, UserRole.USER);
        User savedUser = repository.save(newUser);
    }
}