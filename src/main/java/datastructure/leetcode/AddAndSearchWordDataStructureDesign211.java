package datastructure.leetcode;

import java.util.TreeMap;

/**
 * @author: Alex
 * @date: 2018/12/13 17:55
 * description: leetcode-211. 【添加与搜索单词 - 数据结构设计】
设计一个支持以下两种操作的数据结构：
void addWord(word)
bool search(word)
search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
示例:
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
说明:
你可以假设所有单词都是由小写字母 a-z 组成的。
 */
public class AddAndSearchWordDataStructureDesign211{

    private class Node{

        boolean isWord;
        TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    /** Initialize your data structure here. */
    public AddAndSearchWordDataStructureDesign211() {
        root = new Node();
        size = 0;
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if(word == null)
            return;
        Node cur = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c,new Node());
            cur = cur.next.get(c);
        }
        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root,word,0);
    }

    private boolean match(Node node, String word, int index) {
        if(word == null)
            return false;

        if(index == word.length()){
            return node.isWord;
        }
        Node cur = node;
        char c = word.charAt(index);
        if(c !=  '.'){
            if(cur.next.get(c) != null){
                return match(cur.next.get(c),word,index + 1);
            }else
                return false;
        }else{
            for(Character n : cur.next.keySet()){
                if(match(cur.next.get(n),word,index + 1)){
                    return true;
                }
            }
            return false;
        }

    }

    public static void main(String[] args) {
        AddAndSearchWordDataStructureDesign211 search = new AddAndSearchWordDataStructureDesign211();
        search.addWord("at");
        search.addWord("and");
        search.addWord("an");
        search.addWord("add");
        //boolean a1 = search.search("a");
        //boolean a2 = search.search(".at");
        search.addWord("bat");
        boolean a3 = search.search("a.d");
        //System.out.println("a = " + a1);
        //System.out.println("a = " + a2);
        System.out.println("a = " + a3);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
