package pig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PigOutTest {

  private PigOut pigOut = new PigOut();

  @ParameterizedTest
  @MethodSource("pig.PigOutTest#argumentsForTranslate")
  void testPigLatinTranslation(String phrase, String pigged) {
    String actual = pigOut.translatePhrase(phrase);
    assertThat(actual).isEqualTo(pigged);
  }

  static Stream<Arguments> argumentsForTranslate() {
    // @formatter:off
    return Stream.of(
        arguments(
            "Don’t give up! I believe in you all. A person’s a person, no matter how small.",
            "Ontday ivegay upyay! Iyay elievebay inyay ouyay allyay. Ayay ersonspay ayay ersonpay, "
            + "onay attermay owhay allsmay."
        ),
        arguments(
            "All that is gold does not glitter, not all those who wander are lost; the old that is "
            + "strong does not wither, deep roots are not reached by the frost.", 
            "Allyay atthay isyay oldgay oesday otnay itterglay, otnay allyay osethay owhay anderway "
            + "areyay ostlay; ethay oldyay atthay isyay ongstray oesday otnay itherway, eepday "
            + "ootsray areyay otnay eachedray byay ethay ostfray."
        )
    );
    // @formatter:of
  }
}
