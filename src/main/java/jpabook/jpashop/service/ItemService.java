package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //Spring이 이 클래스를 빈으로 등록
@Transactional(readOnly = true) //이 클래스의 모든 메서드는 기본적으로 읽기 전용 트랙잭션으로 실행됨
@RequiredArgsConstructor //final 붙은 필드 생성자 자동 생성
public class ItemService { //ItemService는 ItemRepository에 단순히 위임만 하는 클래스

    private final ItemRepository itemRepository; //리포지토리 주입

    @Transactional //(readOnly = false)가 기본값
    public void saveItem(Item item) {
        itemRepository.save(item); //Item id 없으면 persist(), 있으면 merge()
    }

    @Transactional
    public void updateItem(Long id, String name, int price, int stockQuantity) { //param: 파라미터로 넘어온 준영속 상태의 엔티티
        Item item = itemRepository.findOne(id);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
