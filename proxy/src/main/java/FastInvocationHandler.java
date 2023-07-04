import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FastInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("get"))
            return "too fast!";
        else
            throw new UnsupportedOperationException(
                "지원안함: " + method.getName()
            );
    }
}
