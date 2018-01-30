package torres.javier.api.payment.integration;

import cucumber.api.java8.En;
import org.springframework.web.client.HttpClientErrorException;
import torres.javier.api.payment.facade.client.model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PaymentSteps extends BaseCucumberIntegrationTestHelper implements En {

  public PaymentSteps() throws Exception {
    When("^a client creates a payment passing the required fields$", () -> {
      try {
        PaymentDTO paymentDTO = paymentApi.createPayment(getPaymentRequest());
        World.putPaymentDTO(paymentDTO);
        World.putHttpStatus(paymentApi.getApiClient().getStatusCode());
      } catch (HttpClientErrorException e) {
        World.putHttpStatus(e.getStatusCode());
      }
    });

    When("^a client creates a payment without the required fields$", () -> {
      PaymentAttributesRequest paymentAttributesRequest = new PaymentAttributesRequest();
      paymentAttributesRequest.amount(10.0F);
      paymentAttributesRequest.currency("GBP");
      PaymentRequest paymentRequest = new PaymentRequest();
      paymentRequest.type(TranscationTypeDTO.PAYMENT);
      paymentRequest.attributes(paymentAttributesRequest);

      try {
        PaymentDTO paymentDTO = paymentApi.createPayment(paymentRequest);
        World.putPaymentDTO(paymentDTO);
        World.putHttpStatus(paymentApi.getApiClient().getStatusCode());
      } catch (HttpClientErrorException e) {
        World.putHttpStatus(e.getStatusCode());
      }
    });

    When("^a client request for a non existing payment$", () -> {
      try {
        PaymentDTO paymentDTO = paymentApi.getPayment("nonExiting");
      } catch (HttpClientErrorException e) {
        World.putHttpStatus(e.getStatusCode());
      }
    });

    When("^a client request the existing payment$", () -> {
      try {
        PaymentDTO paymentDTO = paymentApi.getPayment(World.getPaymentDTO().getId());
        World.putPaymentDTO(paymentDTO);
        World.putHttpStatus(paymentApi.getApiClient().getStatusCode());
      } catch (HttpClientErrorException e) {
        World.putHttpStatus(e.getStatusCode());
      }
    });

    Then("^the response status is (\\d+)$", (Integer httpStatus) -> {
      assertEquals(httpStatus.intValue(), World.getHttpStatus().value());
    });

    And("^the payment is returned in the response$", () -> {
      assertNotNull(World.getPaymentDTO().getId());
    });

    And("^the payment includes FX data$", () -> {
      assertNotNull(World.getPaymentDTO().getAttributes().getFx().getId());
    });

    And("^the number of payments is (\\d+)$", (Integer totalPayments) -> {
      assertEquals(totalPayments.intValue(), World.getPaymentList().size());
    });

    When("^a client request to delete a non existing payment$", () -> {
      try {
        paymentApi.deletePayment("nonExiting");
      } catch (HttpClientErrorException e) {
        World.putHttpStatus(e.getStatusCode());
      }
    });

    When("^a client request to delete a existing payment$", () -> {
      try {
        paymentApi.deletePayment(World.getPaymentDTO().getId());
        World.putHttpStatus(paymentApi.getApiClient().getStatusCode());
      } catch (HttpClientErrorException e) {
        World.putHttpStatus(e.getStatusCode());
      }
    });

    When("^list of payments$", () -> {
      try {
        PaymentList paymentList = paymentApi.listPayments();
        World.putHttpStatus(paymentApi.getApiClient().getStatusCode());
        World.putPaymentList(paymentList);
      } catch (HttpClientErrorException e) {
        World.putHttpStatus(e.getStatusCode());
      }
    });

    Given("^a empty list of payments", () -> {
      paymentApi.listPayments()
          .forEach(paymentDTO -> paymentApi.deletePayment(paymentDTO.getId()));
    });

    When("^a client request to update a non existing payment$", () -> {
      try {
        paymentApi.updatePayment("nonExiting", getPaymentRequest());
      } catch (HttpClientErrorException e) {
        World.putHttpStatus(e.getStatusCode());
      }
    });

    When("^a client request to update an existing payment$", () -> {
      try {
        PaymentDTO paymentDTO = paymentApi.updatePayment(World.getPaymentDTO().getId(), getPaymentRequest());
        World.putHttpStatus(paymentApi.getApiClient().getStatusCode());
        World.putPaymentDTO(paymentDTO);
      } catch (HttpClientErrorException e) {
        World.putHttpStatus(e.getStatusCode());
      }
    });
  }

  PaymentRequest getPaymentRequest() {
    PartyDTO debtorParty = new PartyDTO();
    debtorParty.accountName("accountName");
    debtorParty.accountNumber("32432423");
    debtorParty.accountNumberCode("dfss324sdf");
    debtorParty.address("address1");
    debtorParty.bankId("bank1");
    debtorParty.bankIdCode("nbank1TRG");
    PartyDTO beneficiaryParty = new PartyDTO();
    beneficiaryParty.accountName("accountName2");
    beneficiaryParty.accountNumber("A32432423");
    beneficiaryParty.accountNumberCode("Bdfss324sdf");
    beneficiaryParty.address("address2");
    beneficiaryParty.bankId("bank2");
    beneficiaryParty.bankIdCode("nbank2SDR");
    PaymentAttributesRequest paymentAttributesRequest = new PaymentAttributesRequest();
    paymentAttributesRequest.amount(10.0F);
    paymentAttributesRequest.currency("GBP");
    paymentAttributesRequest.endToEndReference("reference1");
    paymentAttributesRequest.paymentPurpose("test");
    paymentAttributesRequest.paymentScheme(PaymentSchemeDTO.FPS);
    paymentAttributesRequest.paymentType(PaymentTypeDTO.DEBIT);
    paymentAttributesRequest.referene("reference2");
    paymentAttributesRequest.schemePaymentSubType(SchemaPaymentSubTypeDTO.BANKING);
    paymentAttributesRequest.schemePaymentType(SchemaPaymentTypeDTO.PAYMENT);
    paymentAttributesRequest.debtorParty(debtorParty);
    paymentAttributesRequest.beneficiaryParty(beneficiaryParty);
    PaymentRequest paymentRequest = new PaymentRequest();
    paymentRequest.type(TranscationTypeDTO.PAYMENT);
    paymentRequest.attributes(paymentAttributesRequest);

    return paymentRequest;
  }
}
