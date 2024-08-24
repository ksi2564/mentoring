package org.ssafy.mentoring.mentorship.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import org.ssafy.mentoring.mentorship.domain.MentorshipAvailability;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "mentorshipAvailability")
public class MentorshipAvailabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slot", nullable = false)
    private LocalDateTime slot;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "mentorship_id")
    private MentorshipEntity mentorship;


    public static MentorshipAvailabilityEntity from(MentorshipAvailability availability) {
        MentorshipAvailabilityEntity mentorshipAvailabilityEntity = new MentorshipAvailabilityEntity();
        mentorshipAvailabilityEntity.id = availability.getId();
        mentorshipAvailabilityEntity.slot = availability.getSlot();
        mentorshipAvailabilityEntity.createdAt = availability.getCreatedAt();
        mentorshipAvailabilityEntity.updatedAt = availability.getUpdatedAt();
        mentorshipAvailabilityEntity.mentorship = MentorshipEntity.from(availability.getMentorship());
        return mentorshipAvailabilityEntity;
    }

    public static List<MentorshipAvailabilityEntity> from(List<MentorshipAvailability> availabilities) {
        return availabilities.stream()
                .map(MentorshipAvailabilityEntity::from)
                .toList();
    }

    public MentorshipAvailability toModel() {
        return MentorshipAvailability.builder()
                .id(id)
                .slot(slot)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .mentorship(mentorship.toModel())
                .build();
    }

    public static List<MentorshipAvailability> convertToModelList(List<MentorshipAvailabilityEntity> mentorshipAvailabilityEntities) {
        return mentorshipAvailabilityEntities.stream()
                .map(MentorshipAvailabilityEntity::toModel)
                .toList();
    }
}
