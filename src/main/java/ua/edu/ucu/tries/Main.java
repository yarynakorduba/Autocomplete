package ua.edu.ucu.tries;

import ua.edu.ucu.autocomplete.PrefixMatches;

/**
 * Created by cs.ucu.edu.ua on 1/5/2017.
 */
public class Main {
    public static void main(String[] args) {
        RWayTrie a = new RWayTrie();
        PrefixMatches b = new PrefixMatches(a);
        b.load("sld", "ldkfg", "aalgh", "slda");
        System.out.println("THE ANSWER: ");
        System.out.println(b.wordsWithPrefix("sl"));


    }
}
