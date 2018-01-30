package torres.javier.api.payment.facade.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import torres.javier.api.payment.facade.delegate.PaymentApiDelegateImpl;
import torres.javier.api.payment.facade.exception.NotFoundException;
import torres.javier.api.payment.facade.model.Payment;
import torres.javier.api.payment.facade.repository.PaymentRepository;

@Service
public class PaymentService {

  @Autowired
  PaymentRepository paymentRepository;

  private static Logger logger = LoggerFactory.getLogger(PaymentApiDelegateImpl.class);

  public Payment getPaymentById(String paymentId) {
    Payment payment = paymentRepository.findOne(paymentId);
    if (payment == null) {
      String message = "Payment [" + paymentId + "] not found";
      logger.warn(message);
      throw new NotFoundException(message);
    }
    return payment;
  }
}
