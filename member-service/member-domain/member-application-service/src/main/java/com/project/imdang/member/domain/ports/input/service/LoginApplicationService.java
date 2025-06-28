package com.project.imdang.member.domain.ports.input.service;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.dto.login.ReissueTokenCommand;
import com.project.imdang.member.domain.dto.login.TokenResult;

public interface LoginApplicationService {
    TokenResult reissue(ReissueTokenCommand reissueTokenCommand);
    Boolean logout(MemberId memberId);
}
