package org.ssafy.mentoring.mentorship.serivce;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ssafy.mentoring.common.service.port.DateTimeHolder;
import org.ssafy.mentoring.mentorship.controller.port.MentorshipAvailabilityService;
import org.ssafy.mentoring.mentorship.controller.port.MentorshipService;
import org.ssafy.mentoring.mentorship.controller.response.MentorshipListResponse;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipCreate;
import org.ssafy.mentoring.mentorship.domain.MentorshipStatus;
import org.ssafy.mentoring.mentorship.serivce.port.MentorshipAvailabilityRepository;
import org.ssafy.mentoring.mentorship.serivce.port.MentorshipRepository;
import org.ssafy.mentoring.user.controller.port.UserService;
import org.ssafy.mentoring.user.domain.User;

@Service
@Builder
@RequiredArgsConstructor
public class MentorshipServiceImpl implements MentorshipService {

    private final MentorshipAvailabilityRepository mentorshipAvailabilityRepository;
    private final MentorshipRepository mentorshipRepository;
    private final MentorshipAvailabilityService mentorshipAvailabilityService;
    private final UserService userService;
    private final DateTimeHolder dateTimeHolder;

    @Override
    @Transactional
    public Mentorship create(MentorshipCreate mentorshipCreate) {
        User user = userService.upgradeToMentor(mentorshipCreate.getUserId(), mentorshipCreate.getEmail());

        Mentorship mentorship = Mentorship.from(user, mentorshipCreate, dateTimeHolder);
        mentorship = mentorshipRepository.save(mentorship);

        mentorshipAvailabilityService.createAvailabilities(mentorship.getId(), mentorshipCreate.getAvailableSlots());

        return mentorship;
    }

    @Override
    public Page<MentorshipListResponse> getMentorshipList(Pageable pageable) {
        Page<Mentorship> mentorshipPage = mentorshipRepository.findAllByStatus(MentorshipStatus.APPROVED, pageable);

        return mentorshipPage.map(MentorshipListResponse::from);
    }
}
