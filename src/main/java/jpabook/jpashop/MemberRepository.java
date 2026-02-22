package jpabook.jpashop;

import jakarta.persistence.EntityManager; //JPA에서 DB 작업을 실제로 수행하는 객체
import jakarta.persistence.PersistenceContext; //스프링이 자동으로 EntityManager를 주입하도록 하는 어노테이션
import org.springframework.stereotype.Repository; //이 클래스는 DB 계층임을 표시

@Repository //이 클래스는 Repository 역할임을 선언
public class MemberRepository { //Member 관련 DB 작업을 담당하는 클래스
    @PersistenceContext
    private EntityManager em; //EntityManager란 객체를 담는 변수 em 선언

    public Long save(Member member) { //Member (타입의) 객체를 받아 DB에 저장하는 메서드
        em.persist(member); //member 객체를 영속성 컨텍스트에 등록
        return member.getId(); //객체에 세팅된 자동 생성된 Id 값 반환
    }

    public Member find(Long id) { //Id를 받아 해당 Id의 Member를 찾아서 객체 반환
        return em.find(Member.class, id);
    }

}
