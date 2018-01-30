package torres.javier.api.payment.facade.transformer;

import org.springframework.stereotype.Component;
import torres.javier.api.payment.facade.model.PartyDTO;
import torres.javier.api.payment.facade.model.Party;

import java.util.function.Function;

@Component
public class PartyTransformer {
  public Function<PartyDTO, Party> fromPartyDTO = partyDTO ->
    new Party(partyDTO.getAccountName(),
        partyDTO.getAccountNumber(),
        partyDTO.getAccountNumberCode(),
        partyDTO.getAddress(),
        partyDTO.getBankId(),
        partyDTO.getBankIdCode(),
        partyDTO.getName());

  public Function<Party, PartyDTO> toPartyDTO = party ->
      new PartyDTO()
          .accountName(party.getAccountName())
          .accountNumber(party.getAccountNumber())
          .accountNumberCode(party.getAccountNumberCode())
          .address(party.getAddress())
          .bankId(party.getBankId())
          .bankIdCode(party.getBankIdCode())
          .name(party.getName());
}
