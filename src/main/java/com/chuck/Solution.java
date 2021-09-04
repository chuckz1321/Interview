package com.chuck;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 1) return res;
        LinkedList<Integer> tmp = new LinkedList<>();
        dfs(0, nums, res, tmp);
        return res;
    }

    private void dfs(int i, int[] nums, List<List<Integer>> res, LinkedList<Integer> tmp) {
        res.add(new ArrayList<Integer>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.addLast(nums[j]);
            dfs(j + 1, nums, res, tmp);
            tmp.removeLast();
        }
    }


    public void rotate(int[][] matrix) {
        int row = matrix.length; // hang
        int col = matrix[0].length; // lie
        for (int i = 0; i < row / 2; i++) {
            for (int j = 0; j < col; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[row -i - 1][j];
                matrix[row -i - 1][j] = tmp;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = i; j < col; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    public int maxSubArray(int[] nums) {
        int pre = 0;
        int max = nums[0];
        for (int x : nums) {
            pre = Math.max(x, pre+x);
            max = Math.max(pre ,max);
        }
        return max;
    }

    public int minFallingPathSum(int[][] matrix) {
        int l = matrix.length;
        if (l == 1) return matrix[0][0];
        for (int i = 1; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (j == 0) {
                    matrix[i][j] += Math.min(matrix[i - 1][j], matrix[i - 1][j+1]);
                } else  if (j == l - 1) {
                    matrix[i][j] += Math.min(matrix[i - 1][j], matrix[i-1][j-1]);
                } else {
                    matrix[i][j] += Math.min(matrix[i - 1][j], Math.min(matrix[i-1][j-1], matrix[i - 1][j+1]));
                }
            }
        }
        int min =  Integer.MAX_VALUE;
        for (int i = 0; i < l; i++) {
            min = Math.min(matrix[l - 1][i], min);
        }
        return min;
    }


    int number = 0;
    public int findTargetSumWays(int[] nums, int target) {
        findTargetSumWays(nums, 0, target);
        return number;
    }

    private void findTargetSumWays(int[] nums, int index, int target) {
        if (target == 0 && index == nums.length) {
            number++;
            return;
        }
        if (index == nums.length - 1) return;
        findTargetSumWays(nums, index + 1, target - nums[index]);
        findTargetSumWays(nums, index + 1, target + nums[index]);
    }

    int[][] disMemo;
    public int minDistance(String word1, String word2) {
        disMemo = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) {
            for (int j = 0; j < word2.length() + 1; j++) {
                disMemo[i][j] = -1;
            }
        }
        return minDisDp(word1, word2, word1.length() - 1, word2.length() - 1);
    }

    private int minDisDp(String word1, String word2, int m, int n) {
        if (m == -1) return n+1;
        if (n == -1) return m+1;

        if (disMemo[m][n] > 0) return disMemo[m][n];

        if (word1.charAt(m) == word2.charAt(n)) {
            disMemo[m][n] = minDisDp(word1, word2, m - 1, n - 1);
            return disMemo[m][n];
        }
        // 增加一个
        // 删除一个
        // 替换一个
        disMemo[m][n] = Math.min(minDisDp(word1, word2, m, n - 1), Math.min(minDisDp(word1, word2, m - 1, n),
                minDisDp(word1, word2, m - 1, n - 1) )) + 1;

        return disMemo[m][n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findTargetSumWays(new int[]{1,1}, 2);
    }



    public int numDecodings(String s) {
        int n = s.length();
        int prepre = 0;
        int pre = 1;
        int cur = 0;
        for (int i = 1; i <= n ; i++) {
            cur = 0;
            if (s.charAt(i - 1) != '0') {
                cur += pre;
            }
            if (i > 1 && s.charAt(i - 2) != '0' &&((s.charAt(i-2) - '0') * 10 + (s.charAt(i-1) - '0') ) <=26) {
                cur += prepre;
            }
            prepre = pre;
            pre = cur;
        }

        return cur;
    }
}
