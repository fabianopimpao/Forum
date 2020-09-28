package me.pimpao.forum.controller;

import me.pimpao.forum.config.security.TokenService;
import me.pimpao.forum.controller.dto.TokenDto;
import me.pimpao.forum.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm loginForm) {
        UsernamePasswordAuthenticationToken loginData = loginForm.converter();
        String token;
        try {
            Authentication authenticate = authenticationManager.authenticate(loginData);
            token = tokenService.gerarToken(authenticate);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new TokenDto(token, "Bearer"));
    }
}
