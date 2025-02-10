package com.weizhi.top100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day8 {
  public boolean searchMatrix(int[][] matrix, int target) {
    int l = 0, r = matrix.length - 1;
    while (l <= r) {
      int mid = (l + r) / 2;
      if (matrix[mid][0] > target) {
        r = mid - 1;
        continue;
      }
      if (matrix[mid][matrix[mid].length - 1] < target) {
        l = mid + 1;
        continue;
      }
      int res = Arrays.binarySearch(matrix[mid], target);
      return res >= 0;
    }
    return false;
  }

  List<Integer> res;
  int maxDepth;

  public List<Integer> rightSideView(TreeNode root) {
    res = new ArrayList<>();
    maxDepth = 0;
    deepSeek(root, 1);
    return res;
  }

  public void deepSeek(TreeNode root, int depth) {
    if (root == null) {
      return;
    }
    if (depth > maxDepth) {
      maxDepth = depth;
      res.add(root.val);
    }
    deepSeek(root.right, depth + 1);
    deepSeek(root.left, depth + 1);
  }

  public int jump(int[] nums) {
    if (nums.length == 1) {
      return 0;
    }
    int res = 1;
    int next = nums[0];
    for (int i = 0; i < nums.length; ) {
      if (next >= nums.length - 1) {
        return res;
      }
      int max = 0;
      for (int j = i + 1; j <= next; j++) {
        if (nums[j] + j > max) {
          max = nums[j] + j;
          i = j;
        }
      }
      next = max;
      res++;
    }
    return res;
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    int cur = 0;
    res.add(new ArrayList<>());
    TreeNode flag = new TreeNode(-999);
    ArrayDeque<TreeNode> tree = new ArrayDeque<>();
    tree.add(root);
    tree.add(flag);
    while (!tree.isEmpty()) {
      TreeNode node = tree.poll();
      if (node == flag) {
        if (tree.isEmpty()) {
          return res;
        }
        cur++;
        res.add(new ArrayList<>());
        tree.add(flag);
        continue;
      }
      res.get(cur).add(node.val);
      if (node.left != null) {
        tree.add(node.left);
      }
      if (node.right != null) {
        tree.add(node.right);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Day8 day8 = new Day8();
    int[][] matrix = {
      {1, 3, 5, 7},
      {10, 11, 16, 20},
      {23, 30, 34, 50}
    };
    System.out.println(day8.searchMatrix(matrix, 51));

    //    [2,3,1,1,4]
  }
}
