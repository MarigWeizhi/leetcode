package com.weizhi.top100;

import java.util.ArrayList;
import java.util.List;

public class Day9 {
  public List<Integer> spiralOrder(int[][] matrix) {
    ArrayList<Integer> res = new ArrayList<>();
    int i = 0, j = 0;
    int[][] op = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int direction = 0;
    while (true) {
      if (i >= matrix.length || j >= matrix[0].length || i < 0 || j < 0 || matrix[i][j] == -101) {
        i -= op[direction][0];
        j -= op[direction][1];
        direction = (direction + 1) % 4;
        i += op[direction][0];
        j += op[direction][1];
      }
      res.add(matrix[i][j]);
      matrix[i][j] = -101;
      i += op[direction][0];
      j += op[direction][1];
      if (res.size() == matrix.length * matrix[0].length) {
        break;
      }
    }
    return res;
  }

  public boolean exist(char[][] board, String word) {
    int[][] flag = new int[board.length][board[0].length];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0, flag)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean dfs(char[][] board, int i, int j, String word, int index, int[][] flag) {
    flag[i][j] = 1;
    index++;
    if (index == word.length()) {
      return true;
    }
    if (j < board[0].length - 1
        && flag[i][j + 1] == 0
        && board[i][j + 1] == word.charAt(index)
        && dfs(board, i, j + 1, word, index, flag)) {
      return true;
    }
    if (i < board.length - 1
        && flag[i + 1][j] == 0
        && board[i + 1][j] == word.charAt(index)
        && dfs(board, i + 1, j, word, index, flag)) {
      return true;
    }
    if (j > 0
        && flag[i][j - 1] == 0
        && board[i][j - 1] == word.charAt(index)
        && dfs(board, i, j - 1, word, index, flag)) {
      return true;
    }
    if (i > 0
        && flag[i - 1][j] == 0
        && board[i - 1][j] == word.charAt(index)
        && dfs(board, i - 1, j, word, index, flag)) {
      return true;
    }
    flag[i][j] = 0;
    return false;
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int l1 = 0, l2 = 0, r1 = nums1.length - 1, r2 = nums2.length - 1;
    if (nums1.length == 0) {
      l1 = r1 + 1;
    }
    if (nums2.length == 0) {
      l2 = r2 + 1;
    }
    if (nums1.length == 1 && nums2.length == 1) {
      return (nums1[0] + nums2[0]) / 2.0;
    }

    while (true) {
      if (l1 > r1) {
        if (l2 + 1 == r2 || l2 == r2) {
          return (nums2[l2] + nums2[r2]) / 2.0;
        }
        r2--;
        l2++;
        if (l2 + 1 == r2 || l2 == r2) {
          return (nums2[l2] + nums2[r2]) / 2.0;
        }
        continue;
      }

      if (l2 > r2) {
        if (l1 + 1 == r1 || l1 == r1) {
          return (nums1[l1] + nums1[r1]) / 2.0;
        }
        r1--;
        l1++;
        if (l1 + 1 == r1 || l1 == r1) {
          return (nums1[l1] + nums1[r1]) / 2.0;
        }
        continue;
      }

      if (nums1[r1] >= nums2[r2]) {
        r1--;
      } else {
        r2--;
      }
      if (nums1[l1] <= nums2[l2]) {
        l1++;
      } else {
        l2++;
      }
      if (l1 == r1 && l2 == r2) {
        return (nums1[l1] + nums2[l2]) / 2.0;
      }
      if (l1 > r1 && (l2 == r2 || l2 + 1 == r2)) {
        return (nums2[l2] + nums2[r2]) / 2.0;
      }
      if (l2 > r2 && (l1 == r1 || l1 + 1 == r1)) {
        return (nums1[l1] + nums1[r1]) / 2.0;
      }
    }
  }

  public static void main(String[] args) {
    Day9 day9 = new Day9();
    //    [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
    // [["a","a","a","a"],["a","a","a","a"],["a","a","a","a"]]
    //    char[][] board = {
    //      {'A', 'A', 'A', 'A'},
    //      {'A', 'A', 'A', 'A'},
    //      {'A', 'A', 'A', 'A'},
    //    };
    //    System.out.println(day9.exist(board, "AAAAAAAAAAAAA"));
    int[] arr1 = {1};
    int[] arr2 = {2};
    System.out.println(day9.findMedianSortedArrays(arr1, arr2));
  }
}
