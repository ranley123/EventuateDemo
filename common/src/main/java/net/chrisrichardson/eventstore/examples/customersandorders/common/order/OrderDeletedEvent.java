package net.chrisrichardson.eventstore.examples.customersandorders.common.order;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class OrderDeletedEvent implements OrderEvent {

    private Money orderCost;

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public OrderDeletedEvent(){
        this.orderCost = null;
    };

    public Money getOrderCost() {
        return this.orderCost;
    }
}
