package torres.javier.api.payment.fx.configuration;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration {

    @Value("${mongodb.port}")
    private int mongoPort;

    @Value("${mongodb.hostname}")
    private String mongoHostname;

    @Value("${mongodb.database.name}")
    private String databaseName;

    public @Bean
    MongoClient mongo() throws Exception {
        return new MongoClient(mongoHostname, mongoPort);
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), databaseName);
    }
}
