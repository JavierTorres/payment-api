package torres.javier.api.payment.fx.messaging;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import torres.javier.api.payment.fx.model.Forex;
import torres.javier.api.payment.fx.repository.ForexRepository;

import java.util.Currency;
import java.util.Map;

@Component
public class FXConsumer {

  @Autowired
  ForexRepository forexRepository;

  private static Logger logger = LoggerFactory.getLogger(FXConsumer.class);

  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void consumeCreateMsg(String message) {
    logger.info("FX message received: " + message);
    Map<String, Object> messageMap = new Gson().fromJson(message, Map.class);

    forexRepository.save(
        new Forex.Builder()
            .withPaymentId((String) messageMap.get("paymentId"))
            .withContactReference((String) messageMap.get("reference"))
            .withCurrency(Currency.getInstance((String) messageMap.get("currency")))
            .withOriginalAmount(((Double) messageMap.get("amount")).floatValue())
            .withExchangeRate(getExchangeRate(((String) messageMap.get("currency"))))
            .build()
    );
  }

  @RabbitListener(queues = "#{autoDeleteQueue2.name}")
  public void consumeDeleteMsg(String message) {
    logger.info("FX message received: " + message);
    Map<String, Object> messageMap = new Gson().fromJson(message, Map.class);
    forexRepository.deleteByPaymentId((String) messageMap.get("paymentId"));
  }

  @RabbitListener(queues = "#{autoDeleteQueue3.name}")
  public void consumeUpdateMsg(String message) {
    logger.info("FX message received: " + message);
    Map<String, Object> messageMap = new Gson().fromJson(message, Map.class);

    forexRepository.save(
        Forex.Builder.fromForex(forexRepository.findByPaymentId((String) messageMap.get("paymentId")))
          .withPaymentId((String) messageMap.get("paymentId"))
          .withContactReference((String) messageMap.get("reference"))
          .withCurrency(Currency.getInstance((String) messageMap.get("currency")))
          .withOriginalAmount(((Double) messageMap.get("amount")).floatValue())
          .withExchangeRate(getExchangeRate(((String) messageMap.get("currency"))))
          .build()
    );
  }

  /**
   * This method is simulating a called a third-party service to get the exchange rate
   * @param currencyCode
   * @return
   */
  private Float getExchangeRate(String currencyCode) {
    float exchangeRate = 1.0F;
    if (currencyCode != "GBP") {
      // Calling a to get the exchange rate against the local currency GBP
      exchangeRate = 0.89F;
    }

    return exchangeRate;
  }
}
