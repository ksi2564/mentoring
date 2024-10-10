package org.ssafy.mentoring.mentorship.controller.response;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.user.controller.response.UserResponse;

import java.time.LocalDateTime;

@Getter
@Builder
public class MentorshipListResponse {
    private Long id;
    private String title;
    private Integer fee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserResponse mentor;

    public static MentorshipListResponse from(Mentorship mentorship) {
        return MentorshipListResponse.builder()
                .id(mentorship.getId())
                .title(mentorship.getTitle())
                .fee(mentorship.getFee())
                .createdAt(mentorship.getCreatedAt())
                .updatedAt(mentorship.getUpdatedAt())
                .mentor(UserResponse.from(mentorship.getMentor()))
                .build();
    }
}
