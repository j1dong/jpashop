package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest { //MemberRepository 테스트 클래스

    @Autowired //스프링이 MemberRepository를 자동으로 넣어줌
    MemberRepository memberRepository;

    @Test
    @Transactional //테스트를 하나의 트랜잭션으로 실행(=테스트 끝나면 자동으로 롤백)
    @Rollback(false) //롤백 안 함 -> 실제 DB에 저장
    public void testMember() { //테스트 메서드 시작
        //given
        Member member = new Member(); //Member 객체 생성
        member.setUsername("memberA"); //이름 설정

        //when
        Long savedID = memberRepository.save(member); //DB에 저장하고 id 반환
        Member findMember = memberRepository.find(savedID); //방금 저장한 id로 다시 조회

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId()); //id가 같은지 검증
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername()); //username이 같은지 검증

        Assertions.assertThat(findMember).isEqualTo(member); //객체(주소) 자체가 같은지 검증
    }

}