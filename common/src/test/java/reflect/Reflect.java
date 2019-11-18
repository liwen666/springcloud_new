package reflect;

import org.junit.Test;
import org.reflections.Reflections;

import java.util.Set;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class Reflect {
    @Test
    public void name() {
        Reflections reflections = new Reflections("reflect.test");
        Set<Class<? extends Reflect>> dataAdapterClassSet = reflections.getSubTypesOf(Reflect.class);
        Class<? extends Reflect> next = dataAdapterClassSet.iterator().next();
        System.out.println(next.getName());

    }
}
