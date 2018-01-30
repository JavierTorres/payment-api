package torres.javier.api.payment.fx.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import torres.javier.api.payment.fx.FxApiDelegate;
import torres.javier.api.payment.fx.model.FXDTO;
import torres.javier.api.payment.fx.repository.ForexRepository;
import torres.javier.api.payment.fx.transformer.FXTransformer;

@Component
public class FxApiDelegateImpl implements FxApiDelegate {
  @Autowired
  ForexRepository forexRepository;

  @Override
  public ResponseEntity<FXDTO> getFX(String paymentId) {
    return ResponseEntity.ok(FXTransformer.toFXDTO.apply(forexRepository.findByPaymentId(paymentId)));
  }
}
