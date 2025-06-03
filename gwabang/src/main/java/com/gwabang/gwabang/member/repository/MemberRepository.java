package com.gwabang.gwabang.member.repository;

import com.gwabang.gwabang.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email); //email로 사용자 정보 가져오기
    boolean existsByEmail(String email);

    @Query("SELECT m FROM Member m JOIN FETCH m.department WHERE m.id = :id")
    Optional<Member> findByIdWithDepartment(@Param("id") Long id);
}
