package torres.javier.api.payment.facade.configuration;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {

  @Value("${messaging.fx.exchange}")
  private String exchangeFx;

  private static final String createRounting = "forex-create";
  private static final String deleteRounting = "forex-delete";
  private static final String updateRounting= "forex-update";

  @Bean
  public DirectExchange direct() {
    return new DirectExchange(exchangeFx);
  }

  public String getCreateRounting() {
    return createRounting;
  }

  public String getDeleteRounting() {
    return deleteRounting;
  }

  public String getUpdateRounting() {
    return updateRounting;
  }
}
