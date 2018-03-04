package gumtree.api.utils;

import java.util.Random;

public class Utility {

  private static final Random rnd = new Random();

  public static String generateRandomString() {
    char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < 10; i++) {
      char c = chars[rnd.nextInt(chars.length)];
      sb.append(c);
    }
    return sb.toString();
  }

  public static void printRandomEmails(int randomNumberLength, String emailDomain) {

    String email = generateRandomString() + getRandomNumber() + emailDomain;
    System.out.println("email address is " + email);
  }

  public static int getRandomNumber() {
    return (int) (Math.random() * 100);
  }

}
