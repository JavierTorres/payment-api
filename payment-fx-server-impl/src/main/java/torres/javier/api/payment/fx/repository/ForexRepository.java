package torres.javier.api.payment.fx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import torres.javier.api.payment.fx.model.Forex;

public interface ForexRepository extends MongoRepository<Forex, String> {
  Forex findByPaymentId(String id);

  void deleteByPaymentId(String id);
}
