package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)    //junit 한테 알려준다
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;



    @Test
    @Transactional//EntityManager 를 통한 모든 데이터 변경은 Transaction 안에서 이루어져야한다.
    //Transactional이 테스트케이스에 있으면 테스트가 끝나고 Rollback을 해버린다. 반복적인 테스트를 위해
    @Rollback(false)
    public void testMember() {
        //given
        Member member = new Member();
        member.setUsername("memberA");
        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);
        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성 보 장
        //같은 영속성 컨텍스트 안에서는 같은 아이디는 같은 객체로 식별   1차캐쉬에서 나간다. select도 안함
    }
}

