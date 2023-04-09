package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository repository;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("조민기");

        // when
        Long resultId = memberService.join(member);

        // then
        assertThat(member.getName()).isEqualTo(memberService.findOne(resultId).get().getName());
    }

    @Test
    void 회원가입_X_중복회원() {
        // given
        Member member = new Member();
        member.setName("조민기");

        Member member2 = new Member();
        member2.setName("조민기");
        // when
        memberService.join(member);

        // then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}