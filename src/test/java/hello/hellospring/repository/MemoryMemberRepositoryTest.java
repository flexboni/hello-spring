package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 각 테스트 케이스가 실행된 후 AfterEach 가 실행된다.
     * 테스트 케이스 별 기존 데이터가 남아 있는 경우 초기화 해주는 코드를 작성함.
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("kim");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("Park");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("Choi");
        repository.save(member2);

        Member result = repository.findByName("Park").get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findAll() {
        Member member = new Member();
        member.setName("Park");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("Choi");
        repository.save(member2);

        ArrayList<Member> members = new ArrayList<>();
        members.add(member);
        members.add(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(members).isEqualTo(result);
    }
}
