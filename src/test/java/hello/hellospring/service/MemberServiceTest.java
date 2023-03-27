package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository repository;

    @BeforeEach
    void beforeEach() {
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("조민기");

        // when
        Long resultId = memberService.join(member);

        // then
        assertThat(member).isSameAs(memberService.findOne(resultId).get());
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