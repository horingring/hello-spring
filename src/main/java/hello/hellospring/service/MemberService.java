package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {


    //  인스턴스 생성 시, 외부 간섭 없이 내부에 MemberRepository가 생성
    //    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //  인스턴스 생성 시 외부에서 MemberRepository를 입력
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {  // 생성자
        this.memberRepository = memberRepository;
    }


    /* 회원가입 */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 회원가입 x
        validateDuplicateMember(member);  // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /* id를 통한 회원 조회 */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
