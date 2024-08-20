package org.ssafy.mentoring.mentorship.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.ssafy.mentoring.mentorship.controller.port.MentorshipService;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipCreate;

@Controller
@RequestMapping("/mentors")
@Builder
@RequiredArgsConstructor
public class MentorshipCreateController {

    private final MentorshipService mentorshipService;

    @GetMapping("/create-form")
    public String createForm() {
        return "mentorshipCreateForm";
    }

    @PostMapping("")
    public String create(@ModelAttribute MentorshipCreate mentorshipCreate, RedirectAttributes redirectAttributes) {
        Mentorship mentorship = mentorshipService.create(mentorshipCreate);
        redirectAttributes.addFlashAttribute("id", mentorship.getId());
        redirectAttributes.addFlashAttribute("message", "멘토 등록이 성공적으로 요청되었습니다.");
        return "redirect:/mentors";
    }

}
