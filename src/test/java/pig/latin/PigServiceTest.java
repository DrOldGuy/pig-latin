/**
 * 
 */
package pig.latin;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * 
 * This class takes 2 phrases with known Pig Latin translations and verifies that the phrases are
 * translated correctly.
 * 
 * @author Promineo
 *
 */
class PigServiceTest {
  /*
   * We don't need to create a new PigService each time because no state (i.e., instance variables)
   * is saved by the service.
   */
  private PigService pigService = new PigService();

  /**
   * This method tests that a Dr. Seuss quote from <em>Fox in Socks</em> is correctly translated
   * into Pig Latin.
   */
  @Test
  void testSeuss() {
    // Given: original text and expected translation
    String[] original =
        {"Don’t give up! I believe in you all. A person’s a person, " + "no matter how small."};

    String expected = "Ontday ivegay upyay! Iyay elievebay inyay ouyay allyay. Ayay ersonspay "
        + "ayay ersonpay, onay attermay owhay allsmay.";

    // When: the original text is translated
    String actual = pigService.translate(original);

    // Then: the translated text is what is expected
    assertThat(actual).isEqualTo(expected);
  }

  /**
   * This method tests that a quote from J.R.R. Tolkien's <em>The Fellowship of the Ring</em> is
   * translated correctly.
   */
  @Test
  void testTolkien() {
    // Given: original text and expected translation
    String[] original = {"All that is gold does not glitter, not all those who wander are ",
        "lost; the old that is strong does not wither, deep roots are not reached by the frost."};

    String expected = "Allyay atthay isyay oldgay oesday otnay itterglay, otnay allyay "
        + "osethay owhay anderway areyay ostlay; ethay oldyay atthay isyay ongstray oesday "
        + "otnay itherway, eepday ootsray areyay otnay eachedray byay ethay ostfray.";

    // When: the original text is translated
    String actual = pigService.translate(original);

    // Then: the translated text is what is expected
    assertThat(actual).isEqualTo(expected);
  }

}
