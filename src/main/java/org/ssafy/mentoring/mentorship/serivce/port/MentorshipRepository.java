package org.ssafy.mentoring.mentorship.serivce.port;

import org.ssafy.mentoring.mentorship.domain.Mentorship;

import java.util.Optional;

public interface MentorshipRepository {
    Mentorship getById(Long mentorshipId);

    Optional<Mentorship> findById(Long mentorshipId);

    Mentorship save(Mentorship mentorship);
}
