package torres.javier.api.payment.facade.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import torres.javier.api.payment.facade.delegate.PaymentApiDelegateImpl;
import torres.javier.api.payment.facade.model.*;
import torres.javier.api.payment.fx.client.FXApi;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.function.Function;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class PaymentTransformer {

  private FXApi fxApi;
  private ForexTransformer forexTransformer;
  private PartyTransformer partyTransformer;

  @Autowired
  public PaymentTransformer(ForexTransformer forexTransformer, PartyTransformer partyTransformer, FXApi fxApi) {
    this.forexTransformer = forexTransformer;
    this.partyTransformer = partyTransformer;
    this.fxApi = fxApi;
  }

  public Function<PaymentRequest, Payment.Builder> fromPaymentRequest = paymentRequest -> {
    PaymentAttributesRequest paymentAttributesRequest = paymentRequest.getAttributes();

    try {
      Currency.getInstance(paymentAttributesRequest.getCurrency());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Currency code " + paymentAttributesRequest.getCurrency() + " is not allowed");
    }

    return new Payment.Builder()
        .withType(Payment.Type.fromValue((paymentRequest.getType().toString())))
        .withAttributes(
            new Attributes(
                paymentAttributesRequest.getAmount(),
                Currency.getInstance(paymentAttributesRequest.getCurrency()),
                paymentAttributesRequest.getEndToEndReference(),
                paymentAttributesRequest.getPaymentPurpose(),
                Attributes.Scheme.valueOf(paymentAttributesRequest.getPaymentScheme().toString()),
                LocalDate.now(),
                paymentAttributesRequest.getReferene(),
                Attributes.SchemaPaymentSubType.valueOf(paymentAttributesRequest.getSchemePaymentSubType().toString()),
                Attributes.SchemaPaymentType.valueOf(paymentAttributesRequest.getSchemePaymentType().toString()),
                partyTransformer.fromPartyDTO.apply(paymentAttributesRequest.getBeneficiaryParty()),
                partyTransformer.fromPartyDTO.apply(paymentAttributesRequest.getDebtorParty()),
                paymentAttributesRequest.getSponsorParty() != null ? partyTransformer.fromPartyDTO.apply(paymentAttributesRequest.getSponsorParty()) : null,
                Attributes.Type.valueOf(paymentAttributesRequest.getPaymentType().toString())));

  };

  public Function<Payment, PaymentDTO> toPaymentDTOwithAllFields = payment -> {
    PaymentDTO paymentDTO = this.toPaymentDTO.apply(payment);
    PaymentAttributesDTO paymentAttributesDTO = paymentDTO.getAttributes()
        .fx(forexTransformer.toForexDTO.apply(fxApi.getFX(payment.getId())));
    paymentDTO.setAttributes(paymentAttributesDTO);
    return paymentDTO;
  };

  public Function<Payment, PaymentDTO> toPaymentDTO = payment -> {
    Attributes attributes = payment.getAttributes();

    LinkList linkList = new LinkList();
    linkList.add(new LinkDTO().self(
        linkTo(PaymentApiDelegateImpl.class).toUriComponentsBuilder().path("/payment/" + payment.getId()).build().toString()));

    return new PaymentDTO()
        .type(TranscationTypeDTO.fromValue(payment.getType().getValue()))
        .links(linkList)
        .id(payment.getId())
        .attributes(
            new PaymentAttributesDTO()
                .amount(payment.getAttributes().getAmount())
                .currency(payment.getAttributes().getCurrency().getCurrencyCode())
                .endToEndReference(attributes.getEndToEndReference())
                .paymentPurpose(attributes.getPaymentPurpose())
                .processingDate(attributes.getProcessing_date().toString())
                .referene(attributes.getReference())
                .paymentScheme(PaymentSchemeDTO.fromValue(attributes.getSchemaPaymentType().toString()))
                .paymentType(PaymentTypeDTO.fromValue(attributes.getType().toString()))
                .schemePaymentSubType(SchemaPaymentSubTypeDTO.fromValue(attributes.getSchemaPaymentSubType().toString()))
                .schemePaymentType(SchemaPaymentTypeDTO.fromValue(attributes.getSchemaPaymentType().toString()))
                .debtorParty(partyTransformer.toPartyDTO.apply(attributes.getDebtorParty()))
                .sponsorParty(attributes.getSponsorParty() != null ? partyTransformer.toPartyDTO.apply(attributes.getSponsorParty()) : null)
                .beneficiaryParty(partyTransformer.toPartyDTO.apply(attributes.getBeneficaryParty())));
  };

  public Function<List<Payment>, PaymentList> toPaymentList = payments -> {
    PaymentList paymentList = new PaymentList();
    payments.stream()
        .map(payment -> toPaymentDTOwithAllFields.apply(payment))
        .forEach(paymentDTO -> paymentList.add(paymentDTO));

    return paymentList;
  };
}
