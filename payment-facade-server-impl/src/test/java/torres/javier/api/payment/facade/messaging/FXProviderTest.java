package torres.javier.api.payment.facade.messaging;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import torres.javier.api.payment.facade.configuration.MessagingConfiguration;
import torres.javier.api.payment.facade.model.Attributes;
import torres.javier.api.payment.facade.model.Payment;
import torres.javier.api.payment.facade.transformer.ForexTransformer;

import java.time.LocalDate;
import java.util.Currency;
import java.util.function.Function;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FXProviderTest {
  @Mock
  private RabbitTemplate rabbitTemplate;

  @Mock
  MessagingConfiguration messagingConfiguration;

  @Mock
  private DirectExchange direct;

  @Mock
  ForexTransformer forexTransformer;

  @InjectMocks
  FXProvider fxProvider;

  @Before
  public void setUp() {
    when(direct.getName()).thenReturn("direct");
    forexTransformer.toFxMessage = mock(Function.class);
  }

  @Test
  public void testCreate() {
    when(messagingConfiguration.getCreateRounting()).thenReturn("fx-creating");
    fxProvider.create(getPayment());
    verify(rabbitTemplate).convertAndSend(anyString(), anyString(), anyString());
  }

  @Test
  public void testDelete() {
    when(messagingConfiguration.getCreateRounting()).thenReturn("fx-delete");
    fxProvider.delete(getPayment());
    verify(rabbitTemplate).convertAndSend(anyString(), anyString(), anyString());
  }

  @Test
  public void testUpdate() {
    when(messagingConfiguration.getCreateRounting()).thenReturn("fx-update");
    fxProvider.update(getPayment());
    verify(rabbitTemplate).convertAndSend(anyString(), anyString(), anyString());
  }

  private Payment getPayment() {
    return new Payment("id1", Payment.Type.PAYMENT, "org1", 2L,
        new Attributes(1.1F, Currency.getInstance("GBP"), "reference1", "purpose1",
            Attributes.Scheme.FPS, LocalDate.now(), "reference2", Attributes.SchemaPaymentSubType.INTERNET_BANKING,
            Attributes.SchemaPaymentType.IMMEDIATE_PAYMENT, null, null, null, Attributes.Type.CREDIT));
  }
}
