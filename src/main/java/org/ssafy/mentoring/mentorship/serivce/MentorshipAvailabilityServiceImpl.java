package org.ssafy.mentoring.mentorship.serivce;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.mentoring.common.service.port.DateTimeHolder;
import org.ssafy.mentoring.mentorship.controller.port.MentorshipAvailabilityService;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipAvailability;
import org.ssafy.mentoring.mentorship.serivce.port.MentorshipAvailabilityRepository;
import org.ssafy.mentoring.mentorship.serivce.port.MentorshipRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
public class MentorshipAvailabilityServiceImpl implements MentorshipAvailabilityService {

    private final MentorshipAvailabilityRepository mentorshipAvailabilityRepository;
    private final MentorshipRepository mentorshipRepository;
    private final DateTimeHolder dateTimeHolder;

    @Override
    @Transactional
    public List<MentorshipAvailability> createAvailabilities(Long mentorshipId, List<LocalDateTime> availableSlots) {
        Mentorship mentorship = mentorshipRepository.getById(mentorshipId);
        List<MentorshipAvailability> availabilities = MentorshipAvailability.from(mentorship, availableSlots, dateTimeHolder);
        return mentorshipAvailabilityRepository.saveAll(availabilities);
    }
}
