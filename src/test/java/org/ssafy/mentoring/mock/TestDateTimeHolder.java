package org.ssafy.mentoring.mock;

import lombok.RequiredArgsConstructor;
import org.ssafy.mentoring.common.service.port.DateTimeHolder;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class TestDateTimeHolder implements DateTimeHolder {

    private final LocalDateTime localDateTime;

    @Override
    public LocalDateTime getDateTime() {
        return localDateTime;
    }
}
