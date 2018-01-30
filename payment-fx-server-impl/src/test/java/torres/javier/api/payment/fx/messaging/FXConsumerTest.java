package torres.javier.api.payment.fx.messaging;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import torres.javier.api.payment.fx.model.Forex;
import torres.javier.api.payment.fx.repository.ForexRepository;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FXConsumerTest {

  @Mock
  ForexRepository forexRepository;

  @InjectMocks
  FXConsumer fxConsumer;

  @Test
  public void testConsumeCreateMsg() {
    fxConsumer.consumeCreateMsg(new Gson().toJson(getMessage()));
    verify(forexRepository).save(any(Forex.class));
  }

  @Test
  public void testConsumeDeleteMsg() {
    when(forexRepository.findByPaymentId(anyString())).thenReturn(new Forex("id", "paymentId", "reference1",
        1.1F, 2.2F, Currency.getInstance("EUR")));
    fxConsumer.consumeDeleteMsg(new Gson().toJson(getMessage()));
    verify(forexRepository).deleteByPaymentId(anyString());
  }

  @Test
  public void testConsumeUpdateMsg() {
    when(forexRepository.findByPaymentId(anyString())).thenReturn(new Forex("id", "paymentId", "reference1",
        1.1F, 2.2F, Currency.getInstance("EUR")));
    fxConsumer.consumeUpdateMsg(new Gson().toJson(getMessage()));
    verify(forexRepository).save(any(Forex.class));
  }

  private Map<String, Object> getMessage() {
    Map<String, Object> map = new HashMap<>();
    map.put("id", "id1");
    map.put("currency", "EUR");
    map.put("amount", 2.2F);

    return map;
  }

}