/**
 * 
 */
package pig;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pig.latin.PigService;

/**
 * This class is the entry point for a standalone Spring Boot application. It takes an array of
 * words from the command line and translates them to Pig Latin. The {@link CommandLineRunner}
 * interface defines a single {@link #run(String...)} method, which Spring Boot calls after
 * performing the application initialization (component scan, auto-initialization, etc.).
 * 
 * @author Promineo
 *
 */
@SpringBootApplication
public class Pig implements CommandLineRunner {

  @Autowired
  private PigService pigLatinService;

  /**
   * The main method starts Spring Boot. Since Spring Boot Web is not present on the classpath,
   * Spring Boot starts a standalone application.
   * 
   * @param args The command-line arguments.
   */
  public static void main(String[] args) {
    SpringApplication.run(Pig.class, args);
  }

  /**
   * This method is called by Spring Boot after it initializes the application. Dependency Injection
   * has occurred by this point so we can access any injected instance variables.
   * 
   * @param args The command-line arguments.
   */
  @Override
  public void run(String... args) {
    String actual = pigLatinService.translate(args);
    String orig = Stream.of(args).collect(Collectors.joining(" "));

    System.out.println("      Orig: " + orig);
    System.out.println("Translated: " + actual);
  }

}
