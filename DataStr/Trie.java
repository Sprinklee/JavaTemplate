package DataStr;

class Trie { // 字典树 多用于高校查找字符串前缀等
    //见 https://leetcode.cn/problems/implement-trie-prefix-tree/solutions/2993894/cong-er-cha-shu-dao-er-shi-liu-cha-shu-p-xsj4
    private static class Node {
        Node[] son = new Node[26];
        boolean end;
    }

    private final Node root = new Node();

    public void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (cur.son[c] == null) {
                cur.son[c] = new Node();
            }
            cur = cur.son[c];
        }
        cur.end = true;
    }

    public boolean search(String word) {
        return find(word) == 2;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) != 0;
    }

    private int find(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (cur.son[c] == null) {
                return 0;
            }
            cur = cur.son[c];
        }
        return cur.end ? 2 : 1;
    }
}