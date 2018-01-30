package torres.javier.api.payment.integration;


import org.springframework.http.HttpStatus;
import torres.javier.api.payment.facade.client.model.PaymentDTO;
import torres.javier.api.payment.facade.client.model.PaymentList;

import java.util.HashMap;
import java.util.Map;

public class World {

  private static Map<Object, Object> map = new HashMap<>();

  public static void putPaymentDTO(PaymentDTO response) {
    map.put("paymentDTO", response);
  }

  public static PaymentDTO getPaymentDTO() {
    return (PaymentDTO) map.get("paymentDTO");
  }

  public static void putHttpStatus(HttpStatus httpStatus) {
    map.put("httpStatus", httpStatus);
  }

  public static HttpStatus getHttpStatus() {
    return (HttpStatus) map.get("httpStatus");
  }

  public static void putPaymentList(PaymentList paymentList) {
    map.put("paymentList", paymentList);
  }

  public static PaymentList getPaymentList() {
    return (PaymentList) map.get("paymentList");
  }
}
