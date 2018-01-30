package torres.javier.api.payment.fx.model;

import com.mongodb.annotations.Immutable;
import org.springframework.data.annotation.Id;

import java.util.Currency;

@Immutable
public class Forex {
  @Id
  private final String id;
  private final String paymentId;
  private final String contactReference;
  private final float exchangeRate;
  private final float originalAmount;
  private final Currency currency;

  public Forex(String id, String paymentId, String contactReference, float exchangeRate, float originalAmount, Currency currency) {
    this.id = id;
    this.paymentId = paymentId;
    this.contactReference = contactReference;
    this.exchangeRate = exchangeRate;
    this.originalAmount = originalAmount;
    this.currency = currency;
  }

  public String getContactReference() {
    return contactReference;
  }

  public float getExchangeRate() {
    return exchangeRate;
  }

  public float getOriginalAmount() {
    return originalAmount;
  }

  public Currency getCurrency() {
    return currency;
  }

  public String getId() {
    return id;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public static class Builder {
    private String id;
    private String paymentId;
    private String contactReference;
    private float exchangeRate;
    private float originalAmount;
    private Currency currency;

    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    public Builder withPaymentId(String paymentId) {
      this.paymentId = paymentId;
      return this;
    }

    public Builder withContactReference(String contactReference) {
      this.contactReference = contactReference;
      return this;
    }

    public Builder withExchangeRate(float exchangeRate) {
      this.exchangeRate = exchangeRate;
      return this;
    }

    public Builder withOriginalAmount(float originalAmount) {
      this.originalAmount = originalAmount;
      return this;
    }

    public Builder withCurrency(Currency currency) {
      this.currency = currency;
      return this;
    }

    public Forex build() {
      return new Forex(id, paymentId, contactReference, exchangeRate, originalAmount, currency);
    }

    public static Builder fromForex(Forex forex) {
      Builder builder = new Builder();
      builder.id = forex.id;
      builder.paymentId = forex.paymentId;
      builder.contactReference = forex.contactReference;
      builder.currency = forex.currency;
      builder.exchangeRate = forex.exchangeRate;
      builder.originalAmount = forex.originalAmount;

      return builder;
    }

  }
}
