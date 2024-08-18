package org.ssafy.mentoring.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.ssafy.mentoring.common.service.port.DateTimeHolder;
import org.ssafy.mentoring.user.domain.OAuth2Attributes;
import org.ssafy.mentoring.user.domain.User;
import org.ssafy.mentoring.user.service.port.UserRepository;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final DateTimeHolder dateTimeHolder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Role generate
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");

        // nameAttributeKey
        String userNameAttribute = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        // User Info
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 소셜 로그인을 여러개 해야할 시 socialType 컬럼을 추가하여 분기처리하면 됨
        OAuth2Attributes oAuth2Attributes = OAuth2Attributes.of(userNameAttribute, attributes);
        log.info("attributes {}", attributes);

        registerUserIfAbsent(oAuth2Attributes);

        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), userNameAttribute);
    }

    private void registerUserIfAbsent(OAuth2Attributes oAuth2Attributes) {
        String socialId = oAuth2Attributes.getOAuth2UserInfo().getId();
        User findUser = userRepository.findBySocialId(socialId).orElse(null);
        if (findUser == null) {
            userRepository.save(User.from(oAuth2Attributes.getOAuth2UserInfo(), dateTimeHolder));
        }
    }
}
