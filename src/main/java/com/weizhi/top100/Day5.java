package com.weizhi.top100;

/**
 * @Author: Weizhi @Date: create in 2025/1/26 11:17 @Description:
 */
public class Day5 {

  public int[] searchRange(int[] nums, int target) {
    if (nums.length == 0) return new int[] {-1, -1};
    int l1 = 0, l2 = nums.length - 1, r1 = 0, r2 = nums.length - 1;
    while (l1 < l2) {
      int lmid = l1 + (l2 - l1) / 2;
      if (nums[lmid] > target) {
        l2 = lmid - 1;
        continue;
      }
      if (nums[lmid] < target) {
        l1 = lmid + 1;
        continue;
      }
      if (nums[lmid] == target) {
        l2 = lmid;
      }
    }

    while (r1 < r2) {
      int rmid = r1 + (r2 - r1 + 1) / 2;
      if (nums[rmid] > target) {
        r2 = rmid - 1;
        continue;
      }
      if (nums[rmid] < target) {
        r1 = rmid + 1;
        continue;
      }
      if (nums[rmid] == target) {
        r1 = rmid;
      }
    }

    if (nums[l1] != target || nums[r1] != target) {
      return new int[] {-1, -1};
    }

    return new int[] {l1, r1};
  }

  public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i = 0; i < n; i++) {
      dp[0][i] = 1;
    }
    for (int i = 0; i < m; i++) {
      dp[i][0] = 1;
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }

    return dp[m - 1][n - 1];
  }

  public boolean isValidBST(TreeNode root) {
    if (root == null) return true;
    if (root.left != null) {
      if (root.left.val >= root.val) {
        return false;
      }
      if (!isLessThan(root.left, root.val)) {
        return false;
      }
      if (!isValidBST(root.left)) {
        return false;
      }
    }

    if (root.right != null) {
      if (root.right.val <= root.val) {
        return false;
      }
      if (!isGreaterThan(root.right, root.val)) {
        return false;
      }
      if (!isValidBST(root.right)) {
        return false;
      }
    }
    return true;
  }

  public boolean isGreaterThan(TreeNode root, int target) {
    if (root.val <= target) {
      return false;
    }
    if (root.left != null && !isGreaterThan(root.left, target)) {
      return false;
    }
    return true;
  }

  public boolean isLessThan(TreeNode root, int target) {
    if (root.val >= target) {
      return false;
    }
    if (root.right != null && !isLessThan(root.right, target)) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Day5 day5 = new Day5();
    int[] input = {5, 7, 7, 8, 8, 10};
    int[] ints = day5.searchRange(input, 6);
    for (int i : ints) {
      System.out.print(i + " ");
    }
    System.out.println();
  }
}
