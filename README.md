# Pig Latin translator

This project translates an English phrase into Pig Latin. See [here](https://en.wikipedia.org/wiki/Pig_Latin) for a description of Pig Latin.

## Usage

The main method is in pig.Pig.java. Run that class file to perform the translation. The text to be translated is accepted by the main method from the command line. The application works like this:
1.  The command line arguments are collapsed into a single string (text) line.
1.  The text line is then parsed into various collections of letters: word characters (vowels and consonants), spaces, and punctuation.
1.  Single quotes are removed from the input so that words like "I'm" is translated as "imyay" and not "iyay'may". (Double quotes have already been removed by the operating system.)
1.  Words consisting of vowels and consonants are then translated. One or more spaces are collapsed into a single space and punctuation is just copied over to the final output. 

## Translation

* Words that start with vowels are copied as is and "yay" is appended to the end.
* Words that start with one or more consonants have the consonant(s) at the start of the word moved to the end of the word and then "ay" is appended to the word.
* One or more spaces are replaced by a single space.
* Punctuation is copied as is. 
* Capitalization is correctly maintained (i.e., "Don't" becomes "Ontday).

## Testing

```
Original: Don’t give up! I believe in you all. A person’s a person, no matter how small.
Pig Latin: Ontday ivegay upyay! Iyay elievebay inyay ouyay allyay. Ayay ersonspay ayay ersonpay, onay attermay owhay allsmay.

Original: All that is gold does not glitter, not all those who wander are lost; the old that is strong does not wither, deep roots are not reached by the frost.
Pig Latin: Allyay atthay isyay oldgay oesday otnay itterglay, otnay allyay osethay owhay anderway areyay ostlay; ethay oldyay atthay isyay ongstray oesday otnay itherway, eepday ootsray areyay otnay eachedray byay ethay ostfray.
```
