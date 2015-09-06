import java.io.*;
import java.util.*;

public class Huffman {

	public static void main(String[] args) throws IOException {

		//read a file and parse it into words
		//count the frequency of each character
		//create a queue of Pair objects
		//create another queue of Pair objects
		//create the Huffman tree
		//find encodings
		String fname;
		File file;
		Scanner keyboard = new Scanner(System.in);
		Scanner inFile;

		String line, word;
		StringTokenizer token;
		int[] freqTable = new int[256];

		System.out.println ("Enter the complete path of the file to read from: ");

		fname = keyboard.nextLine();
		file = new File(fname);
		inFile = new Scanner(file);

		while (inFile.hasNext()) {
			line = inFile.nextLine();
			token = new StringTokenizer(line, " ");
			while (token.hasMoreTokens()) {
				word = token.nextToken();
				freqTable = updateFrequencyTable(freqTable, word);
			}
		}

		//print frequency table

		System.out.println("Table of frequencies");
		System.out.println("Character \t Frequency \n");

		for(int i=0; i<256; i++) {
			if (freqTable[i]>0)
				System.out.println(((char)i) + "\t" + freqTable[i]);
			}

		Queue<BinaryTree<Pair>> S = buildQueue(freqTable);

		Queue<BinaryTree<Pair>> T = new Queue<BinaryTree<Pair>>();

		BinaryTree<Pair> huffmanTree = createTree(S, T);

		String[] encodingTable = findEncoding(huffmanTree);

		System.out.println("Encoding Table");
		for(int i=0; i<256; i++) {
			if (encodingTable[i]!=null)
				System.out.println(((char)i) + "\t" + encodingTable[i]);
		}
		inFile.close();
	}

	public static int[] updateFrequencyTable(int[] result, String s) {
		for (int i=0; i<s.length(); i++)
		result[(byte)s.charAt(i)]++;

		return result;
	}

	public static Pair findSmallest(int[] table) {
		//find smallest freq in the freq table and return a Pair object
		int smallest = table[0];
		char current = 'a';
		int index = 0;
		int i = 1;
		while(i < 256) {
			if(table[i] == 0 || table[i] >= smallest) {
				i++;
			} else {
				smallest = table[i];
				current = ((char)i);
				index = i;
				i++;
			}
		}
		Pair result = new Pair(current, smallest);

		//also reset the found value in the array to zero
		table[index] = 0;

		return result;
	}


	public static Queue<BinaryTree<Pair>> buildQueue(int[] table) {
		Queue <BinaryTree<Pair>> q = new Queue<BinaryTree<Pair>>();

		//create a Queue of Pairs sorted from the smallest freq
		//make use of the method findSmallest
		for(int i = 0; i < table.length; i ++) {
			Pair temp = findSmallest(table);
			BinaryTree<Pair> current = new BinaryTree<Pair>();
			current.setData(temp);
			q.enqueue(current);
		}

		return q;
	}

	public static BinaryTree<Pair> createTree(Queue<BinaryTree<Pair>> S, Queue<BinaryTree<Pair>> T) {
		BinaryTree<Pair> resultTree = new BinaryTree<Pair>();
		char dummy = '0';

		BinaryTree<Pair> temp1 = new BinaryTree<Pair>();
		BinaryTree<Pair> temp2 = new BinaryTree<Pair>();
		BinaryTree<Pair> root = new BinaryTree<Pair>();

		while(!S.isEmpty()) {
			temp1 = S.dequeue();
			temp2 = S.dequeue();
			root.setData(new Pair(dummy, temp1.getData().getFreq() + temp2.getData().getFreq()));
			root.setLeft(temp1);
			root.setRight(temp2);
			T.enqueue(root);
		}

		resultTree = T.dequeue();

		return resultTree;

	}

	//methods for finding the encoding table
	public static void findEncoding(BinaryTree<Pair> t, String[] a, String prefix) {
		if (t.getLeft()==null&& t.getRight()==null) {
			a[(byte)(t.getData().getValue())]= prefix;
		} else {
			findEncoding(t.getLeft(), a, prefix+"0");
			findEncoding(t.getRight(), a, prefix+"1");
		}

	}

	public static String[] findEncoding(BinaryTree<Pair> t) {
		String[] result = new String[256];
		findEncoding(t, result, "");
		return result;
	}
}


