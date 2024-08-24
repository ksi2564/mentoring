package org.ssafy.mentoring.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ssafy.mentoring.mock.FakeUserRepository;
import org.ssafy.mentoring.mock.TestDateTimeHolder;
import org.ssafy.mentoring.user.controller.port.UserService;
import org.ssafy.mentoring.user.domain.User;
import org.ssafy.mentoring.user.domain.UserStatus;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        userService = UserServiceImpl.builder()
                .userRepository(fakeUserRepository)
                .dateTimeHolder(new TestDateTimeHolder(LocalDateTime.of(2100, 1, 1, 0, 0, 0)))
                .build();

        fakeUserRepository.save(User.builder()
                .id(1L)
                .socialId("123456789")
                .nickname("test nickname")
                .status(UserStatus.MENTEE)
                .lastLoginAt(LocalDateTime.of(2099, 12, 25, 0, 0, 0))
                .createdAt(LocalDateTime.of(2099, 12, 25, 0, 0, 0))
                .updatedAt(LocalDateTime.of(2099, 12, 25, 0, 0, 0))
                .build());
    }

    @Test
    void 멘토가_멘티로_업그레이드하기_위해서_이메일_정보가_있어야_한다() {
        // given
        String email = "test@test.com";

        // when
        User result = userService.upgradeToMentor(1, email);

        // then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getEmail()).isEqualTo("test@test.com");
        assertThat(result.getStatus()).isEqualTo(UserStatus.MENTOR);
        assertThat(result.getUpdatedAt()).isEqualTo(LocalDateTime.of(2100, 1, 1, 0, 0, 0));
    }
}