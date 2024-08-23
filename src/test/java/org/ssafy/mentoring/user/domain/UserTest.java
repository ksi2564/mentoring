package org.ssafy.mentoring.user.domain;

import org.junit.jupiter.api.Test;
import org.ssafy.mentoring.config.security.userinfo.OAuth2UserInfo;
import org.ssafy.mentoring.mentorship.domain.MentorshipCreate;
import org.ssafy.mentoring.mentorship.mock.FakeOAuth2UserInfo;
import org.ssafy.mentoring.mentorship.mock.TestDateTimeHolder;

import java.time.LocalDateTime;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void OAuth2UserInfo_로_회원_등록을_할_수_있다() {
        // given
        Map<String, Object> attributes = Map.of(
                "id", "123456789",
                "nickname", "TestUser"
        );
        OAuth2UserInfo oAuth2UserInfo = new FakeOAuth2UserInfo(attributes);
        LocalDateTime localDateTime = LocalDateTime.of(2099, 12, 25, 0, 0, 0);

        // when
        User user = User.from(oAuth2UserInfo, new TestDateTimeHolder(localDateTime));

        // then
        assertThat(user.getSocialId()).isEqualTo("123456789");
        assertThat(user.getNickname()).isEqualTo("TestUser");
        assertThat(user.getStatus()).isEqualTo(UserStatus.MENTEE);
        assertThat(user.getLastLoginAt()).isEqualTo(localDateTime);
        assertThat(user.getCreatedAt()).isEqualTo(localDateTime);
        assertThat(user.getUpdatedAt()).isEqualTo(localDateTime);
    }

    @Test
    void MentorshipCreate_로_이메일_등록과_멘토가_될_수_있다() {
        // given
        MentorshipCreate mentorshipCreate = MentorshipCreate.builder()
                .userId(1)
                .email("test1@example.com")
                .title("test title")
                .content("test content")
                .fee(10)
                .build();
        User user = User.builder()
                .socialId("1234")
                .nickname("tester")
                .status(UserStatus.MENTEE)
                .build();
        LocalDateTime localDateTime = LocalDateTime.of(2100, 1, 1, 0, 0, 0);

        // when
        user = user.registerMentor(mentorshipCreate, new TestDateTimeHolder(localDateTime));

        // then
        assertThat(user.getEmail()).isEqualTo("test1@example.com");
        assertThat(user.getStatus()).isEqualTo(UserStatus.MENTOR);
        assertThat(user.getUpdatedAt()).isEqualTo(localDateTime);
    }

}