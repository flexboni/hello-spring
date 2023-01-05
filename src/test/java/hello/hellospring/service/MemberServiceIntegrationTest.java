package hello.hellospring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

/**
 * MemberServiceIntegrationTest
 */
@SpringBootTest
@Transactional // DB에 데이터를 넣고 나서 rollback 해준다.
public class MemberServiceIntegrationTest {
    @Autowired
    MemberService service;

    @Autowired
    MemberRepository memberRepository;

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
        // given
        // Member member = new Member();
        // member.setName("park");

        // when
        // Member findMember = service.findMember(member.getId()).get();

        // then
        // Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void testFindMembers() {
        // when
        List<Member> findMember = service.findMembers();

        // // then
        // Assertions.assertThat(.isEqualTo(findMember.getName());
    }
}