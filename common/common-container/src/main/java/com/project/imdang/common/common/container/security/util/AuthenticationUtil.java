package com.project.imdang.common.common.container.security.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationUtil {

    public static String getUserSocialId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getMemberId();
    }

    public static Authentication getAuthentication(User user) {

        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(user, "",
                grantedAuthorities);
    }

//    public static void makeAuthentication(Member member) {
//        // Authentication 정보 만들기
//        User user = User.builder()
//                .socialId(member.getSocialId())
//                .roles(Arrays.asList(member.getRole().getKey()))
//                .build();
//
//        // ContextHolder 에 Authentication 정보 저장
//        Authentication auth = AuthenticationUtil.getAuthentication(user);
//        SecurityContextHolder.getContext().setAuthentication(auth);
//    }

}
