package torres.javier.api.payment.facade.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import java.util.stream.Stream;

public class Payment {
  @Id
  private final String id;
  private final Type type;
  private final String organisationId;
  private final Long version;
  private final Attributes attributes;

  public Payment(String id, Type type, String organisationId, Long version, Attributes attributes) {
    this.id = id;
    this.type = type;
    this.organisationId = organisationId;
    this.version = version;
    this.attributes = attributes;
  }

  public String getId() {
    return id;
  }

  public Type getType() {
    return type;
  }

  public String getOrganisationId() {
    return organisationId;
  }

  public Long getVersion() {
    return version;
  }

  public Attributes getAttributes() {
    return attributes;
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public static class Builder {
    public String id;
    public Type type;
    public String organisationId;
    private Long version;
    private Attributes attributes;

    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    public Builder withType(Type type) {
      this.type = type;
      return this;
    }

    public Builder withOrganisationId(String organisationId) {
      this.organisationId = organisationId;
      return this;
    }

    public Builder withVersion(Long version) {
      this.version = version;
      return this;
    }

    public Builder withAttributes(Attributes attributes) {
      this.attributes = attributes;
      return this;
    }

    public Payment build() {
      return new Payment(id, type, organisationId, version, attributes);
    }
  }

  public enum Type {
    PAYMENT("Payment"), Refund("Refund");

    private final String value;

    public static Type fromValue(String val) {
      return Stream.of(Type.values())
          .filter(t -> t.getValue().equals(val))
          .findAny()
          .get();
    }

    Type(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

}
