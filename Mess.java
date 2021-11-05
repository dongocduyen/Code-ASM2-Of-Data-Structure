import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Mess {
  
  ArrayList<String> Message_Buffer = new ArrayList<String>();

  Queue<String> Message_Queue = new LinkedList<String>();
  
  int length_Buffer = 40;

  int Send(String mess) {
    String destination = "";
    int i = 0, j = 0;
    String letter;
    int count = 0;

    if (mess.length() <= 0) {
      System.out.println("You did not write anything ヾ(≧へ≦)〃 Please try again!!!.");
      return count;
    }

    if (mess.length() > 250) {
      System.out.println("The message > 250 letters 🥲 Try again!!!");
      return count;
    }
    System.out.println("Length of message: " + mess.length() + " letters.");

    try {
      while (i < mess.length()) {
        while (Message_Buffer.size() < length_Buffer && i < mess.length()) {// A is not full
          letter = mess.substring(i, i + 1); // Read the letter from s
          Message_Buffer.add(letter); // Add the letter to A
          i++; // Move to the next letter of s
        }

        while (Message_Buffer.size() > j) {
          letter = Message_Buffer.get(j); // Read the letter from A
          Message_Queue.add(letter); // Add the letter to Q
          Message_Buffer.remove(j); // Remove the moved letter from A
        }

        while (Message_Queue.size() > j) {
          letter = Message_Queue.peek();
          destination += letter;
          Message_Queue.remove();
        }

        count++;
      }
    } catch (Exception error) {
      System.out.println("Error: " + error);
      return count;
    }
    System.out.println("Message received : " + destination);
    return count;
  }
}

class Main {
  public static void main(String[] args) {
    Mess Message_Object = new Mess();

    Scanner scan = new Scanner(System.in);

    System.out.print("Please enter your message here: ");
    String message = scan.nextLine();

    double start = System.currentTimeMillis();
    int count = Message_Object.Send(message);
    double finish = System.currentTimeMillis();
    double time = (finish - start) / Message_Object.length_Buffer;
    double space = Message_Object.length_Buffer * 2;

    if (count > 0) {
      System.out.print("\n\nSent ||ヽ(*￣▽￣*)ノミ|Ю");
      System.out.print("\nThe number of buffer usage: " + count + " times.");

      System.out.printf("\nTime : %.1f ms", time);
      System.out.printf("\nSpace : %.1f byte", space);
    } else
      System.out.print("\nMessage not received (˘･_･˘) ");
  }
}