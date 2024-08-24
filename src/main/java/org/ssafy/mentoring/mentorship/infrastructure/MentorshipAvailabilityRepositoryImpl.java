package org.ssafy.mentoring.mentorship.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.mentoring.mentorship.domain.MentorshipAvailability;
import org.ssafy.mentoring.mentorship.serivce.port.MentorshipAvailabilityRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MentorshipAvailabilityRepositoryImpl implements MentorshipAvailabilityRepository {

    private final MentorshipAvailabilityJpaRepository mentorshipAvailabilityJpaRepository;

    @Override
    public MentorshipAvailability save(MentorshipAvailability mentorshipAvailability) {
        return mentorshipAvailabilityJpaRepository.save(MentorshipAvailabilityEntity.from(mentorshipAvailability)).toModel();
    }

    @Override
    public List<MentorshipAvailability> saveAll(List<MentorshipAvailability> availabilities) {
        return MentorshipAvailabilityEntity.convertToModelList(
                mentorshipAvailabilityJpaRepository.saveAll(MentorshipAvailabilityEntity.from(availabilities))
        );
    }
}
