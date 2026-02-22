package jpabook.jpashop;

import jakarta.persistence.Entity; //아 클래스는 DB 테이블임을 알려주는 어노테이션
import jakarta.persistence.GeneratedValue; //기본키 값 자동 생성 기능
import jakarta.persistence.Id; //이 필드가 기본키임을 표시
import lombok.Getter; //getter, setter 메서드를 자동으로 만들어줌
import lombok.Setter;

@Entity //이 클래스는 DB 테이블과 매핑된다는 의미 (Member -> member 테이블 생성)
@Getter @Setter
public class Member { //Member 라는 엔티티 클래스 생성(= DB 테이블 1개)

    @Id @GeneratedValue //기본키 지정, DB가 id 값 자동 생성
    private Long id; //DB 테이블의 PK(기본키) 컬럼 -> 회원 고유 번호(=Id)
    private String username; //DB 테이블의 회원 이름 컬럼

}