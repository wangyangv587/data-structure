package loadbalance.round;

import loadbalance.ServerIps;

import java.util.HashMap;
import java.util.Map;

/**
 * 平滑加权轮询算法
 *
 * 每个服务器对应两个权重，分别为weight和currentWeight，其中weight是固定的，currentWeight会动态
 * 调整，初始值为0.当有新的请求进来时，遍历服务器列表，让他的currentWeight加上自身权重，遍历完成
 * 之后，找到最大的currentWeight，并将其减去权重之和，返回对应的服务器ip即可
 */
public class RoundRobin {

    /**
     * cw：当前权重值（固定权重值+当前权重值）
     * mw：当前权重值最大ip（return）
     * cw.mw-tt：最大权重-权重之和后的权重值
     *
     *     cw    mw  cw.mw-tt
     1 [5, 1, 1] A [-2, 1, 1]
     2 [3, 2, 2] A [-4, 2, 2]
     3 [1, 3, 3] B [1, -4, 3]
     4 [6, -3, 4] A [-1, -3, 4]
     5 [4, -2, 5] C [4, -2, -2]
     6 [9, -1, -1] A [2, -1, -1]
     7 [7, 0, 0] A [0, 0, 0]

     */
    private static Map<String,Weight> weightMap = new HashMap<>();

    public static String getServer(){

        //计算权重值之和
        int totalWeight = ServerIps.IP_MAP.values().stream().reduce(0,(w1,w2) -> w1 + w2);

        //初始化权重对象map
        if(weightMap.isEmpty()){
            for(String ip : ServerIps.IP_MAP.keySet()){
                weightMap.put(ip,new Weight(ip,ServerIps.IP_MAP.get(ip),ServerIps.IP_MAP.get(ip)));
            }
        }

        //当前权重值最大的weight
        Weight maxCurrentWeight = null;
        for(Weight weight : weightMap.values()){
            if(maxCurrentWeight == null || weight.getCurrentWeight() > maxCurrentWeight.getCurrentWeight()){
                maxCurrentWeight = weight;
            }
        }

        //把当前权重值最大的weight对象的当前权重值进行更新
        maxCurrentWeight.setCurrentWeight(maxCurrentWeight.getCurrentWeight() - totalWeight);

        //把所有weight的当前权重值进行更新
        for(Weight weight : weightMap.values()){
            weight.setCurrentWeight(weight.getCurrentWeight() + weight.getWeight());
        }
        return maxCurrentWeight.getIp();
    }

    public static void main(String[] args) {
        for(int i = 0; i < 7; i++){
            System.out.println(getServer());
        }
    }
}
