package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {  // join 메서드
        // given : 받는 데이터 / 사용할 데이터
        Member member = new Member();
        member.setName("호링링");

        // when : 실행부
        Long saveId = memberService.join(member);

        // then : 검증부
        Member foundMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(foundMember.getName());
    }
    
    @Test
    void 회원가입_예외_중복회원검증(){  // join 메서드_예외
        // given : 받는 데이터 / 사용할 데이터
        Member member1 = new Member();
        member1.setName("호링링");

        Member member2 = new Member();
        member2.setName("호링링");

        // when : 실행부
        memberService.join(member1);
//        방법 1
//        try{
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

//        방법 2-1 : assertThrows 사용
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        
//        방법 2-2 : 메세지까지 검증
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then : 검증부
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}