package com.weizhi.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public static int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(target - nums[i])) {
        return new int[] {i, map.get(target - nums[i])};
      }
      map.put(nums[i], i);
    }
    return null;
  }

  public static List<List<String>> groupAnagrams(String[] strs) {
    HashMap<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      char[] charArray = str.toCharArray();
      Arrays.sort(charArray);
      String s = new String(charArray);
      map.compute(
          s,
          (k, v) -> {
            if (v == null) {
              v = new ArrayList<>();
            }
            v.add(str);
            return v;
          });
    }
    return new ArrayList<List<String>>(map.values());
  }

  public static int longestConsecutive(int[] nums) {
    if (nums.length == 0) return 0;
    int res = 1;
    HashMap<Integer, Integer> max2min = new HashMap<>();
    HashMap<Integer, Integer> min2max = new HashMap<>();
    for (int n : nums) {
      if (max2min.containsKey(n) || min2max.containsKey(n)) {
        continue;
      }
      min2max.put(n, n);
      max2min.put(n, n);

      if (max2min.containsKey(n - 1)) {
        Integer l = max2min.get(n - 1);
        min2max.put(l, n);
        max2min.put(n, l);
        if (n - l + 1 > res) {
          res = n - l + 1;
        }
      }

      if (min2max.containsKey(n + 1)) {
        Integer r = min2max.get(n + 1);
        min2max.put(n, r);
        max2min.put(r, n);
        if (r - n + 1 > res) {
          res = r - n + 1;
        }
        if (max2min.containsKey(n - 1)) {
          Integer ll = max2min.get(n - 1);
          max2min.put(r, ll);
          min2max.put(ll, r);

          if (r - ll + 1 > res) {
            res = r - ll + 1;
          }
        }
      }
    }
    return res;
  }

  // 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
  //
  // 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
  //
  // 示例 1:
  //
  // 输入: nums = [0,1,0,3,12]
  // 输出: [1,3,12,0,0]
  // 示例 2:
  public static void moveZeroes(int[] nums) {
    int count = 0;
    for (int i = 0; i + count < nums.length; ) {
      if (nums[i] == 0) {
        for (int j = i + 1; j < nums.length - count; j++) {
          nums[j - 1] = nums[j];
          nums[j] = 0;
        }
        count++;
      } else {
        i++;
      }
    }
  }

  public static void moveZeroes2(int[] nums) {
    int l = -1;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0 && l == -1) {
        l = i;
      }
      if (nums[i] != 0 && l != -1) {
        nums[l] = nums[i];
        nums[i] = 0;
        l++;
      }
    }
  }

  public static int maxArea(int[] height) {
    int ans = 0;
    for (int i = 0; i < height.length; i++) {
      for (int j = i + 1; j < height.length; j++) {
        int h = Math.min(height[i], height[j]);
        if (h * (j - i) > ans) {
          ans = h * (j - i);
        }
      }
    }
    return ans;
  }

  public static int maxArea2(int[] height) {
    int l = 0, r = height.length - 1;
    int ans = 0;
    while (l < r) {
      int h = Math.min(height[l], height[r]);
      if (h * (r - l) > ans) {
        ans = h * (r - l);
      }
      if (height[l] == h) {
        l++;
      } else {
        r--;
      }
    }
    return ans;
  }

  public static List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    Map<String, List<Integer>> ans = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int l = i + 1, r = nums.length - 1;
      int target = -nums[i];
      if (target < 0) break;
      if (i > 0 && nums[i] == nums[i - 1]) continue;
      while (l < r) {
        if (l == i) {
          l++;
          continue;
        }
        if (r == i) {
          r--;
          continue;
        }

        if (nums[l] + nums[r] == target) {
          List<Integer> list = Arrays.asList(nums[l], nums[r], nums[i]);
          ans.put(list.stream().sorted().toList().toString(), list);
          l++;
        }
        if (nums[l] + nums[r] < target) {
          l++;
        } else {
          r--;
        }
      }
    }
    return ans.values().stream().toList();
  }

  public static void main(String[] args) {
    //      ["eat","tea","tan","ate","nat","bat"]
    //    int[] ints =
    //        new int[] {-1, 9, -3, -6, 7, -8, -6, 2, 9, 2, 3, -2, 4, -1, 0, 6, 1, -9, 6, 8, 6, 5,
    // 2};
    int[] ints = new int[] {-1, 0, 1, 2, -1, -4};
    //    int[] ints = new int[] {100, 4, 200, 1, 3, 2};
    //    List<List<String>> lists =
    //        groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
    //    moveZeroes2(ints);
    //    for (int a : ints) {
    //      System.out.println(a);
    //    }
    System.out.println(threeSum(ints));
    //    System.out.println(Arrays.asList(ints));
  }
}
