package org.ssafy.mentoring.mock;

import org.ssafy.mentoring.mentorship.domain.MentorshipAvailability;
import org.ssafy.mentoring.mentorship.serivce.port.MentorshipAvailabilityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class FakeMentorshipAvailabilityRepository implements MentorshipAvailabilityRepository {

    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<MentorshipAvailability> data = new ArrayList<>();


    @Override
    public MentorshipAvailability save(MentorshipAvailability mentorshipAvailability) {
        if (mentorshipAvailability.getId() == null || mentorshipAvailability.getId() == 0) {
            MentorshipAvailability newMentorshipAvailability = MentorshipAvailability.builder()
                    .id(autoGeneratedId.incrementAndGet())
                    .slot(mentorshipAvailability.getSlot())
                    .createdAt(mentorshipAvailability.getCreatedAt())
                    .updatedAt(mentorshipAvailability.getUpdatedAt())
                    .mentorship(mentorshipAvailability.getMentorship())
                    .build();
            data.add(newMentorshipAvailability);
            return newMentorshipAvailability;
        } else {
            data.removeIf(item -> Objects.equals(item.getId(), mentorshipAvailability.getId()));
            data.add(mentorshipAvailability);
            return mentorshipAvailability;
        }
    }

    @Override
    public List<MentorshipAvailability> saveAll(List<MentorshipAvailability> availabilities) {
        List<MentorshipAvailability> result = new ArrayList<>();

        for (MentorshipAvailability availability : availabilities) {
            result.add(this.save(availability));
        }

        return result;
    }
}
