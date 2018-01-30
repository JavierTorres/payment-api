package torres.javier.api.payment.fx.transformer;

import torres.javier.api.payment.fx.model.FXDTO;
import torres.javier.api.payment.fx.model.Forex;

import java.util.function.Function;

public class FXTransformer {
  public static Function<Forex, FXDTO> toFXDTO = forex ->
      new FXDTO()
          .contactReference(forex.getContactReference())
          .currency(forex.getCurrency().getCurrencyCode())
          .exchangeRate(forex.getExchangeRate())
          .id(forex.getId())
          .paymentId(forex.getPaymentId())
          .originalAmount(forex.getOriginalAmount());
}
