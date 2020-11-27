//Ryan Lam
//1/15/2020
//CSE 143
//TA: Aaron Su
//Assigntment#1
//This program will keep track the number of occurence of each letter in a string
//that is passed in. It will ignore the case of letters and anything that is not an
//alphabetic character. It can also create a new object that represents the sum or
//difference of two inventory.

public class LetterInventory{
   private int[] inventory;
   private int size;
   public static final int CAPACITY = 26;
   
   // pre: capacity >= 0
   // post: constructs an inventory of default capacity
   //       find the integer value of each character in the string
   //       and increments the value at the index of inventory that
   //       corresponds to the value of the character
   public LetterInventory(String data){
      inventory = new int[CAPACITY];
      size = 0;
      data = data.toLowerCase();
      for(int i = 0; i<=data.length()-1; i++){
         char letter = data.charAt(i);
         int num = letter;
         for(int j = 0; j<= inventory.length; j++){
            if(num-97 == j){
               inventory[j]++;
               size++;
            }
         }
      }
   }
   
   
   // post: returns the current count in the inventory
   public int size(){
      return size;
   }
   
   // post: returns true if the inventory is empty
   public boolean isEmpty(){
      return size == 0;
   }
   
   // pre: letter has to be an alphabetic character
   //      (throws IllegalArgumentException if not)
   // post: return the count of the given letter in the inventory
   public int get(char letter){
      if(isAlphabetic(letter)){
         return inventory[getInt(Character.toLowerCase(letter))];
      }else{
         throw new IllegalArgumentException();
      }
   }
   
   // post: return a string of all the letters in the inventory in
   //       lowercase and in sorted order. Surrounded by square brackets.
   public String toString(){
      String word = "[";
      for(int i = 0; i<CAPACITY; i++){
         for(int j = 0; j<inventory[i]; j++){
            word+= getChar(i);
         }
      }
      word+= "]";
      return word;
   }
   
   // pre: letter has to be an alphabetic character
   //      (throws IllegalArgumentException if not)
   // post: set the count for the letter in the inventory to
   //       the given value.
   public void set(char letter, int value){
      if(isAlphabetic(letter) && value>= 0){
         int num = getInt(Character.toLowerCase(letter));
         size += value - inventory[num];
         inventory[num] = value;
      }else{
         throw new IllegalArgumentException();
      }
   }
   
   // post: construct a new LetterInventory object that represent
   //       the sum of this inventory and the other inventory.
   public LetterInventory add(LetterInventory other){
      LetterInventory addInventory = new LetterInventory("");
      for(int i = 0; i<CAPACITY; i++){
         int num = inventory[i];
         char letter = getChar(i);
         int num2 = other.get(letter);
         addInventory.set(letter, num + num2);
      }
      return addInventory;
   }
   
   // post: construct a new LetterInventory object that represent
   //       the difference of this inventory and the other inventory.
   public LetterInventory subtract(LetterInventory other){
      LetterInventory subtractInventory = new LetterInventory("");
      for(int i = 0; i<CAPACITY; i++){
         int num = inventory[i];
         char letter = getChar(i);
         int num2 = other.get(letter);
         if(num - num2 < 0){
            return null;
         }
         subtractInventory.set(letter, num - num2);
      }
      return subtractInventory;
   }
   
   // post: return a character after casting the sum of the int passed
   //       in and 97.
   private char getChar(int num){
      return (char)(num+97);
   }
   
   // post: return an int after casting the difference of the char passed
   //       in and 97.
   private int getInt(char letter){
      return letter-97;
   }
   
   // post: return true if the char passed in is an alphabetic character,
   //       false if not.
   private boolean isAlphabetic(char letter){
      return Character.isLetter(letter);
   }
}