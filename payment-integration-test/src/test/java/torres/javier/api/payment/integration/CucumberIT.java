package torres.javier.api.payment.integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
            "src/test/resources/create_payment.feature",
            "src/test/resources/find_payment.feature",
            "src/test/resources/delete_payment.feature",
            "src/test/resources/list_payment.feature",
            "src/test/resources/update_payment.feature"
        },
        strict = true,
        tags = {"~@ignore"},
        format = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        glue = { "classpath:torres/javier/api/payment/integration" }
)
public class CucumberIT {

}
