package org.ssafy.mentoring.mentorship.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MentorshipCreate {

    private final long userId;
    private final String email;
    private final String title;
    private final String content;
    private final int fee;

    @Builder
    public MentorshipCreate(long userId, String email, String title, String content, int fee) {
        this.userId = userId;
        this.email = email;
        this.title = title;
        this.content = content;
        this.fee = fee;
    }
}
