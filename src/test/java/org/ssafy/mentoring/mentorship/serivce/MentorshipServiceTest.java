package org.ssafy.mentoring.mentorship.serivce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.ssafy.mentoring.mentorship.controller.port.MentorshipService;
import org.ssafy.mentoring.mentorship.controller.response.MentorshipListResponse;
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

        Mentorship approvedMentorship = Mentorship.builder()
                .title("허용된 멘토십 제목")
                .content("허용된 멘토십 내용")
                .fee(10)
                .status(MentorshipStatus.APPROVED)
                .createdAt(LocalDateTime.of(2024, 8, 24, 10, 30))
                .updatedAt(LocalDateTime.of(2024, 8, 24, 10, 30))
                .mentor(User.builder()
                        .socialId("12345")
                        .nickname("tester1")
                        .status(UserStatus.MENTOR)
                        .build())
                .build();
        Mentorship deactivatedMentorship = Mentorship.builder()
                .title("비활성화된 멘토십 제목")
                .content("비활성화된 멘토십 내용")
                .fee(11)
                .status(MentorshipStatus.DEACTIVATED)
                .createdAt(LocalDateTime.of(2024, 8, 24, 10, 30))
                .updatedAt(LocalDateTime.of(2024, 8, 24, 10, 30))
                .mentor(User.builder()
                        .socialId("12346")
                        .nickname("tester2")
                        .status(UserStatus.MENTOR)
                        .build())
                .build();
        Mentorship registeredMentorship = Mentorship.builder()
                .title("등록 상태의 멘토십 제목")
                .content("등록 상태의 멘토십 내용")
                .fee(12)
                .status(MentorshipStatus.REGISTERED)
                .createdAt(LocalDateTime.of(2024, 8, 24, 10, 30))
                .updatedAt(LocalDateTime.of(2024, 8, 24, 10, 30))
                .mentor(User.builder()
                        .socialId("12347")
                        .nickname("tester3")
                        .status(UserStatus.MENTOR)
                        .build())
                .build();
        fakeMentorshipRepository.save(approvedMentorship);
        fakeMentorshipRepository.save(deactivatedMentorship);
        fakeMentorshipRepository.save(registeredMentorship);
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

    @Test
    void pageable_를_이용하여_허용된_멘토십_목록을_페이지네이션_할_수_있다() {
        // given
        Pageable pageable = PageRequest.of(0, 24, Sort.by(Sort.Direction.ASC, "createdAt"));

        // when
        Page<MentorshipListResponse> result = mentorshipService.getMentorshipList(pageable);

        // then
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getTotalPages()).isEqualTo(1);
        assertThat(result.getContent().getFirst().getTitle()).isEqualTo("허용된 멘토십 제목");
        assertThat(result.getContent().getFirst().getFee()).isEqualTo(10);
        assertThat(result.getSort().getOrderFor("createdAt")).isNotNull();
        assertThat(result.getSort().getOrderFor("createdAt").getDirection()).isEqualTo(Sort.Direction.ASC);
    }
}