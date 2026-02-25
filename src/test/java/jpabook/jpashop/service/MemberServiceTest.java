package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test //회원가입이 잘 되는지 검증하는 테스트
    public void 회원가입() throws Exception { //예외 상황은 다루지 않음
        //given
        Member member = new Member(); //Member 객체 하나 생성
        member.setName("kim"); //Member 이름은 kim으로 설정

        //when
        Long saveId = memberService.join(member); //memberService의 join() 메서드 실행하고 저장된 회원의 ID를 반환받아 저장

        //then
        assertEquals(member, memberRepository.findOne(saveId)); //위에서 저장한 ID로 다시 회원을 DB에서 꺼내와 저장한 member와 같은지 비교 -> 같으면 성공, 다르면 실패
    }

    @Test //같은 이름으로 두 번 회원가입 시, 예외 발생이 잘되는지 확인하는 테스트(=중복 가입 방지 로직 작동 테스트)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member(); //이름이 kim인 회원 생성
        member1.setName("kim");

        Member member2 = new Member(); //이름이 kim인 회원 또 생성
        member2.setName("kim");

        //when
        memberService.join(member1); //첫번째 회원 가입 -> 정상 저장됨

        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //두번째 회원 가입 시도 -> 이 경우에 예외 발생!
    }
}