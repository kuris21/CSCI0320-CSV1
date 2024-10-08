package edu.brown.cs.student.main;

import ObjectCreators.TrivialCreator;
import Parser.Parser;
import Searcher.CSVSearcher;
import java.io.FileReader;
import java.util.List;

/**
 * The Main class of our project. This is where execution begins. Note: For this first sprint, you
 * will not be running the parser through main(), but rather interacting with the parser through
 * extensive testing!
 */
public final class Main {

  /**
   * The main method is the entry point of the application.
   *
   * @param args command-line arguments passed to the program
   */
  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println(
          "Usage: java Main <CSV file> <search value> [<column identifier>] [<hasHeader>]");
      return;
    }

    String filename = args[0];
    String searchValue = args[1];
    String columnIdentifier = args.length > 2 ? args[2] : null;
    boolean hasHeader = args.length > 3 && args[3].equalsIgnoreCase("true");

    try {
      // Initialize the parser with the CSV file and TrivialCreator
      FileReader reader = new FileReader(filename);
      Parser<List<String>> parser = new Parser<>(reader, new TrivialCreator());

      // Pass the parser to CSVSearcher
      CSVSearcher searcher = new CSVSearcher(parser);

      searcher.search(filename, searchValue, columnIdentifier, hasHeader);
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
