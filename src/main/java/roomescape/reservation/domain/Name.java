package roomescape.reservation.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final int MAXIMUM_LENGTH_OF_NAME = 5;
    private static final Pattern VALID_NAME_PATTERN = Pattern.compile("^[ㄱ-ㅎ가-힣]+$");

    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("예약자 이름이 공백일 수 없습니다.");
        }
        if (name.length() > MAXIMUM_LENGTH_OF_NAME) {
            throw new IllegalArgumentException(String.format("예약자 이름은 최대 %d글자까지 허용합니다.", MAXIMUM_LENGTH_OF_NAME));
        }
        if (isInvalidFormat(name)) {
            throw new IllegalArgumentException(String.format("예약자 이름은 한글만 가능합니다. 현재 입력된 이름=%s", name));
        }
    }

    private boolean isInvalidFormat(String name) {
        return !VALID_NAME_PATTERN.matcher(name)
                .matches();
    }

    public String getValue() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
