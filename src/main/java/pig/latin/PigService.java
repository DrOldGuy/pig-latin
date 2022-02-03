/**
 * 
 */
package pig.latin;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

/**
 * This class breaks an array of words into a single phrase with the words separated by spaces. It
 * then breaks the phrase apart into a list of words. This is done to ensure uniform processing of
 * the words. A unit test will pass the words in as a single array element with the words separated
 * by spaces. The command line will break the words into an array with each word as a separate array
 * element. This class should handle both.
 * 
 * @author Promineo
 *
 */
@Service
public class PigService {

  /**
   * @param args
   * @return
   */
  public String translate(String[] args) {
    /* Assemble any array arguments into a single phrase separated by spaces. */
    String phrase = toString(args);

    /*
     * Convert the phrase into a list of PigWord objects. During this phase, each individual word is
     * translated.
     */
    List<PigWord> words = toWords(phrase);

    /*
     * In the Stream below, .map(PigWord::getTranslated) is a method reference that acts as a
     * simplification for the Lambda expression: .map(pigWord -> pigWord.getTranslated()).
     * Basically, the Lambda takes a single parameter, a PigWord object and returns the results of
     * the method call to pigWord.getTranslated(). This returns the translated word.
     */

    // @formatter:off
    return words
        .stream()                           // Create a Stream of PigWord from the List of PigWord
        .map(PigWord::getTranslated)        // Convert to a Stream of String (translated words)
        .collect(Collectors.joining(" "));  // Convert to a space-separated String (translated phrase)
    // @formatter:on
  }

  /**
   * This method converts a String phrase into a list of {@link PigWord}s. It uses a Stream for
   * this.
   * 
   * @param phrase The phrase to convert.
   * @return The list of PigWord.
   */
  private List<PigWord> toWords(String phrase) {
    /*
     * This works as follows: 1) phrase.split("[ ]+") splits the phrase into an array of separate
     * word "chunks" split on one or more spaces. (String.split() takes a regular expression. The [
     * ] means to split on a space and the plus (+) means "one or more". The resulting regular
     * expression means "split the phrase on one or more spaces".) The "chunks" contain apostrophes,
     * punctuation, and uppercase letters. Those are dealt with in the PigWord constructor. 2)
     * Stream.of() converts the chunk array into a Stream of String (the chunks). 3)
     * .map(PigWord::new) is how to call a constructor using a method reference. It is a simplified
     * form of the Lambda expression .map(chunk -> new PigWord(chunk). 4) The .toList() method call
     * turns the Stream of PigWord into a List of PigWord. This is returned to the caller.
     */
    return Stream.of(phrase.split("[ ]+")).map(PigWord::new).toList();
  }

  /**
   * Combine array elements into a single String phrase separated by spaces.
   * 
   * @return The phrase.
   */
  private String toString(String[] args) {
    /*
     * Stream.of() converts the array of String (words to translate) into a Stream of String.
     * .collect(Collectors.joining(" ")) converts the Stream of String (words) into a single String
     * (phrase) with the words separated by spaces. This allows us to take a single array element
     * provided by a test (["Don't give up!] or an array with each word a separate array element as
     * provided by the command line (["Don't", "give", "up!"]) and return both as a phrase
     * "Don't give up!".
     */
    return Stream.of(args).collect(Collectors.joining(" "));
  }

}
