public class TrieNode {

	private TrieNode [] trieNodes;
	private boolean end;
	private int words;
	private int prefix;


	public TrieNode(){
		trieNodes= new TrieNode[26];
		words=0;
		prefix=0;
		end=false;
	}
 		
 	public void printWords(char[]words,int level){
 		for(int i=0;i<level;i++){
 			System.out.print(words[i]);
 		}
 		System.out.println("");
 	}

 	public void printWords(TrieNode t,char prefix[],int level){

 		 if(t==null){
 		 	System.out.println("t is null");
 		 	return;
 		 }
 		 
 		 if(t.words>=1){
 		 	
 		 	printWords(prefix,level);
 		 	
 		 }else{
 		 for(int i=0;i<26;i++){
 		 	if(t.trieNodes[i]!=null){
  		 		prefix[level]=(char)(i+97);
 		 		printWords(t.trieNodes[i],prefix,level+1);

 		 		}
 		 	}
 		 }
 	}

	 
	public void getAllWordsStartingWithPrefix(TrieNode t, String word){

		char[] prefix = new char[50];
		getAllWordsStartingWithPrefix(t,word,prefix,0);
	}

	public void getAllWordsStartingWithPrefix(TrieNode t, String word,char[]prefix,int level){
		 if(word.length()==0){
		 	 
		 	printWords(t,prefix,level);

		 }else{

		 		int v = word.charAt(0)-97;
		 		prefix[level]=word.charAt(0);
		 		 if(t.trieNodes[v]!=null){
		 		 	TrieNode w = t.trieNodes[v];
		 		 	getAllWordsStartingWithPrefix(w,word.substring(1),prefix,level+1);
		  		 }
		 	}
	}
 		
	public int countWords(TrieNode t, String word){
		if(word.length()==0){
			return t.words;
		}
		int v = word.charAt(0)-97;
		if(t.trieNodes[v]==null){
			return 0;
		}else{
			TrieNode n = t.trieNodes[v];
			return countWords(n,word.substring(1));
		}
	}

	public int countPrefix(TrieNode t, String word){
		if(word.length()==0){
			return t.prefix;
		}
		int v = word.charAt(0)-97;
		if(t.trieNodes[v]==null){
			return 0;
		}else{
		TrieNode n = t.trieNodes[v];
		return countPrefix(n,word.substring(1));	
		}
	}

 	public void addWord(TrieNode t, String word){
 		if(word.length()==0){
 		 
 			t.words+=1;
 		}
 		
 		if(word.length()>=1){
 			int v = word.charAt(0)-97;
 			 
 			if(t.trieNodes[v]==null){
 				TrieNode n =new TrieNode();
 				t.trieNodes[v]=n;
 				t.prefix+=1;
 				addWord(n,word.substring(1));
 			}else{
 				t.prefix+=1;
 				TrieNode n =t.trieNodes[v];
 				addWord(n,word.substring(1));
 			}
 		}
 	}

 }