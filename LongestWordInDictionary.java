//TC: Build the Trie: O(n) , here n is number of characters inserted into Trie; DFS Traversal: O(n) onyl if isEnd == true
//SC: O(n + l_max), L_max for DFS recursion and result storage
//TrieNode using DFS and  backtracking

class Solution {
    class TrieNode{
        boolean isEnd;
        TrieNode[] children;
        public TrieNode(){
            this.children = new TrieNode[26]; // because only lower case characters
        }
    }
    private TrieNode root;
    public Solution() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;  //curr is used for traversing down the trie path
        for(int i=0 ; i< word.length(); i++){
            char ch = word.charAt(i);
            //check if current trienode has child ch
            if(curr.children[ch -'a'] == null ){
                curr.children[ch -'a'] = new TrieNode();
            }
           curr = curr.children[ch-'a'];

        }
        curr.isEnd = true;
    }
    String maxString;
    
    public String longestWord(String[] words) {
        TrieNode curr = root;
        curr.isEnd = true;
        this.maxString ="";
        for(String word : words){
            insert(word);
        }
        StringBuilder path = new StringBuilder();
        helper(curr, path);
        return maxString;
        
    }
    public void helper(TrieNode curr, StringBuilder path){
        //base 
        if(!curr.isEnd)
            return;
        if(path.length() > maxString.length())
        {
            maxString = path.toString();
        }
    
        //logic
      
        for(int i=0; i< 26; i++){
            if(curr.children[i] != null){
                //action
                int len = path.length();
                path.append((char)('a'+ i));
                //recurse
                helper(curr.children[i], path);
                //backtrack
                path.setLength(len);
            }
        }
    }
}

//TrieNode using BFS
//TC: O(n) , total number of characters inserted and traversed
//SC: O(n)

class Solution {
    class TrieNode{
        boolean isEnd;
        TrieNode[] children;
        public TrieNode(){
            this.children = new TrieNode[26]; // because only lower case characters
        }
    }
    private TrieNode root;
    public Solution() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;  //curr is used for traversing down the trie path
        for(int i=0 ; i< word.length(); i++){
            char ch = word.charAt(i);
            //check if current trienode has child ch
            if(curr.children[ch -'a'] == null ){
                curr.children[ch -'a'] = new TrieNode();
            }
           curr = curr.children[ch-'a'];

        }
        curr.isEnd = true;
    }

    public String longestWord(String[] words) {
        for(String word : words){
            insert(word);
        }
        Queue<TrieNode> q = new LinkedList<>();
        Queue<String> sq = new LinkedList<>();
        q.add(root);
        sq.add("");
        String currStr = "";
        while(!q.isEmpty()){
            TrieNode curr = q.poll();
            currStr = sq.poll();
            for(int i=25; i >=0; i--){
                if(curr.children[i] != null && curr.children[i].isEnd){
                    q.add(curr.children[i]);
                    sq.add(currStr + (char)('a'+i));
                }
            }
        }
        return currStr;
    }
}