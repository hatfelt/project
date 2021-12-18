	import java.io.*;
	import java.util.*;
	

	public class Cipher {
		public static void main(String[] args) throws IOException {
				processer();
		}
		public static ArrayList<Character> base = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
				'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
				'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.', '?', '!', ' '));

		public static void processer() throws IOException {
			
			String message;
			String key;

			message = "";
			key = "";

			try {
				 
				File input = new File("D:\\Wasfi.txt");
				Scanner kb = new Scanner(input);

				key = kb.nextLine();

				while (kb.hasNextLine()) {
					message = message + kb.nextLine() + '\n';
				}

				
				
				kb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter encrypted key");
			String tempkey = sc.next();
			// Increase key length
			int lengthhh = message.length() - key.length();

			for (int i = 0; i <= (lengthhh - 1); i++) {

				key += key.charAt(i);
			}
			String encryptedmessage;
			encryptedmessage = Enc(message, key);
			FileOutputStream encryptor = new FileOutputStream("D://encryption1.dec");
			byte[] printer = encryptedmessage.getBytes();
			encryptor.write(printer);
			encryptor.close();
			File genEnc; 
			genEnc= new File("D://encryption1.dec");
			
			dec(genEnc, tempkey);

		}
		
		
		public static void dec(File FL, String key) throws IOException {
			String Text,EncText,decKey,NewdecKey ;
			 Text= ""; 
			 EncText= "";
			 decKey= "";
			 NewdecKey= "";

			Scanner fileReader = new Scanner(FL);

			while (fileReader.hasNextLine()) {
				EncText += fileReader.nextLine() + '\n';
			}

			for (int i = 0; i < key.length(); i++) {
				int num = (67 - base.indexOf(key.charAt(i)) % 67);

				decKey += base.get(num);
			}
			NewdecKey = decKey;
			// Increase key length
			int lengthhh = EncText.length() - key.length();

			for (int i = 0; i <= (lengthhh - 1); i++) {

				decKey += decKey.charAt(i);
			}
			
			Text = Enc(EncText, decKey);
			FileOutputStream decPrinter;
			decPrinter = new FileOutputStream("D://decryption111.txt");
			byte[] decryptedData = Text.getBytes();
			NewdecKey = NewdecKey + '\n';
			decPrinter.write(NewdecKey.getBytes());
			decPrinter.write(decryptedData);

			
			decPrinter.close();

		}


		public static String Enc(String msg, String key) throws IOException {
			
			String finalmessage;
			finalmessage = "";
			int snum = 0;

			for (int i = 0; i < msg.length(); i++) {

				if (msg.charAt(i) == '\n') {
					finalmessage = finalmessage + '\n';
					snum--;
				}

				else {
					int msgindex = base.indexOf(msg.charAt(i));

					int ketindex = base.indexOf(key.charAt(i + snum));

					int index = ((msgindex + ketindex) % 67);

					finalmessage += base.get(index);
				}
			} return finalmessage;

		}

		
	}

