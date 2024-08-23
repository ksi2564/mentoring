package org.ssafy.mentoring.mentorship.mock;

import org.ssafy.mentoring.config.security.userinfo.OAuth2UserInfo;

import java.util.Map;

public class FakeOAuth2UserInfo extends OAuth2UserInfo {
    private final String id;
    private final String nickname;

    public FakeOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
        this.id = (String) attributes.get("id");
        this.nickname = (String) attributes.get("nickname");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
