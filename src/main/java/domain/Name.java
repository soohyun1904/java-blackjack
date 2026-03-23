package domain;

public class Name {
    private static final int MAX_LENGTH = 10;

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.isEmpty() || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름의 길이는 1이상 10이하여야 합니다.");
        }
    }

    public String name() {
        return name;
    }
}
