package org.ssafy.mentoring.mentorship.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MentorshipCreate {

    private final long userId;
    private final String email;
    private final String title;
    private final String content;
    private final List<LocalDateTime> availableSlots;
    private final int fee;

    @Builder
    public MentorshipCreate(long userId, String email, String title, String content, List<LocalDateTime> availableSlots, int fee) {
        this.userId = userId;
        this.email = email;
        this.title = title;
        this.content = content;
        this.availableSlots = availableSlots;
        this.fee = fee;
    }
}
