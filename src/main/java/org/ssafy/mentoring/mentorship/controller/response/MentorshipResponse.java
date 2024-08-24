package org.ssafy.mentoring.mentorship.controller.response;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipStatus;
import org.ssafy.mentoring.user.controller.response.UserResponse;

import java.time.LocalDateTime;

@Getter
@Builder
public class MentorshipResponse {

    private Long id;
    private String title;
    private String content;
    private Integer fee;
    private MentorshipStatus mentorshipStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserResponse mentor;

    public static MentorshipResponse from(Mentorship mentorship) {
        return MentorshipResponse.builder()
                .id(mentorship.getId())
                .title(mentorship.getTitle())
                .content(mentorship.getContent())
                .fee(mentorship.getFee())
                .mentorshipStatus(mentorship.getStatus())
                .createdAt(mentorship.getCreatedAt())
                .updatedAt(mentorship.getUpdatedAt())
                .mentor(UserResponse.from(mentorship.getMentor()))
                .build();
    }
}
