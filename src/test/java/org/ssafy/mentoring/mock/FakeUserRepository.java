package org.ssafy.mentoring.mock;

import org.ssafy.mentoring.common.domain.exception.ResourceNotFoundException;
import org.ssafy.mentoring.user.domain.User;
import org.ssafy.mentoring.user.service.port.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeUserRepository implements UserRepository {

    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<User> data = new ArrayList<>();

    @Override
    public User getById(long id) {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", id));
    }

    @Override
    public Optional<User> findById(long id) {
        return data.stream().filter(item -> item.getId().equals(id)).findAny();
    }

    @Override
    public Optional<User> findBySocialId(String socialId) {
        return data.stream().filter(item -> item.getSocialId().equals(socialId)).findAny();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null || user.getId() == 0) {
            User newUser = User.builder()
                    .id(autoGeneratedId.incrementAndGet())
                    .socialId(user.getSocialId())
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .status(user.getStatus())
                    .lastLoginAt(user.getLastLoginAt())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .build();
            data.add(newUser);
            return newUser;
        } else {
            data.removeIf(item -> Objects.equals(item.getId(), user.getId()));
            data.add(user);
            return user;
        }
    }
}
