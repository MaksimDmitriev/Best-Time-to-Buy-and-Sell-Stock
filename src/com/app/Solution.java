package com.app;

public class Solution {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int start = 0;
        int end = prices.length - 1;
        int[] diffs = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            diffs[i] = prices[i] - prices[i - 1];
        }
        return maxProfit(diffs, start, end);
    }

    private int maxProfit(int[] diffs, int start, int end) {
        if (start == end) {
            return diffs[start];
        } else {
            int mid = start + (end - start) / 2;
            int left = maxProfit(diffs, start, mid);
            int right = maxProfit(diffs, mid + 1, end);
            int cross = maxProfitOfCrossingSubArray(diffs, start, mid, end);
            return findMaxOfTree(left, right, cross);
        }
    }

    private int maxProfitOfCrossingSubArray(int[] diffs, int start, int mid, int end) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= start; i--) {
            sum += diffs[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= end; i++) {
            sum += diffs[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }

        return leftSum + rightSum;
    }

    private int findMaxOfTree(int leftSum, int rightSum, int crossSum) {
        if (leftSum >= rightSum && leftSum >= crossSum) {
            return leftSum;
        } else if (rightSum >= leftSum && rightSum >= crossSum) {
            return rightSum;
        } else {
            return crossSum;
        }
    }
}
