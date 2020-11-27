//Ryan Lam
//2/16/2020
//CSE 143
//TA: Aaron Su
//Assigntment#5
//This program finds all combinations of wards that have the same letter with the given
//word.

import java.util.*;
public class AnagramSolver{
   private List<String> dictionary;
   private HashMap<String, LetterInventory> dictionaryMap;
   
   // pre: Assume list is nonempty, no duplicates, and state doesn't change
   // post: Initialize dictionary and creates a map of the dictionary with
   //       the words as key and the LetterInventory of the words as values.
   public AnagramSolver(List<String> list){
      dictionary = list;
      dictionaryMap = new HashMap<String, LetterInventory>();
      for(String find: list){
         LetterInventory newInventory = new LetterInventory(find);
         dictionaryMap.put(find, newInventory);
      }
   }
   
   // pre: max greater than 0 (throw IllegalArgumentException if not)
   // post: Creates a stack and calls createAnagrams to print out all
   //       combinations of words that have same letter as s.
   public void print(String s, int max){
      if(max < 0){
         throw new IllegalArgumentException();
      }
      LetterInventory sInventory = new LetterInventory(s);
      Stack<String> anagrams = new Stack<>();
      List<String> reduceDictionary = new ArrayList<String>();
      for(String find : dictionary){
         if(sInventory.subtract(dictionaryMap.get(find)) != null){
            reduceDictionary.add(find);
         }
      }
      createAnagrams(anagrams, sInventory, max, reduceDictionary);
   }
   
   // post: Runs through the dicitonary and checks if the letters is in the sInventory
   //       If it is then it'll add it to the the stack. If not goes on to next word.
   //       When the sInventory is empty, it'll rpint out the stack.
   private void createAnagrams(Stack<String> anagrams, LetterInventory sInventory,
                                          int max, List<String> reduceDictionary){
      if(sInventory.isEmpty()){
         System.out.println(anagrams.toString());
      }
      if(max == 0 ||  max > anagrams.size()){
         for(String find : reduceDictionary){
            if(sInventory.subtract(dictionaryMap.get(find)) != null){
               sInventory = sInventory.subtract(dictionaryMap.get(find));
               anagrams.push(find);
               createAnagrams(anagrams, sInventory, max, reduceDictionary);
               sInventory = sInventory.add(dictionaryMap.get(find));
               anagrams.pop();
            }
         }
      }
   }
}