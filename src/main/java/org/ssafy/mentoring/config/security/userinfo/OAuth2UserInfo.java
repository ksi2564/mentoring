package org.ssafy.mentoring.config.security.userinfo;

import java.util.Map;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getId(); // 카카오의 식별자 key 이름이 id

    public abstract String getNickname();
}
