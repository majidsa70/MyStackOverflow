package ir.majidsadeghi.stackoverflowlike.controller;

import ir.majidsadeghi.stackoverflowlike.auth.TokenProvider;
import ir.majidsadeghi.stackoverflowlike.dto.LoginResponseDto;
import ir.majidsadeghi.stackoverflowlike.dto.SignInDto;
import ir.majidsadeghi.stackoverflowlike.dto.SignUpDto;
import ir.majidsadeghi.stackoverflowlike.entity.User;
import ir.majidsadeghi.stackoverflowlike.response.BaseResponse;
import ir.majidsadeghi.stackoverflowlike.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ir.majidsadeghi.stackoverflowlike.util.AppConstants.API_PATH;


@RestController
@RequestMapping(API_PATH + "auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService service;
    @Autowired
    private TokenProvider tokenService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<?>> signUp(@RequestBody @Validated SignUpDto data) {
        service.signUp(data);
        return new ResponseEntity<>(new BaseResponse<>(true,null,null,"Register successfully"), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse<LoginResponseDto>> signIn(@RequestBody @Validated SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.mobile(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new BaseResponse<>(true, new LoginResponseDto(accessToken), null));
    }
}
