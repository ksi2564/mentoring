package org.ssafy.mentoring.user.domain;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.mentoring.config.security.userinfo.KakaoOAuth2UserInfo;
import org.ssafy.mentoring.config.security.userinfo.OAuth2UserInfo;

import java.util.Map;

/**
 * OAuth2 provider 마다 받을 수 있는 데이터가 다르므로
 * 받는 데이터에 맞춰 분기 처리 가능하도록 확장을 염두한 DTO 클래스
 */
@Getter
public class OAuth2Attributes {

    private final String nameAttributeKey;
    private final OAuth2UserInfo oAuth2UserInfo;

    @Builder
    public OAuth2Attributes(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    public static OAuth2Attributes of(String nameAttributeKey, Map<String, Object> attributes) {
        return OAuth2Attributes.builder()
                .nameAttributeKey(nameAttributeKey)
                .oAuth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }
}
