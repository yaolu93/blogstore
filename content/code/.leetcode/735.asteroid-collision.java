/*
 * @lc app=leetcode id=735 lang=java
 *
 * [735] Asteroid Collision
 *
 * https://leetcode.com/problems/asteroid-collision/description/
 *
 * algorithms
 * Medium (45.15%)
 * Likes:    7784
 * Dislikes: 1030
 * Total Accepted:    508K
 * Total Submissions: 1.1M
 * Testcase Example:  '[5,10,-5]'
 *
 * We are given an array asteroids of integers representing asteroids in a
 * row.
 * 
 * For each asteroid, the absolute value represents its size, and the sign
 * represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 * 
 * Find out the state of the asteroids after all collisions. If two asteroids
 * meet, the smaller one will explode. If both are the same size, both will
 * explode. Two asteroids moving in the same direction will never meet.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never
 * collide.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide
 * resulting in 10.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= asteroids.length <= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 * 
 * 
 */

// @lc code=start

import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        //[5, 10, -5, -3, -5, -4, 7]
        int size = asteroids.length;
        Stack<Integer> stack = new Stack<>();
        System.out.println(size);

        for (int i = 0; i < size; i++) {
            if (asteroids[i] > 0 || stack.isEmpty()) {
                stack.push(asteroids[i]);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroids[i])) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() == Math.abs(asteroids[i])) {
                    stack.pop();
                } else {
                    if (stack.isEmpty() || stack.peek() < 0) {
                        stack.push(asteroids[i]);
                    }
                }
            }
        }
        // for (int i = 0; i < size ; i++) {
        //     System.out.println(asteroids[i]);
        //     if(asteroids[i] > 0){
        //         stack.push(asteroids[i]);
        //     } else if(asteroids[i] < 0 && Math.abs(asteroids[i]) >= stack.peek()){
        //         while (Math.abs(asteroids[i]) >= stack.peek()) {
        //             stack.pop();
        //             // if(stack.peek() == null){
        //             //     stack.push(asteroids[i]);
        //             // }
        //         }
        //     }
        // }

        int[] array = new int[stack.size()];
        int index = 0;
        for (int ele : stack ) {
            array[index] = ele;
            index++;
        } 


        return array;
    }
}
// @lc code=end
