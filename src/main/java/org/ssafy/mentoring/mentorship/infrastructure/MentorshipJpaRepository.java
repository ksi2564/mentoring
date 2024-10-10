package org.ssafy.mentoring.mentorship.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipStatus;

public interface MentorshipJpaRepository extends JpaRepository<MentorshipEntity, Long> {
    Page<Mentorship> findAllByStatus(MentorshipStatus status, Pageable pageable);
}
