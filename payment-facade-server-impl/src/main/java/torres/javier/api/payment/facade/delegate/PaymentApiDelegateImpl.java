package torres.javier.api.payment.facade.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import torres.javier.api.payment.facade.PaymentApiDelegate;
import torres.javier.api.payment.facade.messaging.FXProvider;
import torres.javier.api.payment.facade.model.Payment;
import torres.javier.api.payment.facade.model.PaymentDTO;
import torres.javier.api.payment.facade.model.PaymentList;
import torres.javier.api.payment.facade.model.PaymentRequest;
import torres.javier.api.payment.facade.repository.PaymentRepository;
import torres.javier.api.payment.facade.service.PaymentService;
import torres.javier.api.payment.facade.transformer.PaymentTransformer;

@Component
public class PaymentApiDelegateImpl implements PaymentApiDelegate {

  @Autowired
  FXProvider fxProvider;

  @Autowired
  PaymentRepository paymentRepository;

  @Autowired
  PaymentTransformer paymentTransformer;

  @Autowired
  PaymentService paymentService;

  @Override
  public ResponseEntity<PaymentDTO> createPayment(PaymentRequest body) {
    Payment payment = paymentRepository.save(paymentTransformer.fromPaymentRequest.apply(body).build());
    fxProvider.create(payment);

    return ResponseEntity.status(HttpStatus.CREATED).body(paymentTransformer.toPaymentDTO.apply(payment));
  }

  @Override
  public ResponseEntity<PaymentDTO> getPayment(String paymentId) {
    return ResponseEntity.ok(
        paymentTransformer.toPaymentDTOwithAllFields.apply(
            paymentService.getPaymentById(paymentId)));
  }

  @Override
  public ResponseEntity<PaymentList> listPayments() {
    return ResponseEntity.ok(paymentTransformer.toPaymentList.apply(paymentRepository.findAll()));
  }

  @Override
  public ResponseEntity<Void> deletePayment(String paymentId) {
    Payment payment = paymentService.getPaymentById(paymentId);
    paymentRepository.delete(payment);
    fxProvider.delete(payment);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Override
  public ResponseEntity<PaymentDTO> updatePayment(String paymentId, PaymentRequest body) {
    Payment payment = paymentService.getPaymentById(paymentId);
    payment = paymentRepository.save(
        paymentTransformer.fromPaymentRequest.apply(body)
            .withId(payment.getId())
            .build()
    );
    fxProvider.update(payment);

    return ResponseEntity.ok(paymentTransformer.toPaymentDTO.apply(payment));
  }
}
