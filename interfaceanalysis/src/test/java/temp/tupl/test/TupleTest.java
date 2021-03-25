package temp.tupl.test;

/**
 * 由于有了泛型,你可以很容易的创建元组,令其返回一组任意类型的对象,而你所要做的,只是编写表达式而已.
 */
public class TupleTest {

    static TwoTuple<String, Integer> f(){
        //Autoboxing conveerts the int to Integer;
        return new TwoTuple<String, Integer>("hi", 47);
    }

    static ThreeTuple<Amphibian, String, Integer> g(){
        return new ThreeTuple<Amphibian, String, Integer>(new Amphibian(), "hi", 47);
    }

    static FourTuple<Vehicle, Amphibian, String ,Integer> h(){
        return new FourTuple<Vehicle, Amphibian, String, Integer>(new Vehicle(), new Amphibian(), "hi", 47);
    }

    public static void main(String[] args) {
        TwoTuple<String, Integer> ttsi = f();
        System.out.println(ttsi);
        System.out.println(g());
        System.out.println(h());
    }

}

class Amphibian {}

class Vehicle {}