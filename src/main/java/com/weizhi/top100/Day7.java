package com.weizhi.top100;

/**
 * @Author: Weizhi @Date: create in 2025/1/28 12:21 @Description:
 */
public class Day7 {
  public int findDuplicate(int[] nums) {
    int slow = nums[0], fast = nums[nums[0]];
    // 让快指针进循环
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[nums[fast]];
    }
    slow = 0;
    // 快指针在循环一直走，slow从0往循环走，再次相遇就是答案
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[fast];
    }
    return slow;
  }

  public static void main(String[] args) {
    Day7 day7 = new Day7();
    int[] ints = new int[] {1, 3, 4, 2, 2};
    System.out.println(day7.findDuplicate(ints));
  }
}
