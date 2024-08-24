package org.ssafy.mentoring.mentorship.domain;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.mentoring.common.service.port.DateTimeHolder;
import org.ssafy.mentoring.user.domain.User;

import java.time.LocalDateTime;

@Getter
public class Mentorship {
    private final Long id;
    private final String title;
    private final String content;
    private final Integer fee;
    private final MentorshipStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final User mentor;

    @Builder
    public Mentorship(Long id, String title, String content, Integer fee, MentorshipStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, User mentor) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.fee = fee;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.mentor = mentor;
    }

    public static Mentorship from(User mentor, MentorshipCreate mentorshipCreate, DateTimeHolder dateTimeHolder) {
        return Mentorship.builder()
                .title(mentorshipCreate.getTitle())
                .content(mentorshipCreate.getContent())
                .fee(mentorshipCreate.getFee())
                .status(MentorshipStatus.APPROVED)
                .createdAt(dateTimeHolder.getDateTime())
                .updatedAt(dateTimeHolder.getDateTime())
                .mentor(mentor)
                .build();
    }
}
