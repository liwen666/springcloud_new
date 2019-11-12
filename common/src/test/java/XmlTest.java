import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class XmlTest {
    //返回JavaBean集合
    public List<Province> getProvinceList(){
        Province p1=new Province();
        p1.setName("北京");
        p1.addCity(new City("东城区","DongChengQu"));
        p1.addCity(new City("昌平区","ChangPingQu"));
        
        Province p2=new Province();
        p2.setName("辽宁");
        p2.addCity(new City("沈阳","shenYang"));
        p2.addCity(new City("葫芦岛","HuLuDao"));
        List<Province> provinceList=new ArrayList<Province>();
        provinceList.add(p1);
        provinceList.add(p2);
        
        return provinceList;
    }
    
    
    @Test
    public void fun1(){
        List<Province> proList=getProvinceList();
        /*
         * 创建XStream对象
         * 调用toXML把集合转换成xml字符串
         */
        XStream xs=new XStream();
        String xml = xs.toXML(proList);
        System.out.println(xml);
    }
    
    /*
     * 别名
     * 希望：
     *         >默认List类型对应<List>元素，希望让List类型对应<china>元素
     *         >默认Province类型对应<com.xjs.demo1.Province>,希望它对应<province>
     *         >默认City类型对应<com.xjs.demo1.City>，希望对应<city>
     */
    @Test
    public void fun2(){
        List<Province> proList=getProvinceList();
        XStream xs=new XStream();
        /*
         * 给指定的类型指定别名
         */
        xs.alias("china", List.class);//
        xs.alias("province", Province.class);
        xs.alias("city", City.class);
        
        String xml = xs.toXML(proList);
        System.out.println(xml);
    }
    
    /*
     * 默认JavaBean的属性都会生成子元素，而现在希望生成元素的属性
     */
    @Test
    public void fun3(){
        List<Province> proList=getProvinceList();
        XStream xs=new XStream();
        xs.alias("china", List.class);//List类型指定别名为china
        xs.alias("province", Province.class);
        xs.alias("city", City.class);
        
        /*
         * 把Province类型的name属性，生成<province>元素的属性
         */
        
        xs.useAttributeFor(Province.class, "name");
        
        String xml = xs.toXML(proList);
        System.out.println(xml);
    }
    
    /*
     * 去除List类型的属性，只把List中的元素生成XML
     */
    @Test
    public void fun4(){
        List<Province> proList=getProvinceList();
        XStream xs=new XStream();
        xs.alias("china", List.class);//List类型指定别名为china
        xs.alias("province", Province.class);
        xs.alias("city", City.class);
        
        /*
         * 把Province类型的name属性，生成<province>元素的属性
         */
        
        xs.useAttributeFor(Province.class, "name");
        
        /*
         * 去除<cities>这样Collection类型的属性
         * 去除Province类的名为cities的List类型的属性
         */
        xs.addImplicitCollection(Province.class, "cities");
        
        
        String xml = xs.toXML(proList);
        System.out.println(xml);
    }
    
    /*
     * 去除某个JavaBean中的属性
     */
    @Test
    public void fun5(){
        List<Province> proList=getProvinceList();
        XStream xs=new XStream();
        xs.alias("china", List.class);//List类型指定别名为china
        xs.alias("province", Province.class);
        xs.alias("city", City.class);
        
        xs.useAttributeFor(Province.class, "name");
        xs.addImplicitCollection(Province.class, "cities");
        
        /*
         * 让City类的description属性不生成对应 的xml元素
         */
        xs.omitField(City.class, "description");
        
        String xml = xs.toXML(proList);
        System.out.println(xml);
    }
}