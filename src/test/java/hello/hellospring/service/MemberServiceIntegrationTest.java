package hello.hellospring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
}