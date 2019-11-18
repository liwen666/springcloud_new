package method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BeanA {
 
    @Autowired
    private BeanB beanB;
 
    public BeanA() {
        System.out.println("这是Bean A 的构造方法");
    }
 
 
    @PostConstruct
    private void init() {
        System.out.println("这是BeanA的 init 方法");
        beanB.testB();
    }
}