package torres.javier.api.payment.facade.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import torres.javier.api.payment.facade.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

}
