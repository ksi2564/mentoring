package org.ssafy.mentoring.user.service;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.mentoring.common.service.port.DateTimeHolder;
import org.ssafy.mentoring.user.controller.port.UserService;
import org.ssafy.mentoring.user.domain.User;
import org.ssafy.mentoring.user.service.port.UserRepository;

@Service
@Builder
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DateTimeHolder dateTimeHolder;


    @Override
    @Transactional
    public User upgradeToMentor(long id, String email) {
        User user = userRepository.getById(id);
        User mentor = user.upgradeToMentor(email, dateTimeHolder);
        return userRepository.save(mentor);
    }
}
