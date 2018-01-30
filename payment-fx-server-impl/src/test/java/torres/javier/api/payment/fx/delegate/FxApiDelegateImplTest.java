package torres.javier.api.payment.fx.delegate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import torres.javier.api.payment.fx.model.FXDTO;
import torres.javier.api.payment.fx.model.Forex;
import torres.javier.api.payment.fx.repository.ForexRepository;

import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FxApiDelegateImplTest {
  @Mock
  ForexRepository forexRepository;

  @InjectMocks
  FxApiDelegateImpl fxApiDelegate;

  @Test
  public void testGetFX() {
    when(forexRepository.findByPaymentId(anyString())).thenReturn(new Forex("id", "paymentid1",
        "reference1", 1.1F, 2.2F, Currency.getInstance("GBP")));
    ResponseEntity<FXDTO> fx = fxApiDelegate.getFX("payment1");
    assertEquals(HttpStatus.OK, fx.getStatusCode());
    assertEquals("id", fx.getBody().getId());
    assertEquals("paymentid1", fx.getBody().getPaymentId());
    assertEquals("reference1", fx.getBody().getContactReference());
    assertEquals(new Float(1.1), fx.getBody().getExchangeRate());
    assertEquals(new Float(2.2), fx.getBody().getOriginalAmount());
  }
}
