package torres.javier.api.payment.facade.model;

import java.time.LocalDate;
import java.util.Currency;

public class Attributes {
  private final Float amount;
  private final Currency currency;
  private final String endToEndReference;
  private final String paymentPurpose;
  private final Scheme scheme;
  private final LocalDate processing_date;
  private final String reference;
  private final SchemaPaymentSubType schemaPaymentSubType;
  private final SchemaPaymentType schemaPaymentType;
  private final Party beneficaryParty;
  private final Party debtorParty;
  private final Party sponsorParty;
  private final Type type;

  public Attributes(Float amount, Currency currency, String endToEndReference, String paymentPurpose, Scheme scheme,
                    LocalDate processing_date, String reference, SchemaPaymentSubType schemaPaymentSubType,
                    SchemaPaymentType schemaPaymentType, Party beneficaryParty, Party debtorParty, Party sponsorParty,
                    Type type) {
    this.amount = amount;
    this.currency = currency;
    this.endToEndReference = endToEndReference;
    this.paymentPurpose = paymentPurpose;
    this.scheme = scheme;
    this.processing_date = processing_date;
    this.reference = reference;
    this.schemaPaymentSubType = schemaPaymentSubType;
    this.schemaPaymentType = schemaPaymentType;
    this.beneficaryParty = beneficaryParty;
    this.debtorParty = debtorParty;
    this.sponsorParty = sponsorParty;
    this.type = type;
  }

  public Float getAmount() {
    return amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  public String getEndToEndReference() {
    return endToEndReference;
  }

  public String getPaymentPurpose() {
    return paymentPurpose;
  }

  public Scheme getScheme() {
    return scheme;
  }

  public LocalDate getProcessing_date() {
    return processing_date;
  }

  public String getReference() {
    return reference;
  }

  public SchemaPaymentSubType getSchemaPaymentSubType() {
    return schemaPaymentSubType;
  }

  public SchemaPaymentType getSchemaPaymentType() {
    return schemaPaymentType;
  }

  public Party getBeneficaryParty() {
    return beneficaryParty;
  }

  public Party getDebtorParty() {
    return debtorParty;
  }

  public Party getSponsorParty() {
    return sponsorParty;
  }

  public Type getType() {
    return type;
  }

  public enum Scheme {
    FPS
  }

  public enum Type {
    CREDIT, DEBIT
  }

  public enum SchemaPaymentSubType {
    INTERNET_BANKING
  }

  public enum SchemaPaymentType {
    IMMEDIATE_PAYMENT
  }
}
