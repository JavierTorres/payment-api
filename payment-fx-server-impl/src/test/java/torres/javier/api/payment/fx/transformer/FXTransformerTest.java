package torres.javier.api.payment.fx.transformer;

import org.junit.Test;
import torres.javier.api.payment.fx.model.FXDTO;
import torres.javier.api.payment.fx.model.Forex;

import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class FXTransformerTest {

  @Test
  public void testToFXDTO() {
    FXDTO fxdto = FXTransformer.toFXDTO.apply(new Forex.Builder()
        .withContactReference("reference1")
        .withCurrency(Currency.getInstance("GBP"))
        .withExchangeRate(1.1F)
        .withId("id1")
        .withOriginalAmount(0.9F)
        .withPaymentId("paymentId1")
        .build());

    assertEquals("reference1", fxdto.getContactReference());
    assertEquals("GBP", fxdto.getCurrency());
    assertEquals(new Float(1.1), fxdto.getExchangeRate());
    assertEquals("id1", fxdto.getId());
    assertEquals(new Float(0.9), fxdto.getOriginalAmount());
    assertEquals("paymentId1", fxdto.getPaymentId());
  }
}
