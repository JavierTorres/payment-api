package torres.javier.api.payment.facade.transformer;

import org.springframework.stereotype.Component;
import torres.javier.api.payment.facade.model.ForexDTO;
import torres.javier.api.payment.facade.model.Payment;
import torres.javier.api.payment.fx.client.model.FXDTO;

import java.util.HashMap;
import java.util.function.Function;

@Component
public class ForexTransformer {

  public Function<FXDTO, ForexDTO> toForexDTO = fxdto ->
    new ForexDTO()
      .contactReference(fxdto.getContactReference())
      .currency(fxdto.getCurrency())
      .exchangeRate(fxdto.getExchangeRate())
      .id(fxdto.getId())
      .paymentId(fxdto.getPaymentId())
      .originalAmount(fxdto.getOriginalAmount());

  public Function<Payment, HashMap<String, Object>> toFxMessage = payment -> {
    HashMap<String, Object> message = new HashMap<>();
    message.put("paymentId", payment.getId());
    message.put("amount", payment.getAttributes().getAmount());
    message.put("currency", payment.getAttributes().getCurrency());
    message.put("reference", payment.getAttributes().getReference());

    return message;
  };
}
