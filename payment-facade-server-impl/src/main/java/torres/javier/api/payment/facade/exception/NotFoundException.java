package torres.javier.api.payment.facade.exception;

public class NotFoundException extends RuntimeException {
    private final String entityType;

    public NotFoundException(String message) {
        super(message);
        entityType = "unknown";
    }

    public NotFoundException(String entityType, String message) {
        super(message);
        this.entityType = entityType;
    }

    public String getEntityType() {
        return entityType;
    }
}
