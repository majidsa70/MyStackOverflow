package ir.majidsadeghi.stackoverflowlike.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import ir.majidsadeghi.stackoverflowlike.dto.SignUpDto;
import ir.majidsadeghi.stackoverflowlike.entity.User;
import ir.majidsadeghi.stackoverflowlike.entity.UserRole;
import ir.majidsadeghi.stackoverflowlike.exceptions.DuplicateException;
import ir.majidsadeghi.stackoverflowlike.repository.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {


    private final UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    private User findUser(String username){
        return repository.findByMobile(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username :" + username));

    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        val user = findUser(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user.getRole()));
    }

    public void signUp(SignUpDto data) throws JWTVerificationException {
        if (repository.findByMobile(data.mobile()).isPresent()) {
            throw new DuplicateException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.mobile(), encryptedPassword, UserRole.valueOf(data.role())); //TODO check role validation in dto
        repository.save(newUser);
    }


    private Collection<? extends GrantedAuthority> getAuthorities(UserRole role) {
        if (role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}