package org.ssafy.mentoring.mentorship.serivce;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.mentoring.common.service.port.DateTimeHolder;
import org.ssafy.mentoring.mentorship.controller.port.MentorshipService;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipAvailability;
import org.ssafy.mentoring.mentorship.domain.MentorshipCreate;
import org.ssafy.mentoring.mentorship.serivce.port.MentorshipAvailabilityRepository;
import org.ssafy.mentoring.mentorship.serivce.port.MentorshipRepository;
import org.ssafy.mentoring.user.domain.User;
import org.ssafy.mentoring.user.service.port.UserRepository;

import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
public class MentorshipServiceImpl implements MentorshipService {

    private final MentorshipAvailabilityRepository mentorshipAvailabilityRepository;
    private final MentorshipRepository mentorshipRepository;
    private final UserRepository userRepository;
    private final DateTimeHolder dateTimeHolder;

    @Override
    @Transactional
    public Mentorship create(MentorshipCreate mentorshipCreate) {
        User user = userRepository.getById(mentorshipCreate.getUserId());
        User mentor = user.upgradeToMentor(mentorshipCreate.getEmail(), dateTimeHolder);
        userRepository.save(mentor);

        Mentorship mentorship = Mentorship.from(mentor, mentorshipCreate, dateTimeHolder);
        mentorship = mentorshipRepository.save(mentorship);
        List<MentorshipAvailability> availabilities = MentorshipAvailability.from(mentorship, mentorshipCreate, dateTimeHolder);
        mentorshipAvailabilityRepository.saveAll(availabilities);

        return mentorship;
    }
}
