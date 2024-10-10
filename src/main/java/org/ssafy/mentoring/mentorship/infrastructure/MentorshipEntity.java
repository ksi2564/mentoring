package org.ssafy.mentoring.mentorship.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipStatus;
import org.ssafy.mentoring.user.infrastructure.UserEntity;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "mentorship")
public class MentorshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "fee", nullable = false)
    private Integer fee;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MentorshipStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity mentor;


    public static MentorshipEntity from(Mentorship mentorship) {
        MentorshipEntity mentorshipEntity = new MentorshipEntity();
        mentorshipEntity.id = mentorship.getId();
        mentorshipEntity.title = mentorship.getTitle();
        mentorshipEntity.content = mentorship.getContent();
        mentorshipEntity.fee = mentorship.getFee();
        mentorshipEntity.status = mentorship.getStatus();
        mentorshipEntity.createdAt = mentorship.getCreatedAt();
        mentorshipEntity.updatedAt = mentorship.getUpdatedAt();
        mentorshipEntity.mentor = UserEntity.from(mentorship.getMentor());
        return mentorshipEntity;
    }


    public Mentorship toModel() {
        return Mentorship.builder()
                .id(id)
                .title(title)
                .content(content)
                .fee(fee)
                .status(status)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .mentor(mentor.toModel())
                .build();
    }
}
