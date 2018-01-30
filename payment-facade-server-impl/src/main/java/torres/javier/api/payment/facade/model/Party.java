package torres.javier.api.payment.facade.model;

public class Party {
  private final String accountName;
  private final String accountNumber;
  private final String accountNumberCode;
  private final String address;
  private final String bankId;
  private final String bankIdCode;
  private final String name;

  public Party(String accountName, String accountNumber, String accountNumberCode, String address, String bankId, String bankIdCode, String name) {
    this.accountName = accountName;
    this.accountNumber = accountNumber;
    this.accountNumberCode = accountNumberCode;
    this.address = address;
    this.bankId = bankId;
    this.bankIdCode = bankIdCode;
    this.name = name;
  }

  public String getAccountName() {
    return accountName;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getAccountNumberCode() {
    return accountNumberCode;
  }

  public String getAddress() {
    return address;
  }

  public String getBankId() {
    return bankId;
  }

  public String getBankIdCode() {
    return bankIdCode;
  }

  public String getName() {
    return name;
  }

}
