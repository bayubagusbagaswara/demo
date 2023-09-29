package com.bayu.demo.controller;

import com.bayu.demo.dto.AuthenticationRequest;
import com.bayu.demo.dto.AuthenticationResponse;
import com.bayu.demo.dto.RegisterRequest;
import com.bayu.demo.dto.WebResponse;
import com.bayu.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(path = "/registration")
    public ResponseEntity<WebResponse<AuthenticationResponse>> registration(@RequestBody RegisterRequest request) {
        AuthenticationResponse register = userService.register(request);
        return new ResponseEntity<>(new WebResponse<>(Boolean.TRUE, "Success registration", register), HttpStatus.CREATED);
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<WebResponse<AuthenticationResponse>> signIn(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse login = userService.login(request);
        return new ResponseEntity<>(new WebResponse<>(Boolean.TRUE, "Success login", login), HttpStatus.OK);
    }

}
