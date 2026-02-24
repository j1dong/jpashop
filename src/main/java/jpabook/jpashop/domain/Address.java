package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter //값 타입은 변경 불가능하게 설계해야함 -> @Setter는 제거
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() { //기본 생성자
    } //JPA가 객체를 만들기 위해 필요함 -> 생성자 안은 비워둠

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
