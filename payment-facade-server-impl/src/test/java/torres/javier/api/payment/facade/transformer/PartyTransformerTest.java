package torres.javier.api.payment.facade.transformer;

import org.junit.jupiter.api.Test;
import torres.javier.api.payment.facade.model.Party;
import torres.javier.api.payment.facade.model.PartyDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartyTransformerTest {
  PartyTransformer partyTransformer = new PartyTransformer();

  @Test
  public void testFromPartyDTO() {
    Party party = partyTransformer.fromPartyDTO.apply(
        new PartyDTO()
            .accountName("accountName1")
            .accountNumber("accountNumber1")
            .accountNumberCode("accountNumberCode1")
            .address("address1")
            .bankId("bank1")
            .bankIdCode("bankIdCode1")
            .name("name1"));

    assertEquals("accountName1", party.getAccountName());
    assertEquals("accountNumber1", party.getAccountNumber());
    assertEquals("accountNumberCode1", party.getAccountNumberCode());
    assertEquals("address1", party.getAddress());
    assertEquals("bank1", party.getBankId());
    assertEquals("bankIdCode1", party.getBankIdCode());
    assertEquals("name1", party.getName());
  }

  @Test
  public void testToPartyDTO() {
    PartyDTO partyDTO = partyTransformer.toPartyDTO.apply(new Party("accountName", "accountNumber",
        "accountNumberCode","address", "bankId", "bankIdCode", "name"));

    assertEquals("accountName", partyDTO.getAccountName());
    assertEquals("accountNumber", partyDTO.getAccountNumber());
    assertEquals("accountNumberCode", partyDTO.getAccountNumberCode());
    assertEquals("address", partyDTO.getAddress());
    assertEquals("bankId", partyDTO.getBankId());
    assertEquals("bankIdCode", partyDTO.getBankIdCode());
    assertEquals("name", partyDTO.getName());
  }
}
