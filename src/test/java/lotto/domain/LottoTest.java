package lotto.domain;

import lotto.dto.LottoNumbersDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호가_허용범위를_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 47)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호가_최소허용_범위보다_작으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호에_해당번호가_있으면_true를_반환한다() {
        assertTrue(new Lotto(List.of(1, 2, 3, 4, 5, 6)).contains(1));
    }

    @Test
    void 로또_번호에_해당번호가_없으면_false를_반환한다() {
        assertFalse(new Lotto(List.of(1, 2, 3, 4, 5, 6)).contains(10));
    }

    @Test
    void toDTO를_통해_LottoNumbersDTO를_생성한다() {
        assertEquals(new Lotto(List.of(1, 2, 3, 4, 5, 6)).toDTO()
                , new LottoNumbersDTO(List.of("1", "2", "3", "4", "5", "6")));
    }

    @Test
    void getNumbers를_통해_로또번호를_반환한다() {
        assertEquals(new Lotto(List.of(1, 2, 3, 4, 5, 6)).getNumbers()
                , List.of(1, 2, 3, 4, 5, 6));
    }
}