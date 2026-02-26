package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    //비즈니스 로직
    public void addStock(int quantity) { //상품 재고 수량 증가 로직
        this.stockQuantity += quantity; //현재 재고 + 추가 수량
    }

    public void removeStock(int quantity) { //상품 재고 수량 감소 로직
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) { //현재 재고보다 더 많이 감소하면 안됨
            throw new NotEnoughStockException("need more stock"); //더 많이 감소 시 예외 발생!
        }
        this.stockQuantity = restStock; //예외 발생 없을 시 정상적으로 재고 감소
    }
}