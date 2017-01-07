package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int tmp = 0;
        for (int el = 0; el < strings.length; el++) {
            String[] strGroup = strings[el].split(" ");
            for (int innerEl = 0; innerEl < strGroup.length; innerEl++) {
                if (strGroup[innerEl].length() > 2) { //
                    Tuple nw = new Tuple(strGroup[innerEl], strGroup[innerEl].length());
                    tmp += 1;
                    trie.add(nw);
                }
            }
        }
        return tmp;
    }


    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        if (contains(word) == true) {
            trie.delete(word);
            return true;
        }
        return false;
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() >=2) {
            return trie.wordsWithPrefix(pref);
        }

        return null;
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        ArrayList<String> ansList = new ArrayList<>();
        Iterable<String> prefWrds = trie.wordsWithPrefix(pref);


        for (String wrd : prefWrds) {
            if (wrd.length() < pref.length() + k) {
                ansList.add(wrd);
            }
        }
        return ansList;

    }

    public int size() {
        return trie.size();
    }
}
