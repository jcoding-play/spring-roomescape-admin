package roomescape.reservation.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @Test
    @DisplayName("예약자 명을 알 수 있다.")
    void create() {
        Name name = new Name("뽀로로");
        assertThat(name).isEqualTo(new Name("뽀로로"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이름이 공백이면 예외가 발생한다.")
    void invalidName1(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("예약자 이름이 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 5글자를 초과하면 예외가 발생한다.")
    void invalidName2() {
        assertThatThrownBy(() -> new Name("일이삼사오육"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("예약자 이름은 최대 5글자까지 허용합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "123", "!!!"})
    @DisplayName("이름이 한글이 아니라면 예외가 발생한다.")
    void invalidName3(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("예약자 이름은 한글만 가능합니다. 현재 입력된 이름=" + name);
    }
}
