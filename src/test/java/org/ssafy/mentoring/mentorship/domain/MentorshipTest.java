package org.ssafy.mentoring.mentorship.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ssafy.mentoring.mentorship.mock.TestDateTimeHolder;
import org.ssafy.mentoring.user.domain.User;
import org.ssafy.mentoring.user.domain.UserStatus;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class MentorshipTest {

    @Test
    void MentorshipCreate_로_멘토_등록을_할_수_있다() {
        // given
        MentorshipCreate mentorshipCreate = MentorshipCreate.builder()
                .userId(1)
                .email("test1@example.com")
                .title("test title")
                .content("test content")
                .fee(10)
                .build();
        User mentor = User.builder()
                .socialId("1234")
                .nickname("tester")
                .status(UserStatus.MENTEE)
                .build();

        // when
        Mentorship mentorship = Mentorship.from(mentor, mentorshipCreate,
                new TestDateTimeHolder(LocalDateTime.of(2099, 12, 25, 0, 0, 0)));

        // then
        assertThat(mentorship.getTitle()).isEqualTo("test title");
        assertThat(mentorship.getContent()).isEqualTo("test content");
        assertThat(mentorship.getFee()).isEqualTo(10);
        assertThat(mentorship.getCreatedAt()).isEqualTo(LocalDateTime.of(2099, 12, 25, 0, 0, 0));
    }

}