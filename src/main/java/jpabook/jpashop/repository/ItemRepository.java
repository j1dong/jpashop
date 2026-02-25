package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //스프링이 이 클래스를 빈으로 등록
@RequiredArgsConstructor //final 붙은 필드를 생성자로 자동 주입
public class ItemRepository { //Item을 DB에 저장 및 조회하는 역할의 리포지토리

    private final EntityManager em; //EntityManager 주입

    public void save(Item item) {
        if (item.getId() == null) { //id가 null이면,
            em.persist(item); //아직 저장이 안 된 새로운 객체 -> DB에 삽입됨
        } else { //id가 이미 있으면,
            em.merge(item); //기존 객체를 수정
        }
    }

    public Item findOne(Long id) { //기본키(id)로 Item 조회
        return em.find(Item.class, id); //Item 객체 찾아서 반환, 못찾으면 null 반환
    }

    public List<Item> findAll() { //Item 전부 조회
        return em.createQuery("select i from Item i", Item.class) //JPQL을 작성 및 실행하여
                .getResultList(); //모든 Item을 리스트로 반환
    }
}
