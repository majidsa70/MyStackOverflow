package ir.majidsadeghi.stackoverflowlike.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import ir.majidsadeghi.stackoverflowlike.dto.SignUpDto;
import ir.majidsadeghi.stackoverflowlike.entity.User;
import ir.majidsadeghi.stackoverflowlike.entity.UserRole;
import ir.majidsadeghi.stackoverflowlike.exceptions.DuplicateException;
import ir.majidsadeghi.stackoverflowlike.repository.UserRepository;
import ir.majidsadeghi.stackoverflowlike.security.TokenProvider;
import lombok.val;
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
public class UserService {


    private final UserRepository repository;
    private final TokenProvider tokenProvider;

    public UserService(UserRepository repository, TokenProvider tokenProvider) {
        this.repository = repository;
        this.tokenProvider = tokenProvider;
    }

    public User findUser(){
        return findUser(tokenProvider.getUserName());
    }

    private User findUser(String username){

        return repository.findByMobile(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username :" + username));

    }
}