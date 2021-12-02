package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("호링링");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        /* jupiter의 Assertions 사용 */
//        Assertions.assertEquals(result, member);  // 테스트 성공
//        Assertions.assertEquals(result, null);  // 테스트 실패

        /* assertj의 Assertions 사용 */
//        Assertions.assertThat(result).isEqualTo(member);  // 객체 직접 사용
        assertThat(result).isEqualTo(member);  // static import를 통한 사용 ( * 단축키 alt+Enter )
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("호링링1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("호링링2");
        repository.save(member2);

        Member result = repository.findByName("호링링1").get();  // 테스트 성공
//        Member result = repository.findByName("호링링1").get();  // 테스트 실패

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("호링링1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("호링링2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
