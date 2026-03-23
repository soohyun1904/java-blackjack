package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @Test
    void 이름이_정상_생성된다() {
        Name name = new Name("홍길동");
        assertThat(name.name()).isEqualTo("홍길동");
    }

    @Test
    void 이름의_길이가_10_초과이면_예외가_터진다(){
        assertThatThrownBy(() -> new Name("일이삼사오육칠팔구십일"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름의 길이는 1이상 10이하여야 합니다.");
    }

    @Test
    void 이름의_길이가_1_미만이면_예외가_터진다(){
        assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름의 길이는 1이상 10이하여야 합니다.");
    }
}