package org.ssafy.mentoring.mentorship.controller.port;

import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipCreate;

public interface MentorshipService {
    Mentorship create(MentorshipCreate mentorshipCreate);
}
