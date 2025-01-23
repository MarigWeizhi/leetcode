package com.weizhi.top100;

import java.util.PriorityQueue;

/**
 * @Author: Weizhi @Date: create in 2025/1/23 23:16 @Description:
 */
public class Day3 {

  public int maxProduct(int[] nums) {
    // 直接遍历一直求最大值，遇0则重置
    // 在没有0的分段中，偶数个负数相乘自然会求出最大值，奇数个负数，要么舍去最左边的负数，要么舍去最右边的负数，两个方向都遍历一次就行
    int max = Integer.MIN_VALUE;
    int temp = 1;
    for (int i = 0; i < nums.length; i++) {
      temp *= nums[i];
      max = Math.max(temp, max);
      if (nums[i] == 0) {
        temp = 1;
      }
    }
    temp = 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      temp *= nums[i];
      max = Math.max(temp, max);
      if (nums[i] == 0) {
        temp = 1;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int[] ints = new int[] {2, 3, -2, 4};
    Day3 day3 = new Day3();
    System.out.println(day3.maxProduct(ints));
  }
}

class MedianFinder {

  public PriorityQueue<Integer> maxHeap;
  public PriorityQueue<Integer> minHeap;

  public MedianFinder() {
    //    提前分配空间，避免扩容
    maxHeap = new PriorityQueue<>(25000, (o1, o2) -> o2 - o1);
    minHeap = new PriorityQueue<>(25000);
  }

  public void addNum(int num) {
    Integer l = maxHeap.peek();
    if (l == null) {
      maxHeap.add(num);
      return;
    }
    if (num <= l) {
      maxHeap.add(num);
      if (maxHeap.size() - minHeap.size() > 1) {
        minHeap.add(maxHeap.remove());
      }
    } else {
      minHeap.add(num);
      if (minHeap.size() - maxHeap.size() > 0) {
        maxHeap.add(minHeap.remove());
      }
    }
  }

  public double findMedian() {
    if (maxHeap.size() == minHeap.size()) {
      return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
    return maxHeap.peek();
  }
}
