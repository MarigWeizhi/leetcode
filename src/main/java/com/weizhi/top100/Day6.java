package com.weizhi.top100;

/**
 * @Author: Weizhi @Date: create in 2025/1/27 22:28 @Description:
 */
public class Day6 {
  public int maxDepth(TreeNode root) {
    return maxDepth(root, 1);
  }

  public int maxDepth(TreeNode root, int depth) {
    if (root == null) {
      return depth - 1;
    }
    int l = maxDepth(root.left, depth + 1);
    int r = maxDepth(root.right, depth + 1);
    return Math.max(l, r);
  }

  //  通过打表减少递归次数
  int[] ints = new int[10005];

  public int numSquares(int n) {
    if (n <= 0) return 0;
    int x = (int) Math.sqrt(n);
    int ans = Integer.MAX_VALUE;
    //  当成BFS最短路径
    for (int i = 1; i <= x; i++) {
      if (ints[n - i * i] == 0) {
        ints[n - i * i] = numSquares(n - i * i);
      }
      ans = Math.min(ans, ints[n - i * i] + 1);
    }
    return ans;
  }

  public int numSquares2(int n) {
    // 动态规划，dp[i] 表示 i 最少由几个平方数相加的
    // 初始状态都是 i，最差情况
    int[] dp = new int[n + 1];
    for (int i = 0; i < n + 1; i++) {
      dp[i] = i;
    }
    for (int i = 1; i < dp.length; i++) {
      int x = (int) Math.sqrt(i);
      for (int j = 1; j <= x; j++) {
        // 当前这个平方数（j*j）选择要或者不要，挑最小的
        dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
      }
    }

    return dp[n];
  }

  public static void main(String[] args) {
    Day6 day6 = new Day6();
    System.out.println(day6.numSquares(54));
  }
}
