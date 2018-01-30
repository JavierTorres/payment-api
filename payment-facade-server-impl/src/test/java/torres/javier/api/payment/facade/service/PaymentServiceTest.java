package torres.javier.api.payment.facade.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import torres.javier.api.payment.facade.exception.NotFoundException;
import torres.javier.api.payment.facade.model.Payment;
import torres.javier.api.payment.facade.repository.PaymentRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

  @Mock
  PaymentRepository paymentRepository;

  @InjectMocks
  PaymentService paymentService;

  @Test
  public void testGetPaymentById() {
    when(paymentRepository.findOne(anyString())).thenReturn(new Payment("id1", Payment.Type.PAYMENT, "org1", 2L, null));
    Payment payment = paymentService.getPaymentById("ide1");
    assertEquals("id1", payment.getId());
    assertEquals(Payment.Type.PAYMENT, payment.getType());
    assertEquals("org1", payment.getOrganisationId());
    assertEquals(new Long(2), payment.getVersion());
  }

  @Test(expected = NotFoundException.class)
  public void testNotFoundGetPaymentById() {
    when(paymentRepository.findOne(anyString())).thenReturn(null);
    paymentService.getPaymentById("ide1");
  }
}
