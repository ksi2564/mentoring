package org.ssafy.mentoring.mentorship.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorshipJpaRepository extends JpaRepository<MentorshipEntity, Long> {
}
