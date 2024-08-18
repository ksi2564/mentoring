package org.ssafy.mentoring.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.mentoring.user.domain.User;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<User> findBySocialId(String socialId);
}
