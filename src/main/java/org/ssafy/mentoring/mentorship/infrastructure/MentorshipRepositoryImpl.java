package org.ssafy.mentoring.mentorship.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.serivce.port.MentorshipRepository;

@Repository
@RequiredArgsConstructor
public class MentorshipRepositoryImpl implements MentorshipRepository {

    private final MentorshipJpaRepository mentorshipJpaRepository;

    @Override
    public Mentorship save(Mentorship mentorship) {
        return mentorshipJpaRepository.save(MentorshipEntity.from(mentorship)).toModel();
    }
}
