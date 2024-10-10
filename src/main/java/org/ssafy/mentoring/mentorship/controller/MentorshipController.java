package org.ssafy.mentoring.mentorship.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.mentoring.mentorship.controller.port.MentorshipService;
import org.ssafy.mentoring.mentorship.controller.response.MentorshipListResponse;

@RestController
@RequestMapping("/api/mentors")
@RequiredArgsConstructor
public class MentorshipController {

    private final MentorshipService mentorshipService;

    @GetMapping
    public ResponseEntity<Page<MentorshipListResponse>> getMentors(@RequestParam(defaultValue = "1") int page) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mentorshipService.getMentorshipList(
                        PageRequest.of(page - 1, 24, Sort.by(Sort.Direction.ASC, "createdAt"))
                ));
    }
}
