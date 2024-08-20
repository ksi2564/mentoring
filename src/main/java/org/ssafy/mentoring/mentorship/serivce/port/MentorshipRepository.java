package org.ssafy.mentoring.mentorship.serivce.port;

import org.ssafy.mentoring.mentorship.domain.Mentorship;

public interface MentorshipRepository {
    Mentorship save(Mentorship mentorship);
}
