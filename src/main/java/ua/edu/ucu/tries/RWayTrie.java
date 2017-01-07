package ua.edu.ucu.tries;

import java.util.ArrayList;
import java.util.Objects;

public class RWayTrie implements Trie {

    private static final int R = 0;
    private Node root;
    private int n;

    public RWayTrie() {
        root = new Node(null);
    }

    //private class which represents node of the tree
    private static class Node {
        protected Character value;
        private Node[] next = new Node[R];
        private int weight;


        Node(Character value) {
            this.value = value;
        }

        Node addL(char l, int size) {
            for (int el=0; el<next.length; el++) {
                System.out.println(next[el].value == l);
                if (Objects.equals(next[el].value, l)) {
                    if (next[el].weight != 0) {
                        return next[el];
                    }
                    else {
                        next[el].weight = size;
                        return next[el];
                    }
                }
            }

            Node newNode = new Node(l);
            newNode.weight = size;
            // rewriting an array
            Node[] temp = new Node[next.length+1];
            for (int elem = 0; elem<next.length; elem++) {
                temp[elem] = next[elem];
            }
            temp[next.length] = newNode;
            next = temp;
            //end of rewriting


            return newNode;
        }



        void prefMatches(Node node, String wrd, String pref, ArrayList arrLst) {
            if (wrd.startsWith(pref)&&node.weight != 0) {
                arrLst.add(wrd);
                System.out.println(wrd);
            }

            for (int elem = 0; elem < node.next.length; elem++) {
                if (node.next[elem] != null) {
                    prefMatches(node.next[elem], wrd + node.next[elem].value, pref, arrLst);
                }

            }
            System.out.println("fin");
        }


        void matches(Node node, String wrd, ArrayList wordArray) {
            if (node.weight > 0) {
                // rewriting an array
                // String[] temp = new String[next.length+1];
                //end of rewriting
                wordArray.add(wrd);


            }

            for (int c=0; c< node.next.length; c++) {
                matches(next[c], wrd + next[c].value, wordArray);
            }
        }



    }
    // end of private class


    /*
*/

    @Override
    public void add(Tuple word) {
        n += 1;
        root.weight = 0;
        Node tmp = root;

        for(int elem = 0; elem< word.weight; elem++) {
            if (elem + 1 == word.weight) {
                tmp = tmp.addL(word.term.charAt(elem), word.weight);
            }
            else {
                tmp = tmp.addL(word.term.charAt(elem), 0);
            }
        }
    }

    @Override
    public boolean contains(String word) {
        Node tmp = root;
        int pointer = 0;
        for (int el = 0; el < word.length(); el++) {
            for (int elem = 0; elem < tmp.next.length; elem++) {
                if (tmp.next[elem].value.equals(word.charAt(el))) {
                    tmp = tmp.next[elem];
                    pointer += 1;
                    break;
                }
            }
        }
        boolean statement = (pointer == word.length());
        return statement;
    }

    @Override
    public void delete(String word) {
        if (contains(word)) {
            int pointer = 0;
            Node tmp = root;
            while (true) {
                for (int point=0; point<word.length(); point++) {
                    for (int el = 0; el < tmp.next.length; el++) {
                        if (tmp.next[el].value == word.charAt(point)) {
                            if (pointer + 1 == word.length()) {
                                tmp.weight = 0;
                                System.out.println("WORD DELETED");
                                break;
                            }
                            tmp = tmp.next[el];
                            pointer += 1; // !!!
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public Iterable<String> words() {
        ArrayList<String> wrd = new ArrayList<>();
        root.matches(root, "", wrd);

        return wrd;
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        // wrds = new String[n];
        ArrayList<String> wrd = new ArrayList<>();

        root.prefMatches(root, "", s, wrd);

        return wrd;
    }


    @Override
    public int size() {
        return n;
    }

}