package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //JPA의 모든 데이타 변경이나 어떤 로직들은 트랜잭션안에서 실행되어야한다.
//@AllArgsConstructor
@RequiredArgsConstructor    //final 이 있는 것만 가지고 생성자를 만들어줌
public class MemberService {//Shift + Ctrl + T(테스트)

     //필드 인젝션
    private final MemberRepository memberRepository;

//    @Autowired //세터 인젝션
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }


//    @Autowired //생성자 인젝션 @Autowired 생략가능.생성자 하나만 있으면 자동으로 주입
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원가입
     */
    @Transactional
    public  Long join(Member member){
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        //이렇게 해도 문제가 된다. 멤버A가(같은 이름) 둘이 동시에 가입하면 문제가 된다. 통과한다.멀티쓰레드상황을 고려해서.
        //멤버의 name을 유니크 제약조건으로 잡아줘야한다.
    }

    //회원 전체 조회
    public  List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //단건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
