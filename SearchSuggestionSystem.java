//TimeComplexity: 
// sorting - O(n log n) + insertion - O(n*m) + search word per character - O(L)

class Solution {
    class TrieNode{
        TrieNode[] children;
        List<String> suggestions;
        boolean isEnd;

        public TrieNode(){
            this.children = new TrieNode[26];
            this.suggestions = new ArrayList<>();
            this.isEnd = false;
        }

    }

    private TrieNode root = new TrieNode();

    private void insert(String word){
        TrieNode curr = root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            if(curr.children[ch-'a']== null){
                curr.children[ch-'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
            if(curr.suggestions.size() < 3){
                curr.suggestions.add(word);
            }
        }
        curr.isEnd = true;
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);

        for(String product : products){
            insert(product);
        }

        List<List<String>> result = new ArrayList<>();
        TrieNode curr = root;
        for(int i=0; i<searchWord.length(); i++){
            char ch = searchWord.charAt(i);
            if(curr!= null){
                curr = curr.children[ch-'a'];
            }

            result.add(curr == null ? new ArrayList<>() : curr.suggestions);
        }
        return result;
    }
}