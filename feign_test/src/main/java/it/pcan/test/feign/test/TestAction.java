package it.pcan.test.feign.test;

public interface TestAction {
    void work(TestBean name);
}
class My_Main {
    private static void t1(TestAction aAction) {
        aAction.work( TestBean.builder().name("test").build());
    }
    public static void main(String[] args) {
        t1((n) -> System.out.println(n + " 正在千锋学习呢"));
      gzipInterceptor().work(TestBean.builder().name("abce").build() );
    }

    static TestAction gzipInterceptor() {
        return testbean -> testbean.test();
    }
}