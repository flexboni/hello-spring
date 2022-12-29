package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

/**
 * MemberService
 */
@Service
public class MemberService {
    private final MemberRepository repository;

    @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(Member member) {
        validateDuplicatedMember(member);

        repository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        // 같은 이름이 있는 중복 회원X
        repository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findMember(Long id) {
        return repository.findById(id);
    }

}