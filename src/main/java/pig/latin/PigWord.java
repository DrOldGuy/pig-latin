/**
 * 
 */
package pig.latin;

import java.util.List;
import java.util.Objects;
import lombok.Getter;

/**
 * This class accepts a word (anything between spaces - may contain punctuation) and performs the
 * translation. Pig Latin is a word-based translation, so there are no inter-word dependencies. It
 * makes sense, then, to do the translation in the constructor. To translate:
 * <ul>
 * <li>If the word starts with a vowel, add "yay" onto the end of the word.</li>
 * <li>If the word starts with a consonant, move the consonants to the end of the word and add
 * "ay"</li>
 * <li>Single quotes must be removed first as they should not appear in the translated word.</li>
 * <li>Any trailing punctuation should be removed before the translation and added again afterwards
 * (i.e., "can!" becomes "ancay!").</li>
 * <li>A capitalized word should be reflected in the translation, even though the capitalized
 * character may move in the word. So, "Small" becomes "Allsmay".
 * <ul>
 * 
 * @author Promineo
 *
 */
public class PigWord {
  /* The original word */
  @Getter
  private String word;

  /* The word type - starts with a consonant or starts with a vowel. */
  @Getter
  private WordType type;

  /* The word translated into Pig Latin with corrected punctuation and capitalization. */
  @Getter
  private String translated;

  public enum WordType {
    CONSONANT, VOWELL
  }

  /* The list of vowels. */
  private List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');

  /**
   * Creates a new PigWord object. The word type and translation are calculated from the given word.
   * 
   * @param word The word to translate.
   */
  public PigWord(String word) {
    if (Objects.isNull(word) || word.isEmpty()) {
      throw new IllegalArgumentException("Word must not be blank or null.");
    }

    /*
     * The regular expression removes all normal single quotes and Microsoft Word quotes. The
     * character group ['’] says to match on every ' and ’ and replace them with nothing (i.e.,
     * remove them).
     */
    this.word = word.replaceAll("['’]", "");
    this.type = findWordType(this.word);
    this.translated = translate(this.word);
  }

  /**
   * This determines the word type based on whether the first letter is found in the {@link #vowels}
   * list. It returns {@link WordType#VOWELL} if the word starts with a vowel and
   * {@link WordType#CONSONANT} if the word starts with a consonant.
   * 
   * @param word The word to identify.
   */
  private WordType findWordType(String word) {
    char ch = word.charAt(0);

    /*
     * We must convert the character to lower case because the list of vowels is all lower case. We
     * use the Character wrapper class for this.
     */
    return vowels.contains(Character.toLowerCase(ch)) ? WordType.VOWELL : WordType.CONSONANT;
  }

  /**
   * Translate the word to Pig Latin. The word may contain punctuation in the last character and it
   * may start with a capital letter. We must account for both.
   * 
   * @param wordToTranslate The word to translate.
   * @return The translated word.
   */
  private String translate(String wordToTranslate) {
    /*
     * If the word starts with an upper case letter, save this information so that we can restore it
     * at the end.
     */
    boolean isUpper = Character.isUpperCase(wordToTranslate.charAt(0));

    /*
     * Convert the word to lower case so that any capital letter won't move to the middle of the
     * word.
     */
    String xlat = wordToTranslate.toLowerCase();
    String punc = null;

    /* Store the last character so that we can determine if it is punctuation. */
    char lastChar = xlat.charAt(xlat.length() - 1);

    /*
     * If the last character isn't alphabetic, it must be punctuation. If so, store it in the
     * variable punc and remove the last character.
     */
    if (!Character.isAlphabetic(lastChar)) {
      punc = Character.toString(lastChar);
      xlat = xlat.substring(0, xlat.length() - 1);
    }

    /* The word type was determined in the constructor and saved in the type instance variable. */
    switch (type) {
      case CONSONANT:
        xlat = translateConsonant(xlat);
        break;

      case VOWELL:
        xlat += "yay";
        break;

      default:
        throw new IllegalStateException("Unknown WordType: " + type);
    }

    /* If we found punctuation earlier, add it back onto the end of the word. */
    if (Objects.nonNull(punc)) {
      xlat += punc;
    }

    /*
     * If the word started with a capital letter, capitalize the first letter of the translated
     * word.
     */
    if (isUpper) {
      xlat = xlat.substring(0, 1).toUpperCase() + xlat.substring(1);
    }

    return xlat;
  }

  /**
   * This method translates a word that starts with a consonant. All the consonants at the start of
   * the word are moved to the end of the word. The word is in lower case at this point and has had
   * any punctuation removed.
   * 
   * @return The translated word.
   */
  private String translateConsonant(String orig) {
    /*
     * We will loop through the word one character at a time looking for the first vowel. The
     * variable vowelPos will contain the vowel position. So, the loop will find the vowel position
     * of "small" at position 2.
     */
    int vowelPos = 0;

    while (vowelPos < orig.length()) {
      /* If a vowel is found break out of the loop. */
      if (vowels.contains(orig.charAt(vowelPos))) {
        break;
      }

      ++vowelPos;
    }

    String xlat;

    /*
     * It is very unlikely that a word will not contain any vowels but check for that anyway. This
     * is indicated by vowelPos equal to the word length. In this unlikely event, set the translated
     * word to the original. Otherwise, move the characters after and including the vowel to the
     * start of the translated word and the characters at the start of the word up to but not
     * including the vowel to the end of the translated word.
     */
    if (vowelPos < orig.length()) {
      xlat = orig.substring(vowelPos) + orig.substring(0, vowelPos);
    } else {
      xlat = orig;
    }

    /* Add "ay" to the end of the word following the rules for Pig Latin. */
    return xlat + "ay";
  }

  /**
   * Create a toString method that returns the original word and the translated word.
   */
  @Override
  public String toString() {
    return "PigWord [word=" + word + ", type=" + type + ", translated=" + translated + "]";
  }
}
