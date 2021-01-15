package com.study.juhee.stringboot.config.auth;

import com.study.juhee.stringboot.config.auth.dto.OAuthAttributes;
import com.study.juhee.stringboot.config.auth.dto.SessionUser;
import com.study.juhee.stringboot.domain.user.User;
import com.study.juhee.stringboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

/**
 * 소셜 로그인 후 실행.
 * 가져온 사용자의 정보들을 기반으로 가입/정보수정/세션저장 등 기능의 클래스
 */
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행중인 서비스를 구분하는 코드. (구글, 네이버, 카카오 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // OAuty2 로그인 진행시 키가되는 필드값.(Primary Key와 같은 의미) // 구글=기본제공(=sub), 네이버카카오=기본지원X
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuthAttributes : OAuth2UserService를 통해서 가져온 OAuth2User의 attribute를 담을 클래스.
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));    // SessionUser : 세션 사용자 정보를 저장하기위한 Dto클래스.

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributesKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());     // 조회결과가 null이면 새로 등록함.

        return userRepository.save(user);
    }
}
