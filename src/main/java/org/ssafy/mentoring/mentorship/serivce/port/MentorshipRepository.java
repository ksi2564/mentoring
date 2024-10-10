package org.ssafy.mentoring.mentorship.serivce.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipStatus;

import java.util.Optional;

public interface MentorshipRepository {
    Mentorship getById(Long mentorshipId);

    Optional<Mentorship> findById(Long mentorshipId);

    Mentorship save(Mentorship mentorship);

    Page<Mentorship> findAllByStatus(MentorshipStatus status, Pageable pageable);
}
