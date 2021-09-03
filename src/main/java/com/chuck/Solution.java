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


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.subsets(new int[]{1,2});
    }
}
