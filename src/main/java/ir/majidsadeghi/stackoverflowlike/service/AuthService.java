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


    private final UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByMobile(username);
    }

    public void signUp(SignUpDto data) throws JWTVerificationException {
        if (repository.findByMobile(data.mobile()) != null) {
            throw new DuplicateException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.mobile(), encryptedPassword, UserRole.valueOf(data.role())); //TODO check role validation in dto
        repository.save(newUser);
    }
}