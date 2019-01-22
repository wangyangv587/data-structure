package loadbalance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerIps {

    public static Map<String,Integer> IP_MAP = new HashMap<>();

    static {
        IP_MAP.put("192.168.1.1",5);
        IP_MAP.put("192.168.1.2",1);
        IP_MAP.put("192.168.1.3",1);
    }
}
