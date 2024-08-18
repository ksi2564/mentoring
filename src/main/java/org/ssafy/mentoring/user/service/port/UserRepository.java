package org.ssafy.mentoring.user.service.port;

import org.ssafy.mentoring.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    User getById(long id);

    Optional<User> findById(long id);

    Optional<User> findBySocialId(String socialId);

    User save(User user);
}
