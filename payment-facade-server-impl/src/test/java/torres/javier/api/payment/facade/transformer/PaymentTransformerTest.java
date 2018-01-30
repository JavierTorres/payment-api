package torres.javier.api.payment.facade.transformer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import torres.javier.api.payment.facade.model.*;
import torres.javier.api.payment.fx.client.FXApi;

import java.util.Currency;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PaymentTransformerTest {

  @Mock
  private FXApi fxApi;

  private ForexTransformer forexTransformer = new ForexTransformer();
  private PartyTransformer partyTransformer = new PartyTransformer();

  PaymentTransformer paymentTransformer;

  @Before
  public void setUp() {
    paymentTransformer = new PaymentTransformer(forexTransformer, partyTransformer, fxApi);
  }

  @Test
  public void testFromPaymentRequest() {
    Payment payment = paymentTransformer.fromPaymentRequest.apply(
        new PaymentRequest()
            .type(TranscationTypeDTO.PAYMENT)
            .attributes(new PaymentAttributesRequest()
                .amount(1.1F)
                .currency("GBP")
                .endToEndReference("reference1")
                .paymentPurpose("purpose1")
                .paymentScheme(PaymentSchemeDTO.FPS)
                .paymentType(PaymentTypeDTO.CREDIT)
                .referene("reference2")
                .schemePaymentSubType(SchemaPaymentSubTypeDTO.BANKING)
                .schemePaymentType(SchemaPaymentTypeDTO.PAYMENT)
                .beneficiaryParty(new PartyDTO()
                    .accountName("accountName1")
                    .accountNumber("accountNumber1")
                    .accountNumberCode("accountNumberCode1")
                    .address("address1")
                    .bankId("bank1")
                    .bankIdCode("bankIdCode1")
                    .name("name1"))
                .debtorParty(new PartyDTO()
                  .accountName("accountName2")
                  .accountNumber("accountNumber2")
                  .accountNumberCode("accountNumberCode2")
                  .address("address2")
                  .bankId("bank2")
                  .bankIdCode("bankIdCode2")
                  .name("name2"))
                .sponsorParty(new PartyDTO()
                  .accountName("accountName2")
                  .accountNumber("accountNumber2")
                  .accountNumberCode("accountNumberCode2")
                  .address("address2")
                  .bankId("bank2")
                  .bankIdCode("bankIdCode2")
                  .name("name2"))
                  )).build();

    assertEquals(Payment.Type.PAYMENT, payment.getType());
    assertEquals(new Float(1.1F), payment.getAttributes().getAmount());
    assertEquals(Currency.getInstance("GBP"), payment.getAttributes().getCurrency());
    assertEquals("reference1", payment.getAttributes().getEndToEndReference());
    assertEquals("purpose1", payment.getAttributes().getPaymentPurpose());
    assertEquals(Attributes.SchemaPaymentType.IMMEDIATE_PAYMENT, payment.getAttributes().getSchemaPaymentType());
    assertEquals(Attributes.Type.CREDIT, payment.getAttributes().getType());
    assertEquals("reference2", payment.getAttributes().getReference());
    assertEquals(Attributes.SchemaPaymentSubType.INTERNET_BANKING, payment.getAttributes().getSchemaPaymentSubType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFromPaymentRequestIncorrectCurrencyCode() {
    paymentTransformer.fromPaymentRequest.apply(
        new PaymentRequest()
            .type(TranscationTypeDTO.PAYMENT)
            .attributes(new PaymentAttributesRequest()
                .amount(1.1F)
                .currency("INCORRECT")
                .endToEndReference("reference1")
                .paymentPurpose("purpose1")
                .paymentScheme(PaymentSchemeDTO.FPS)
                .paymentType(PaymentTypeDTO.CREDIT)
                .referene("reference2")
                .schemePaymentSubType(SchemaPaymentSubTypeDTO.BANKING)
                .schemePaymentType(SchemaPaymentTypeDTO.PAYMENT)
            )).build();
  }
}
