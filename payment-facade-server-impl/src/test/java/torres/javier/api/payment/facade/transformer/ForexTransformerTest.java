package torres.javier.api.payment.facade.transformer;

import org.junit.jupiter.api.Test;
import torres.javier.api.payment.facade.model.Attributes;
import torres.javier.api.payment.facade.model.ForexDTO;
import torres.javier.api.payment.facade.model.Payment;
import torres.javier.api.payment.fx.client.model.FXDTO;

import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForexTransformerTest {
  public final ForexTransformer forexTransformer = new ForexTransformer();

  @Test
  public void testToForexDTO() {
    ForexDTO forexDTO = forexTransformer.toForexDTO.apply(new FXDTO()
        .contactReference("contactReference")
        .currency("10.0")
        .exchangeRate(1.1F)
        .id("id1")
        .originalAmount(1.05F)
        .paymentId("paymentId1"));

    assertEquals("contactReference", forexDTO.getContactReference());
    assertEquals("10.0", forexDTO.getCurrency());
    assertEquals(1.1F, forexDTO.getExchangeRate().floatValue());
    assertEquals("id1", forexDTO.getId());
    assertEquals(1.05F, forexDTO.getOriginalAmount().floatValue());
    assertEquals("paymentId1", forexDTO.getPaymentId());
  }

  @Test
  public void testToFxMessage() {
    HashMap<String, Object> fxMessage = forexTransformer.toFxMessage.apply(
        new Payment.Builder()
            .withId("id1")
            .withType(Payment.Type.PAYMENT)
            .withOrganisationId("org1")
            .withVersion(1L)
            .withAttributes(new Attributes(1.1F, Currency.getInstance("GBP"), "reference1",
                "purpose1", Attributes.Scheme.FPS, LocalDate.now(), "reference2", Attributes.SchemaPaymentSubType.INTERNET_BANKING,
                Attributes.SchemaPaymentType.IMMEDIATE_PAYMENT, null, null, null, Attributes.Type.CREDIT))
            .build());

    assertEquals("id1", fxMessage.get("paymentId"));
    assertEquals(1.1F, ((Float) fxMessage.get("amount")).floatValue());
    assertEquals(Currency.getInstance("GBP"), (Currency) fxMessage.get("currency"));
    assertEquals("reference2", fxMessage.get("reference"));
  }
}
