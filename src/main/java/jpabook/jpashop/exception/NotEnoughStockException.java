package jpabook.jpashop.exception;

public class NotEnoughStockException extends RuntimeException { //재고 부족 시 사용하는 예외

    public NotEnoughStockException() { //기본 생성자
    }

    public NotEnoughStockException(String message) { //메시지를 받는 생성자, 부모 클래스(RuntimeException)의 생성자 호출 -> 예외 메시지를 부모에게 전달
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) { //메시지와 원인을 받는 생성자
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) { //원인만 받는 생성자
        super(cause);
    }
}