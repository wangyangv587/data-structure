package datastructure.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author: Alex
 * @date: 2018/12/13 17:37
 * description: leetcode-347.【前K个高频元素】
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
示例 1:
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:
输入: nums = [1], k = 1
输出: [1]
说明：
你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */
public class TopKFrequentElements347 {

    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer,Integer> tree = new TreeMap<>();
        for(int num : nums){
            if(tree.containsKey(num)){
                tree.put(num,tree.get(num) + 1);
            }else{
                tree.put(num,1);
            }
        }

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b)->{
            return tree.get(b) - tree.get(a);
        });
        for(Integer num : tree.keySet()){
            queue.add(num);
        }

        List<Integer> res = new ArrayList<>(k);
        for(int i = 0; i < k; i++){
            res.add(queue.poll());
        }
        return res;
    }
}
