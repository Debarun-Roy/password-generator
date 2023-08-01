import java.security.SecureRandom;  
import java.util.Scanner;
import java.util.Collections;  
import java.util.List;  
import java.util.Random;  
import java.util.stream.Collectors;  
import java.util.stream.IntStream;  
import java.util.stream.Stream;  

public class PasswordGenerator {
    public static void main(String args[]) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter the length of the password that you want to generate : ");
    	int l = sc.nextInt();
    	System.out.println("How many numbers do you want?");
    	int n = sc.nextInt();
    	System.out.println("How many special characters do you want?");
    	int spc = sc.nextInt();
    	System.out.println("How many uppercase letters do you want?");
    	int uc = sc.nextInt();
    	System.out.println("How many lowercase letters do you want?");
    	int lc = sc.nextInt();
        String pass = generateSecurePassword(n,spc,uc,lc);
        System.out.println("Password generated is : "+pass);
        sc.close();
    }
    // create generateSecurePassword() method that finds the secure password
    public static String generateSecurePassword(int n, int spc, int uc, int lc) {
         Stream<Character> demoPassword = Stream.concat(getRandomNumbers(n),   
                 Stream.concat(getRandomSpecialChars(spc),   
                         Stream.concat(getRandomAlphabets(lc, true), getRandomAlphabets(uc, false))));  
        // create a list of Char that stores all the characters, numbers, and special characters   
        List<Character> listOfChar = demoPassword.collect(Collectors.toList());
        // use shuffle() method of the Collections to shuffle the list elements   
        Collections.shuffle(listOfChar);
        //generate a random string(secure password) by using list stream() method and collect() method  
        String password = listOfChar.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)  
                .toString();    
        return password;  
    }  
    // create getRandomSpecialChars() method that returns a Stream of special chars of the specified length  
    public static Stream<Character> getRandomSpecialChars(int length) {  
        Stream<Character> specialCharsStream;  
        Random random = new SecureRandom();  
        // use ints() method of random to get IntStream of special chars of the specified length  
        IntStream specialChars = random.ints(length, 33, 45);  
        specialCharsStream =  specialChars.mapToObj(data -> (char) data);   
        return specialCharsStream;  
    }  
    // create getRandomNumbers() method that returns a Stream of numbers of the specified length  
    public static Stream<Character> getRandomNumbers(int length) {  
        Stream<Character> numberStream;  
        Random random = new SecureRandom();  
        // use ints() method of random to get IntStream of number of the specified length  
        IntStream numberIntStream = random.ints(length, 48, 57);  
        numberStream = numberIntStream.mapToObj(data -> (char) data);  
        return numberStream;  
    }  
    // create getRandomAlphabets() method that returns either a stream of upper case chars or stream of lower case chars  
    // of the specified length based on the boolean variable check  
    public static Stream<Character> getRandomAlphabets(int length, boolean check) {  
        Stream<Character> lowerUpperStream;  
        // for lower case stream  
        if(check == true) {   
            Random random = new SecureRandom();  
            IntStream lCaseStream = random.ints(length, 'a', 'z');  
            lowerUpperStream =  lCaseStream.mapToObj(data -> (char) data);  
        }  
        // for upper case stream  
        else {    
            Random random = new SecureRandom();  
            IntStream uCaseStream = random.ints(length, 'A', 'Z');  
            lowerUpperStream =  uCaseStream.mapToObj(data -> (char) data);  
        }   
        return lowerUpperStream;  
    }  
}
