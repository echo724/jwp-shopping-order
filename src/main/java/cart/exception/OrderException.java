package cart.exception;

import cart.domain.Member;
import cart.domain.Money;
import cart.domain.Order;

public class OrderException extends RuntimeException {
    public OrderException(final String message) {
        super(message);
    }

    public static class IllegalMember extends OrderException {
        public IllegalMember(final Order order, final Member member) {
            super("해당 유저가 접근할 수 없는 Order 입니다.; orderId=" + order.getId() + ", memberId=" + member.getId());
        }
    }

    public static class NotFound extends OrderException {
        public NotFound(final Long orderId) {
            super("해당 아이디의 Order를 찾을 수 없습니다.; orderId=" + orderId);
        }
    }

    public static class EmptyItemInputException extends OrderException {
        public EmptyItemInputException() {
            super("주문에 필요한 장바구니 상품 id가 입력되지 않았습니다.");
        }
    }

    public static class PriceMismatchException extends OrderException {
        public PriceMismatchException(final Money initialPrice, final Money lastPrice) {
            super("주문 금액이 일치하지 않습니다.; initialPrice=" + initialPrice + ", lastPrice=" + lastPrice);
        }
    }
}
