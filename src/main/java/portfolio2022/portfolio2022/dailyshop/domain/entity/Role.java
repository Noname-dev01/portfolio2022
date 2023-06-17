package portfolio2022.portfolio2022.dailyshop.domain.entity;

public enum Role {

    USER("USER","ROLE_USER"),
    ADMIN("ADMIN","ROLE_ADMIN");

    private String key;
    private String value;

    Role(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
