/*
 * @lc app=leetcode id=989 lang=java
 *
 * [989] Add to Array-Form of Integer
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        int length = num.length - 1;

        int sum = 0, carry = 0;
        List<Integer> result = new ArrayList<>();
        while (length >= 0 || k != 0 ) {

            // x and y, is checked the condition then caculate then give the value.
            int x = length >= 0 ? num[length] : 0;
            int y = k != 0 ? k % 10: 0;
            length --;
            sum = x + y + carry;
            carry = sum / 10;
            k = k / 10;
            result.add(0, sum % 10);
        }

        if(carry != 0){
            result.add(0, carry);
        }

        return result;
    }
}
// @lc code=end
