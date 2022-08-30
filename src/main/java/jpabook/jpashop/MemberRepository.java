package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    @PersistenceContext     //주입해준다
    private EntityManager em;//등록을 스프링부트가 해준다.

    public Long save(Member member){//Test 단축키 Shift + Ctrl +T
        em.persist(member);
        return member.getId();  //왜 아이디만? 커멘드만 쿼리를 분리
    }

    public Member find(Long id){//조회
        return em.find(Member.class,id);
    }
}
