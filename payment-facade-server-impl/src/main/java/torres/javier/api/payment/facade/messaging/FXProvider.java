package torres.javier.api.payment.facade.messaging;

import com.google.gson.Gson;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import torres.javier.api.payment.facade.configuration.MessagingConfiguration;
import torres.javier.api.payment.facade.model.Payment;
import torres.javier.api.payment.facade.transformer.ForexTransformer;

@Component
public class FXProvider {
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private MessagingConfiguration messagingConfiguration;

  @Autowired
  private DirectExchange direct;

  @Autowired
  private ForexTransformer forexTransformer;

  public void create(final Payment payment) {
    send(payment, messagingConfiguration.getCreateRounting());
  }

  public void delete(final Payment payment) {
    send(payment, messagingConfiguration.getDeleteRounting());
  }

  public void update(final Payment payment) {
    send(payment, messagingConfiguration.getUpdateRounting());
  }

  private void send(final Payment payment, final String rountingKey) {
    rabbitTemplate.convertAndSend(direct.getName(), rountingKey,
        new Gson().toJson(forexTransformer.toFxMessage.apply(payment)));
  }
}
