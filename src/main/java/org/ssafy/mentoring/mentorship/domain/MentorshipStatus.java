package org.ssafy.mentoring.mentorship.domain;

public enum MentorshipStatus {
    REGISTERED,   // 회원이 멘토십을 등록한 상태
    APPROVED,     // 관리자가 확인하고 허용한 상태
    DEACTIVATED   // 회원이 멘토십을 비활성화한 상태
}
