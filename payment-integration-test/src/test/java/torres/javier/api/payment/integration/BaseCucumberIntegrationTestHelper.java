package torres.javier.api.payment.integration;

import torres.javier.api.payment.facade.client.PaymentApi;

public class BaseCucumberIntegrationTestHelper {

  public static final String FACADE_SERVER_ENDPOINT_URI = "http://localhost:8080";

  protected static PaymentApi paymentApi;

  public BaseCucumberIntegrationTestHelper() {
    paymentApi = new PaymentApi();
    paymentApi.getApiClient().setBasePath(FACADE_SERVER_ENDPOINT_URI);
    paymentApi.getApiClient().setDebugging(true);
  }
}
