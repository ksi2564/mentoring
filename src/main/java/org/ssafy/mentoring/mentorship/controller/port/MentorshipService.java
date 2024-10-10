package org.ssafy.mentoring.mentorship.controller.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ssafy.mentoring.mentorship.controller.response.MentorshipListResponse;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipCreate;

public interface MentorshipService {
    Mentorship create(MentorshipCreate mentorshipCreate);

    Page<MentorshipListResponse> getMentorshipList(Pageable pageable);
}
