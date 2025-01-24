package com.weizhi.top100;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @Author: Weizhi @Date: create in 2025/1/24 23:25 @Description:
 */
public class Day4 {
  public int[] topKFrequent(int[] nums, int k) {
    if (nums.length == 1) {
      return new int[] {nums[0]};
    }
    Arrays.sort(nums);
    int n = nums[0], c = 1;
    int[] ans = new int[k];
    PriorityQueue<int[]> ints = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == n) {
        c++;
      } else {
        ints.add(new int[] {c, n});
        c = 1;
        n = nums[i];
      }
      if (i == nums.length - 1) {
        ints.add(new int[] {c, n});
      }
    }
    for (int i = 0; i < k; i++) {
      ans[i] = ints.poll()[1];
    }
    return ans;
  }

  public static void main(String[] args) {
    Day4 day4 = new Day4();
    System.out.println(Arrays.toString(day4.topKFrequent(new int[] {1, 2}, 2)));
  }
}

class MinStack {
  PriorityQueue<Integer> heap = new PriorityQueue<>();
  Stack<Integer> stack = new Stack<Integer>();

  public MinStack() {}

  public void push(int val) {
    heap.add(val);
    stack.push(val);
  }

  public void pop() {
    Integer pop = stack.pop();
    heap.remove(pop);
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return heap.peek();
  }
}

/**
 * Your MinStack object will be instantiated and called as such: MinStack obj = new MinStack();
 * obj.push(val); obj.pop(); int param_3 = obj.top(); int param_4 = obj.getMin();
 */
