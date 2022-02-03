/**
 * 
 */
package pig.latin;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import pig.latin.PigWord.WordType;

/**
 * The {@link PigWord} constructor performs some pretty complex operations. This unit test ensures
 * that the constructor translates the word correctly in all cases.
 * 
 * @author Promineo
 *
 */
class PigWordTest {

  /**
   * This test checks that a word that starts with a vowel "all" is correctly translated to
   * "allyay".
   */
  @Test
  void assertThatWordsThatStartWithAVowelAreTranslatedCorrectly() {
    PigWord word = new PigWord("all");

    assertThat(word.getType()).isEqualTo(WordType.VOWELL);
    assertThat(word.getWord()).isEqualTo("all");
    assertThat(word.getTranslated()).isEqualTo("allyay");
  }

  /**
   * This test checks that a word that starts with a consonant "small" is correctly translated to
   * "allsmay".
   */
  @Test
  void assertThatWordsThatStartWithAConsonantAreTranslatedCorrectly() {
    PigWord word = new PigWord("small");

    assertThat(word.getType()).isEqualTo(WordType.CONSONANT);
    assertThat(word.getWord()).isEqualTo("small");
    assertThat(word.getTranslated()).isEqualTo("allsmay");
  }

  /**
   * This test checks that a word that ends with a punctuation character ("up!") is correctly
   * translated to "upyay!".
   */
  @Test
  void assertThatWordsThatEndWithPunctuationAreTranslatedCorrectly() {
    PigWord word = new PigWord("up!");

    assertThat(word.getType()).isEqualTo(WordType.VOWELL);
    assertThat(word.getWord()).isEqualTo("up!");
    assertThat(word.getTranslated()).isEqualTo("upyay!");
  }

  /**
   * This checks that words starting with a capital letter are translated correctly. It also checks
   * that single quotes (apostrophes) are removed. So, "Don't" becomes "Ontday" and not "ontDay" or
   * "on'tday".
   */
  @Test
  void assertThatCapitalizedWordIsTranslatedCorrectly() {
    PigWord word = new PigWord("Don't");

    assertThat(word.getType()).isEqualTo(WordType.CONSONANT);
    assertThat(word.getWord()).isEqualTo("Dont");
    assertThat(word.getTranslated()).isEqualTo("Ontday");
  }
}
