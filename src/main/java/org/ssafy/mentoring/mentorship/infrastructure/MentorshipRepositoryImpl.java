package org.ssafy.mentoring.mentorship.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.ssafy.mentoring.common.domain.exception.ResourceNotFoundException;
import org.ssafy.mentoring.mentorship.domain.Mentorship;
import org.ssafy.mentoring.mentorship.domain.MentorshipStatus;
import org.ssafy.mentoring.mentorship.serivce.port.MentorshipRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MentorshipRepositoryImpl implements MentorshipRepository {

    private final MentorshipJpaRepository mentorshipJpaRepository;

    @Override
    public Mentorship getById(Long id) {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Mentorship", id));
    }

    @Override
    public Optional<Mentorship> findById(Long id) {
        return mentorshipJpaRepository.findById(id).map(MentorshipEntity::toModel);
    }

    @Override
    public Mentorship save(Mentorship mentorship) {
        return mentorshipJpaRepository.save(MentorshipEntity.from(mentorship)).toModel();
    }

    @Override
    public Page<Mentorship> findAllByStatus(MentorshipStatus status, Pageable pageable) {
        return mentorshipJpaRepository.findAllByStatus(status, pageable);
    }
}
