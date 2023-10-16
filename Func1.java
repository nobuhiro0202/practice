import java.util.*;

public class Func1 {
  public static ArrayList<String> merge(String[] words, String[] more) {
    ArrayList<String> sentence = new ArrayList<String>();
    for (String w : words) sentence.add(w);
    for (String w : more) sentence.add(w);
    return sentence;
  }

  public static String joinwords(String[] words) {
    StringBuilder sentence = new StringBuilder();
    for (String w : words) {
      sentence.append(w);
    }
    return sentence.toString();
  }

  public static boolean q1_1(String word) {
    boolean[] a = new boolean[128];
    for (int i = 0; i < word.length(); i++) {
      int val = word.charAt(i);
      if (a[val]) return false;
      a[val] = true;
    }
    return true;
  }

  public static boolean q1_2(String s, String t) {
    if (s.length() != t.length()) return false;

    int [] letters = new int[128];
    for (int i = 0; i < s.length() - 1; i++) letters[s.charAt(i)]++;

    for (int i = 0; i < t.length(); i++) {
      letters[t.charAt(i)]--;
      if (letters[t.charAt(i)] < 0) return false;
    }
    
    return true;
  }

  public static void q1_3(String a, int l) {
    int c = 0, index, i = 0;
    char[] str = a.toCharArray();
    for (i = 0; i < l; i++) if (str[i] == ' ') c++;
    index = l + c * 2;
    
    char[] b = new char[index];
    for (i = 0; i < str.length; i++) b[i] = str[i];

    if (l < str.length) b[l] = '\0';
    for (i = l - 1; i >= 0; i--) {
      if (b[i] == ' ') {
        b[index - 1] = '0';
        b[index - 2] = '2';
        b[index - 3] = '%';
        index -= 3;
      } else {
        b[index - 1] = b[i];
        index--;
      }
    }
    String w = new String(b);
    System.out.println(w);
  }

  public static boolean isPermutationOgPalindrome(String phrase) {
    int[] table = buildCharFrequencyTable(phrase);
    return checkMaxOneOdd(table);
  }

  private static boolean checkMaxOneOdd(int[] table) {
    boolean foundOdd = false;
    for (int count : table) {
      if (count % 2 == 1) {
        if (foundOdd) return false;
        foundOdd = true;
      }
    }
    return true;
  }

  private static int[] buildCharFrequencyTable(String phrase) {
    int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
    for (char c : phrase.toCharArray()) {
      int x = getCharNumber(c);
      if (x != -1) table[x]++;
    }
    return table;
  }

  private static int getCharNumber(Character c) {
    int a = Character.getNumericValue('a');
    int z = Character.getNumericValue('z');
    int val = Character.getNumericValue(c);
    if (a <= val && val <= z) return val - a;
    return -1;
  }

  /**
  public static boolean oneEditAway(String f, String s) {
    if (f.length() == s.length()) return oneEditReplace(f, s);
    if (f.length() + 1 == s.length()) return oneEditInsert(f, s);
    if (f.length() - 1 == s.length()) return oneEditInsert(s, f);
    return false;
  }

  private static boolean oneEditReplace(String s1, String s2) {
    boolean foundDifference = false;
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        if (foundDifference) return false;
        foundDifference = true;
      }
    }
    return true;
  }

  private static boolean oneEditInsert(String s1, String s2) {
    int index1 = 0, index2 = 0;
    while (index2 < s2.length() && index1 < s1.length()) {
      if (s1.charAt(index1) != s2.charAt(index2)) {
        if (index1 != index2) return false; 
        index2++;
      } else {
        index1++;
        index2++;
      }
    }
    return true;
  }
  */

  public static boolean oneEditAway(String first, String second) {
    if (first.length() - second.length() > 1) return false;

    String s1 = first.length() < second.length() ? first : second;
    String s2 = first.length() < second.length() ? second : first;

    int index1 = 0, index2 = 0;
    boolean foundDifference = false;
    while (index2 < s2.length() && index1 < s1.length()) {
      if (s1.charAt(index1) != s2.charAt(index2)) {
        if (foundDifference) return false;
        foundDifference = true;

        if (s1.length() == s2.length()) index1++;
      } else index1++;
      index2++;
    }
    return true;
  }

