import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ProxyTest {
    @Test
    void 실행시간_측정_프록시() {
        Map hashMap = (Map) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[] {Map.class},
                new TimerInvocationHandler(new HashMap<>()));

        Map treeMap = (Map) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[] {Map.class},
                new TimerInvocationHandler(new TreeMap<>()));

        hashMap.put("hello", "jaedoo");
        treeMap.put("hello", "jaedoo");
        hashMap.get("no");
        treeMap.get("no");
    }
}
