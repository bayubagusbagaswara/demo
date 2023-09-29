package com.bayu.demo.service;

import com.bayu.demo.dto.AuthenticationRequest;
import com.bayu.demo.dto.AuthenticationResponse;
import com.bayu.demo.dto.RegisterRequest;

public interface UserService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(AuthenticationRequest request);
}
