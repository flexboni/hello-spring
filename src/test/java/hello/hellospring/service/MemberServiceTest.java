package hello.hellospring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberServiceTest {
    MemberService service;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void testJoin() {
        // given
        Member member = new Member();
        member.setName("park");

        // when
        Long joinedId = service.join(member);

        // then
        Member findMember = service.findMember(joinedId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void duplicatedMember() {
        // given
        Member member = new Member();
        member.setName("park");

        Member member2 = new Member();
        member2.setName("park");

        // when
        service.join(member);

        // then
        assertThrows(IllegalStateException.class, () -> service.join(member2));

    }

    @Test
    void testFindMember() {

    }

    @Test
    void testFindMembers() {

    }

}
