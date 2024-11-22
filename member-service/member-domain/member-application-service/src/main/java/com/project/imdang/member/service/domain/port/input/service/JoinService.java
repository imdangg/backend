package com.project.imdang.member.service.domain.port.input.service;

import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;


public interface JoinService {
    LoginResponse join(String accessToken, JoinCommand joinCommand);
}