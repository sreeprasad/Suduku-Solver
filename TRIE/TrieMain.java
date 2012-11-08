import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
public class TrieMain{


	public static void main(String abc[]){
		TrieNode t = new TrieNode();
		try{

			FileInputStream io = new FileInputStream("dictionary.txt");
			BufferedReader buf = new BufferedReader(new InputStreamReader(io));
			String s;
			while((s=buf.readLine())!=null){
				t.addWord(t,s);
			}
		System.out.println("---------------");
		
			t.getAllWordsStartingWithPrefix(t,"sr");
		}catch(FileNotFoundException e){
			System.out.println(e.toString());
		}catch(IOException e){
			System.out.println(e.toString());
		}

	}
}