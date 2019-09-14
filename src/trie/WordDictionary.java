package trie;

/**
 * Created by kpandya on 8/11/19
 Design a data structure that supports the following two operations:
 void addWord(word)
 bool search(word)
 */
public class WordDictionary {

    class TrieNode {
        private TrieNode[] children;
        private boolean isLeaf;

        public TrieNode(){
            this.children = new TrieNode[26];
        }

        public TrieNode get(char c) {
            return this.children[c-'a'];
        }
        public void put(char c, TrieNode node) {
            this.children[c-'a'] = node;
        }
        public boolean containsKey(char c) {
            return this.children[c-'a']!=null;
        }
        public void setLeaf() {
            isLeaf = true;
        }
        public boolean isLeaf() {
            return isLeaf;
        }
    }

    private TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        this.root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode curr = this.root;
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if(!curr.containsKey(ch)) {
                curr.put(ch, new TrieNode());
            }
            curr = curr.get(ch);
        }
        curr.setLeaf();
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     addWord("bad")
     addWord("dad")
     addWord("mad")
     search("pad") -> false
     search("bad") -> true
     search(".ad") -> true
     search("b..") -> true
     */
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }

    private boolean match(char[] chs, int k, TrieNode node) {
        if (k == chs.length) {
            return node.isLeaf;
        }
        if (chs[k] == '.') {
            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null && match(chs, k + 1, node.children[i])) {
                    return true;
                }
            }
        } else {
            return node.children[chs[k] - 'a'] != null && match(chs, k + 1, node.children[chs[k] - 'a']);
        }
        return false;
    }
}
