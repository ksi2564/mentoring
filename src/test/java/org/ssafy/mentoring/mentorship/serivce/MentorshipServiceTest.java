package org.ssafy.mentoring.mentorship.serivce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ssafy.mentoring.mentorship.controller.port.MentorshipService;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipCreate;
import org.ssafy.mentoring.mentorship.domain.MentorshipStatus;
import org.ssafy.mentoring.mock.FakeMentorshipAvailabilityRepository;
import org.ssafy.mentoring.mock.FakeMentorshipRepository;
import org.ssafy.mentoring.mock.FakeUserRepository;
import org.ssafy.mentoring.mock.TestDateTimeHolder;
import org.ssafy.mentoring.user.domain.User;
import org.ssafy.mentoring.user.domain.UserStatus;
import org.ssafy.mentoring.user.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MentorshipServiceTest {

    private MentorshipService mentorshipService;

    @BeforeEach
    void setUp() {
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        FakeMentorshipRepository fakeMentorshipRepository = new FakeMentorshipRepository();
        TestDateTimeHolder testDateTimeHolder = new TestDateTimeHolder(LocalDateTime.of(2100, 1, 1, 0, 0, 0));

        mentorshipService = MentorshipServiceImpl.builder()
                .mentorshipAvailabilityRepository(new FakeMentorshipAvailabilityRepository())
                .mentorshipRepository(fakeMentorshipRepository)
                .mentorshipAvailabilityService(MentorshipAvailabilityServiceImpl.builder()
                        .mentorshipAvailabilityRepository(new FakeMentorshipAvailabilityRepository())
                        .mentorshipRepository(fakeMentorshipRepository)
                        .dateTimeHolder(testDateTimeHolder)
                        .build())
                .userService(UserServiceImpl.builder()
                        .userRepository(fakeUserRepository)
                        .dateTimeHolder(testDateTimeHolder)
                        .build())
                .dateTimeHolder(testDateTimeHolder)
                .build();

        User user = User.builder()
                .socialId("1234")
                .nickname("tester")
                .status(UserStatus.MENTEE)
                .build();
        fakeUserRepository.save(user);
    }

    @Test
    void mentorshipCreate_를_이용하여_멘토십을_생성할_수_있다() {
        // given
        MentorshipCreate mentorshipCreate = MentorshipCreate.builder()
                .userId(1)
                .email("email@email.com")
                .title("멘토십 제목")
                .content("멘토십 내용")
                .availableSlots(List.of(
                        LocalDateTime.of(2024, 8, 24, 10, 30),
                        LocalDateTime.of(2024, 8, 24, 14, 0),
                        LocalDateTime.of(2024, 8, 25, 9, 30)
                ))
                .fee(10)
                .build();

        // when
        Mentorship result = mentorshipService.create(mentorshipCreate);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getTitle()).isEqualTo("멘토십 제목");
        assertThat(result.getContent()).isEqualTo("멘토십 내용");
        assertThat(result.getStatus()).isEqualTo(MentorshipStatus.APPROVED);
        assertThat(result.getMentor().getSocialId()).isEqualTo("1234");
    }
}