  public static String compressBad(String str) {
    String compressedString = "";
    int countConsecutive = 0;
    for (int i = 0; i < str.length(); i++) {
      countConsecutive++;
      if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
        compressedString += "" + str.charAt(i) + countConsecutive;
        countConsecutive = 0;
      }
    }
    return compressedString.length() < str.length() ? compressedString : str;
  }

  /**
  public static String compress(String str) {
    StringBuilder compressed = new StringBuilder();
    int countConsecutive = 0;
    for (int i = 0; i < str.length(); i++) {
      countConsecutive++;
      if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
        compressed.append(str.charAt(i));
        compressed.append(countConsecutive);
        countConsecutive = 0;
      }
    }
    return compressed.length() < str.length() ? compressed.toString() : str;
  }
  */

  public static String compress(String str) {
    int finalLength = countCompression(str);
    if (finalLength >= str.length()) return str;

    StringBuilder compressed = new StringBuilder(finalLength);
    int countConsecutive = 0;
    for (int i = 0; i < str.length(); i++) {
      countConsecutive++;
      if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
        compressed.append(str.charAt(i));
        compressed.append(countConsecutive);
        countConsecutive = 0;
      }
    }
    return compressed.toString();
  }

  private static int countCompression(String str) {
    int compressedLength = 0;
    int countConsecutive = 0;
    for (int i = 0; i < str.length(); i++) {
      countConsecutive++;
      if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
        compressedLength += 1 + String.valueOf(countConsecutive).length();
        countConsecutive = 0;
      }
    }
    return compressedLength;
  }

  public static boolean rotate(int[][] matrix) {
    if (matrix.length == 0 || matrix.length != matrix[0].length) return false;
    int n = matrix.length;
    for (int layer = 0; layer < matrix.length; layer++) {
      int first = layer;
      int last = n - 1 - layer;
      for (int i = first; i < last; i++) {
        int offset = i - first;
        int top = matrix[first][i];
        matrix[first][i] = matrix[last - offset][first];
        matrix[last - offset][first] = matrix[last][last - offset];
        matrix[last][last - offset] = matrix[i][last];
        matrix[i][last] = top;
      }
    }
    return true;
  }

  /**
  public static void setZeros(int[][] matrix) {
    boolean[] row = new boolean[matrix.length];
    boolean[] column = new boolean[matrix[0].length];

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          row[i] = true;
          column[j] = true;
        }
      }
    }

    for (int i = 0; i < row.length; i++) {
      if (row[i]) nullifyRow(matrix, i);
    }

    for (int i = 0; i < column.length; i++) {
      if (column[i]) nullifyCol(matrix, i);
    }
  }
  */

  public static int[][] setZeros(int[][] matrix) {
    boolean rowHasZero = false;
    boolean colHasZero = false;

    for (int i = 0; i < matrix[0].length; i++) {
      if (matrix[0][i] == 0) {
        rowHasZero = true;
        break;
      }
    }

    for (int i = 0; i < matrix.length; i++) {
      if (matrix[i][0] == 0) {
        colHasZero = true;
        break;
      }
    }

    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }
    
    for (int i = 1; i < matrix.length; i++) if (matrix[i][0] == 0) matrix = nullifyRow(matrix, i);
    for (int i = 1; i < matrix[0].length; i++) if (matrix[0][i] == 0) matrix = nullifyCol(matrix, i);

    if (rowHasZero) matrix = nullifyRow(matrix, 0);
    if (colHasZero) matrix = nullifyCol(matrix, 0);
    return matrix;
  }

  private static int[][] nullifyRow(int[][] matrix, int row) {
    for (int i = 0; i < matrix.length; i++) matrix[row][i] = 0;
    return matrix;
  }

  private static int[][] nullifyCol(int[][] matrix, int col) {
    for (int i = 0; i < matrix.length; i++) matrix[i][col] = 0;
    return matrix;
  }

  public static boolean isRotation(String s1, String s2) {
    if (s1.length() == s2.length() && s1.length() > 0) {
      String s1s1 = s1 + s1;
      return isSubstring(s1s1, s2);
    }
    return false;
  }

  private static boolean isSubstring(String s1, String s2) {
    String l = s1.length() - s2.length() > 0 ? s1 : s2;
    String s = s1.length() - s2.length() > 0 ? s2 : s1;

    for (int i = 0; i < l.length() - s.length(); i++) {
        int j = 0;
        for (; j < s.length(); j++) if (l.charAt(i + j) != s.charAt(j)) break;
        if (j == s.length()) return true;
    }
    return false;
  }
}
