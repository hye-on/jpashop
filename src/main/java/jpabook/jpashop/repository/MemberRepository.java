package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

//스프링 빈으로 등록
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //스프링이 EntityManager를 주입해준다.
   // @PersistenceContext
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);//영속성 컨텍스트에 meber 객체를 넣는다. 트랜잭션이 commit되는 시점에 DB에 반영된다.
    }

    //단건조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);//두번째에 pk를 넣어주면 된다.
    }

    //sql과 차이점: 테이블이 아닌 객체를 대상으로 쿼리를 한다.
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)//두번째:반환타입
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)//:name->파라미터 바인딩
                .setParameter("name", name).getResultList();
    }
}
