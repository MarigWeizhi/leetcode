package com.weizhi.top100;

/**
 * @Author: Weizhi @Date: create in 2025/1/26 11:54 @Description:
 */
public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
