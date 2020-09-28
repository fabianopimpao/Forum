package me.pimpao.forum.controller;

import me.pimpao.forum.controller.form.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @PostMapping
    public ResponseEntity<Void> authenticate(@RequestBody @Valid LoginForm loginForm) {
        System.out.println(loginForm.getEmail());
        System.out.println(loginForm.getPassword());

        return ResponseEntity.ok().build();
    }
}
