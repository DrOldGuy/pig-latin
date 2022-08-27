package pig;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PigOut {
  private List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');
  
  public static void main(String[] args) {
    new PigOut().run(args);
  }

  private void run(String[] args) {
    String phrase = convertArgsToString(args);
    translatePhrase(phrase);
  }

  String translatePhrase(String phrase) {
    // @formatter:off
    return Stream.of(phrase.split(" "))
        .map(word -> word.replaceAll("[’']", ""))
        .map(this::translateWord)
        .collect(Collectors.joining(" "));
    // @formatter:on
  }
  
  private String translateWord(String word) {
    boolean isUpper = false;
    char first = word.charAt(0);
    char last = word.charAt(word.length() - 1);
    String punc = null;
    
    if(Character.isUpperCase(first)) {
      isUpper = true;
      first = Character.toLowerCase(first);
    }
    
    if(!Character.isAlphabetic(last) && !Character.isDigit(last)) {
      punc = word.substring(word.length() - 1, word.length());
      word = word.substring(0, word.length() - 1);
    }
    
    word = word.toLowerCase();
    
    if(vowels.contains(first)) {
      word += "yay";
    }
    else {
      word = translateWordWithConsonant(word);
    }
    
    if(isUpper) {
      word = word.substring(0, 1).toUpperCase() + word.substring(1);
    }
    
    if(Objects.nonNull(punc)) {
      word += punc;
    }

    return word;
  }

  private String translateWordWithConsonant(String word) {
    int pos = 0;
    
    while(pos++ < word.length()) {
      char ch = word.charAt(0);
      
      if(vowels.contains(ch)) {
        break;
      }
      
      word = word.substring(1) + Character.toString(ch);
    }
    
    return word + "ay";
  }

  private String convertArgsToString(String[] args) {
    if(args.length == 1) {
      args = args[0].split(" ");
    }
    
    return Stream.of(args).collect(Collectors.joining(" "));
  }
}
