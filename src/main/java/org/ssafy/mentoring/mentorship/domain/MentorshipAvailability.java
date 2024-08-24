package org.ssafy.mentoring.mentorship.domain;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.mentoring.common.service.port.DateTimeHolder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MentorshipAvailability {
    private final Long id;
    private final LocalDateTime slot;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Mentorship mentorship;

    @Builder
    public MentorshipAvailability(Long id, LocalDateTime slot, LocalDateTime createdAt, LocalDateTime updatedAt, Mentorship mentorship) {
        this.id = id;
        this.slot = slot;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.mentorship = mentorship;
    }

    public static List<MentorshipAvailability> from(Mentorship mentorship, List<LocalDateTime> availableSlots, DateTimeHolder dateTimeHolder) {
        LocalDateTime now = dateTimeHolder.getDateTime();
        return availableSlots.stream()
                .map(slot -> MentorshipAvailability.builder()
                        .slot(slot)
                        .createdAt(now)
                        .updatedAt(now)
                        .mentorship(mentorship)
                        .build())
                .toList();
    }
}
