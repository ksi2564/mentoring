package org.ssafy.mentoring.mentorship.controller.port;

import org.ssafy.mentoring.mentorship.domain.MentorshipAvailability;

import java.time.LocalDateTime;
import java.util.List;

public interface MentorshipAvailabilityService {
    List<MentorshipAvailability> createAvailabilities(Long mentorshipId, List<LocalDateTime> availableSlots);
}
