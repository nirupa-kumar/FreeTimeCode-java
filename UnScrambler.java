import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//Assumptions
//A dictionary of English words should be available
//User will use provide only word with letters as input


//Pseudocode

//English dictionary text file will be available 
//on running the program the use will be asked for the path to the dictionary file

//A hash map will be created as follows
 /*for each word in the dictionary
 	sort the word (in alphabetical order)
 	if sorted string is a key in the hash map
 		add the unsorted word to the value (which is an ArrayList of words)
 	else
 		create a new entry in the hash map with key = sorted string and 
 		add the unsorted word to the value array-list
 	endif
 endfor	
 
 An example hasmap entry is shown below:
 //HashMap containing the letters in alphabetical order for the key and the word
	//Example
	// _____________________________________
	//| Key | Value                         |
	//|eilv | veil , live, levi, evil, vile |
	//
 
//User will enter a word to be unscrambled
//The entered word will be converted to all lower case and then sorted 
 * Use the sorted string as key on the hashmap to find the list of words that can be formed
 * Print this list
*/

public class UnScrambler {
	// path for the dictionary file
	private String dictionaryFile;
	
	private HashMap<String, ArrayList<String>> mapwords;

	public UnScrambler(String dictionary) {
		mapwords = new HashMap<String, ArrayList<String>>();
		dictionaryFile = dictionary;
		
	}

	public ArrayList<String> getWords(String location) throws IOException {
		File file = new File(location);
		ArrayList<String> words = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), "UTF-8"));
		int j = 0;
		while (br.ready())

		{
			words.add(br.readLine());
			j++;

		}
		br.close();
		return words;
	}

	public void PreprocessDictionary() throws IOException {
		System.out.println("...Pre-processing dictionary...building hashmap");
		ArrayList<String> words = null;
		try {
			words = getWords(dictionaryFile);

			for (int i = 0; i < words.size(); i++) {
				String unsorted = words.get(i);
				char[] str = unsorted.toCharArray();
				Arrays.sort(str);
				String sorted = new String(str);

				ArrayList<String> tempList = mapwords.get(sorted);
				if (tempList == null) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(unsorted);
					mapwords.put(sorted, list);
				} else {
					tempList.add(unsorted);
				}

			}

		} catch (IOException e) {
			System.out.println("Error in PreProcess: "+ e.getMessage());
			throw e;
		}

	}

	public void unScrambleWord(String scrambledword) {
		System.out.println("The entered word is: \n"+scrambledword);
		char[] input = scrambledword.toCharArray();
		Arrays.sort(input);
	
		ArrayList<String> result = mapwords.get(new String(input));
		System.out.println("The possible words are:");
		
		for (String str : result) {
			System.out.println(str);
		}

	}

	public static void main(String args[]) {

		UnScrambler us = null;
		System.out.println("Enter path to dictionary file:\n");
		Scanner sc = new Scanner(System.in);
		String dFilePath = sc.nextLine();
		
		if(dFilePath != null){
			us = new UnScrambler(dFilePath);
			try {
				us.PreprocessDictionary();
				
				while(true){
					System.out.println("\nEnter scrambled word:\n");
					String scWord = sc.nextLine();
						if(scWord != null){
							us.unScrambleWord(scWord.toLowerCase());
						}
					}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
