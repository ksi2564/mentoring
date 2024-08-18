package org.ssafy.mentoring.user.domain;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.mentoring.common.service.port.DateTimeHolder;
import org.ssafy.mentoring.config.security.userinfo.OAuth2UserInfo;

import java.time.LocalDateTime;

@Getter
public class User {
    private final Long id;
    private final String socialId;
    private final String email;
    private final String nickname;
    private final UserStatus status;
    private final LocalDateTime lastLoginAt;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public User(Long id, String socialId, String email, String nickname, UserStatus status, LocalDateTime lastLoginAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.socialId = socialId;
        this.email = email;
        this.nickname = nickname;
        this.status = status;
        this.lastLoginAt = lastLoginAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User from(OAuth2UserInfo attributes, DateTimeHolder dateTimeHolder) {
        return User.builder()
                .socialId(attributes.getId())
                .nickname(attributes.getNickname())
                .status(UserStatus.MENTEE)
                .lastLoginAt(dateTimeHolder.getDateTime())
                .createdAt(dateTimeHolder.getDateTime())
                .updatedAt(dateTimeHolder.getDateTime())
                .build();
    }
}
