package com.weizhi.top100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: Weizhi @Date: create in 2025/1/22 20:11 @Description:
 */
public class Day2 {
  public int trap(int[] height) {
    if (height.length <= 1) {
      return 0;
    }
    int ans = 0;
    int l = 0, r = height.length - 1;
    while (true) {
      while (l < height.length && height[l] == 0) l++;
      while (r >= 0 && height[r] == 0) r--;
      if (l + 1 >= r) {
        break;
      }
      for (int j = l; j <= r; j++) {
        if (height[j] == 0) {
          ans++;
        } else {
          height[j]--;
        }
      }
    }
    return ans;
  }

  public int trap2(int[] height) {
    if (height.length <= 1) {
      return 0;
    }
    int ans = 0;
    int curh = 0;
    int l = 0, r = height.length - 1;
    while (l + 1 < r) {
      int lh = height[l];
      int rh = height[r];
      int newh = Math.min(lh, rh);
      ans += (r - l - 1) * (newh - curh);
      curh = newh;
      if (lh == newh) {
        l++;
        while (l + 1 < r && height[l] <= lh) {
          ans -= height[l];
          l++;
        }
        ans -= Math.min(height[l], curh);
      } else {
        r--;
        while (l + 1 < r && height[r] <= rh) {
          ans -= height[r];
          r--;
        }
        ans -= Math.min(height[r], curh);
      }
    }
    return ans;
  }

  public int lengthOfLongestSubstring(String s) {
    int ans = 0;
    int l = 0;
    HashSet<Character> set = new HashSet<>();
    for (int i = 0; i < s.length(); i++) {
      if (set.contains(s.charAt(i))) {
        while (s.charAt(l) != s.charAt(i)) {
          set.remove(s.charAt(l));
          l++;
        }
        l++;
      } else {
        set.add(s.charAt(i));
        ans = Math.max(ans, set.size());
      }
    }
    return ans;
  }

  public int lengthOfLongestSubstring2(String s) {
    int ans = 0;
    int[] chars = new int[128];
    int l = 0;
    for (int i = 0; i < s.length(); i++) {
      if (chars[s.charAt(i)] != 0 && chars[s.charAt(i)] >= l) {
        l = chars[s.charAt(i)];
      }
      chars[s.charAt(i)] = i + 1;
      ans = Math.max(ans, i - l + 1);
    }
    return ans;
  }

  public List<Integer> partitionLabels(String s) {
    ArrayList<Integer> ans = new ArrayList<>();
    //  从后往前遍历，记录每个字符最后出现的位置
    int[] last = new int[128];
    for (int i = s.length() - 1; i >= 0; i--) {
      if (last[s.charAt(i)] != 0) continue;
      last[s.charAt(i)] = i + 1;
    }
    //    i是每一段的起点，r是每一段的终点
    for (int i = 0; i < s.length(); ) {
      int r = last[s.charAt(i)];
      int l = i;
      //      判断起点到终点之间的其他字符是否需要延长终点
      while (l < r) {
        if (last[s.charAt(l)] > r) {
          r = last[s.charAt(l)];
        }
        l++;
      }
      ans.add(r - i);
      i = r;
    }
    return ans;
  }

  public boolean canJump(int[] nums) {
    int max = 0;
    for (int i = 0; i <= max; i++) {
      if (nums[i] + i >= nums.length - 1) {
        return true;
      }
      max = Math.max(max, nums[i] + i);
    }
    return false;

    //    return cancanJump(nums, 0);
  }

  //  递归法，会超时
  public boolean cancanJump(int[] nums, int index) {
    if (nums[index] + index >= nums.length - 1) return true;
    for (int i = nums[index]; i > 0; i--) {
      if (cancanJump(nums, index + i)) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Day2 day2 = new Day2();
    //    int[] ints = new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    //    int[] ints = new int[] {4, 2, 0, 3, 2, 5};
    int[] ints = new int[] {1, 2, 3};
    //    System.out.println(day2.trap2(ints));
    //    String str = "ababcbacadefegdehijhklij";
    //    String str = "eccbbbbdec";
    //    String str = "pwwkew";
    //    String str = "a";
    //    System.out.println(day2.lengthOfLongestSubstring2(str));
    //    System.out.println(day2.partitionLabels(str));
    System.out.println(day2.canJump(ints));
  }
}
