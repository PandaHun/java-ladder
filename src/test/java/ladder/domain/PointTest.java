package ladder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    @DisplayName("Point 첫번째 좌표값 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    void getFirst(boolean right) {
        Point point = Point.getFirst(right);

        assertThat(point).isNotNull();
        assertThat(point.isLeft()).isFalse();
        assertThat(point.isRight()).isEqualTo(right);
    }

    @DisplayName("Point 마지막 좌표값 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    void getLast(boolean left) {
        Point point = Point.getLast(3, left);

        assertThat(point).isNotNull();
        assertThat(point.isLeft()).isEqualTo(left);
        assertThat(point.isRight()).isFalse();
    }

    @DisplayName("이전 Point 이용해 다음 Point 생성 테스트")
    @ParameterizedTest
    @MethodSource("makePointNextData")
    void next(boolean previousLeft, boolean previousRight, boolean right, boolean expectRight) {
        Point previous = Point.of(1, previousLeft, previousRight);

        Point actual = previous.next(right);

        assertThat(actual).isNotNull();
        assertThat(actual.isLeft()).isEqualTo(previousRight);
        assertThat(actual.isRight()).isEqualTo(expectRight);
    }

    private static Stream<Arguments> makePointNextData() {
        return Stream.of(
                Arguments.of(true, false, false, false),
                Arguments.of(false, true, true, false),
                Arguments.of(false, true, true, false),
                Arguments.of(false, false, false, false)
        );
    }

    @DisplayName("Point move - 다음 Point의 index 반환 테스트")
    @ParameterizedTest
    @CsvSource(value = {"30:true:false:29", "0:false:true:1", "7:false:false:7"}, delimiter = ':')
    void move(int startIndex, boolean left, boolean right, int expectedIndex) {
        Point point = Point.of(startIndex, left, right);
        int actualIndex = point.move();

        assertThat(actualIndex).isEqualTo(expectedIndex);
    }
}