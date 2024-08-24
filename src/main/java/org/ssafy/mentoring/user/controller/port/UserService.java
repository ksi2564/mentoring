package org.ssafy.mentoring.user.controller.port;

import org.ssafy.mentoring.user.domain.User;

public interface UserService {
    User upgradeToMentor(long id, String email);
}
