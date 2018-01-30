package torres.javier.api.payment.fx.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import torres.javier.api.payment.fx.messaging.FXConsumer;

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

  private static class ReceiverConfig {
    @Bean
    public Queue autoDeleteQueue1() {
      return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue2() {
      return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue3() {
      return new AnonymousQueue();
    }

    @Bean
    public Binding bindingCreate(DirectExchange direct, Queue autoDeleteQueue1) {
      return BindingBuilder.bind(autoDeleteQueue1)
          .to(direct)
          .with(createRounting);
    }

    @Bean
    public Binding bindingDelete(DirectExchange direct, Queue autoDeleteQueue2) {
      return BindingBuilder.bind(autoDeleteQueue2)
          .to(direct)
          .with(deleteRounting);
    }

    @Bean
    public Binding bindingRemove(DirectExchange direct, Queue autoDeleteQueue3) {
      return BindingBuilder.bind(autoDeleteQueue3)
          .to(direct)
          .with(updateRounting);
    }

    @Bean
    public FXConsumer receiver() {
      return new FXConsumer();
    }
  }
}
