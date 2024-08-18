package org.ssafy.mentoring.common.infrastructure;

import org.springframework.stereotype.Component;
import org.ssafy.mentoring.common.service.port.DateTimeHolder;

import java.time.LocalDateTime;

@Component
public class SystemDateTimeHolder implements DateTimeHolder {

    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }
}
