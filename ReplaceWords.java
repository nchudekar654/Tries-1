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
    
    public String replaceWords(List<String> dictionary, String sentence) {
        StringBuilder result = new StringBuilder();
        for(String word: dictionary){
            insert(word);
        }
        String [] strArr = sentence.split(" ");
        for(String word:strArr){
            //sentence for replacement
            StringBuilder replacement = new StringBuilder();
            TrieNode curr = root;
            for(int i=0; i< word.length(); i++){
                char ch = word.charAt(i);
                if(curr.children[ch - 'a'] == null || curr.isEnd){
                    break;
                }
                replacement.append(ch);
                curr = curr.children[ch - 'a'];
            }
            if(curr.isEnd){ //found the replacement
                result.append(replacement.toString());
            }else{
                result.append(word);
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
} 