package org.ssafy.mentoring.mentorship.serivce.port;

import org.ssafy.mentoring.mentorship.domain.MentorshipAvailability;

import java.util.List;

public interface MentorshipAvailabilityRepository {
    MentorshipAvailability save(MentorshipAvailability mentorshipAvailability);

    List<MentorshipAvailability> saveAll(List<MentorshipAvailability> availabilities);
}
