package datastructure.leetcode;

/**
 * @author: Alex
 * @date: 2018/12/13 17:39
 * description: leetcode-922【按奇偶排序数组 II】
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 */
public class SortArrayByParityII922 {

    public static int[] sortArrayByParityII(int[] A){
        int[] B = new int[A.length];
        int dou = 0;
        int sing = 1;
        for(int i = 0; i < A.length; i++){
            if(A[i] % 2 == 0){
                B[dou] = A[i];
                dou += 2;
            }else {
                B[sing] = A[i];
                sing += 2;
            }
        }
        return B;
    }
}
