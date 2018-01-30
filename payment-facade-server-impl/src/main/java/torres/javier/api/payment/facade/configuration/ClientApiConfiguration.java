package torres.javier.api.payment.facade.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import torres.javier.api.payment.fx.client.FXApi;

@Configuration
public class ClientApiConfiguration {
  @Value("${fx.url}")
  private String fxUrl;

  public @Bean
  FXApi fxApi() {
    FXApi fxApi = new FXApi();
    fxApi.getApiClient().setBasePath(fxUrl);
    return fxApi;
  }
}
