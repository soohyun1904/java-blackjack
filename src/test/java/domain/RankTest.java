package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    
    @Test
    void 에이스는_isAce가_참이다(){
        Rank ace = Rank.ACE;
        boolean result = ace.isAce();
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, names = {"JACK", "QUEEN", "KING"})
    void 페이스_카드의_값은_10이다(Rank rank) {
        assertThat(rank.getValue()).isEqualTo(10);
    }

    @Test
    void 에이스의_기본_값은_11이다(){
        assertThat(Rank.ACE.getValue()).isEqualTo(11);
    }
}