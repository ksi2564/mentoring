package org.ssafy.mentoring.mentorship.serivce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ssafy.mentoring.mentorship.controller.port.MentorshipAvailabilityService;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipAvailability;
import org.ssafy.mentoring.mentorship.domain.MentorshipStatus;
import org.ssafy.mentoring.mock.FakeMentorshipAvailabilityRepository;
import org.ssafy.mentoring.mock.FakeMentorshipRepository;
import org.ssafy.mentoring.mock.TestDateTimeHolder;
import org.ssafy.mentoring.user.domain.User;
import org.ssafy.mentoring.user.domain.UserStatus;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MentorshipAvailabilityServiceTest {

    private MentorshipAvailabilityService mentorshipAvailabilityService;

    @BeforeEach
    void setUp() {
        FakeMentorshipAvailabilityRepository fakeMentorshipAvailabilityRepository = new FakeMentorshipAvailabilityRepository();
        FakeMentorshipRepository fakeMentorshipRepository = new FakeMentorshipRepository();
        mentorshipAvailabilityService = MentorshipAvailabilityServiceImpl.builder()
                .mentorshipAvailabilityRepository(fakeMentorshipAvailabilityRepository)
                .mentorshipRepository(fakeMentorshipRepository)
                .dateTimeHolder(new TestDateTimeHolder(LocalDateTime.of(2100, 1, 1, 0, 0, 0)))
                .build();

        User user = User.builder()
                .socialId("1234")
                .email("email@email.com")
                .nickname("tester")
                .status(UserStatus.MENTOR)
                .build();
        Mentorship mentorship = Mentorship.builder()
                .title("test title")
                .content("test content")
                .fee(1)
                .status(MentorshipStatus.APPROVED)
                .mentor(user)
                .build();
        fakeMentorshipRepository.save(mentorship);
    }

    @Test
    void 멘토가_멘토십이_가능한_시간_다건을_등록할_수_있다() {
        // given
        List<LocalDateTime> availableSlots = List.of(
                LocalDateTime.of(2024, 8, 24, 10, 30),
                LocalDateTime.of(2024, 8, 24, 14, 0),
                LocalDateTime.of(2024, 8, 25, 9, 30)
        );

        // when
        List<MentorshipAvailability> result = mentorshipAvailabilityService.createAvailabilities(1L, availableSlots);

        // then
        assertThat(result.size()).isEqualTo(3);
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i).getSlot()).isEqualTo(availableSlots.get(i));
            assertThat(result.get(i).getCreatedAt()).isEqualTo(LocalDateTime.of(2100, 1, 1, 0, 0, 0));
            assertThat(result.get(i).getUpdatedAt()).isEqualTo(LocalDateTime.of(2100, 1, 1, 0, 0, 0));
            assertThat(result.get(i).getMentorship().getId()).isEqualTo(1);
        }
    }
}