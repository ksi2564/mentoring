package org.ssafy.mentoring.mentorship.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ssafy.mentoring.mentorship.controller.port.MentorshipService;

@Controller
@RequestMapping("/mentors")
@Builder
@RequiredArgsConstructor
public class MentorshipController {

    private final MentorshipService mentorshipService;

    @GetMapping
    public String mentors() {
        return "mentors";
    }
}
