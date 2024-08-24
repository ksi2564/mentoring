package org.ssafy.mentoring.mentorship.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.mentoring.mentorship.controller.port.MentorshipService;
import org.ssafy.mentoring.mentorship.controller.response.MentorshipResponse;
import org.ssafy.mentoring.mentorship.domain.MentorshipCreate;

@RestController
@RequestMapping("/api/mentors")
@RequiredArgsConstructor
public class MentorshipCreateController {

    private final MentorshipService mentorshipService;

    @PostMapping
    public ResponseEntity<MentorshipResponse> create(@RequestBody MentorshipCreate mentorshipCreate) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MentorshipResponse.from(mentorshipService.create(mentorshipCreate)));
    }

}
