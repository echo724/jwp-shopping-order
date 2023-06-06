package cart.domain;

public interface DeliveryPolicy {
    Money calculateDeliveryFee(Order order);

    Long getId();

    Money getFee();

    String getName();
}
