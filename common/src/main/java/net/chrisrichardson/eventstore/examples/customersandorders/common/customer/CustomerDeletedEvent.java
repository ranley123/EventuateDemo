package net.chrisrichardson.eventstore.examples.customersandorders.common.customer;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class CustomerDeletedEvent implements CustomerEvent {

    private String name;
    private Money creditLimit;

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public CustomerDeletedEvent(){
        this.name = null;
        this.creditLimit = null;
    };

    public String getName() {
        return this.name;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }
}
