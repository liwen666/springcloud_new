package java8test;

import java.util.ArrayList;
import java.util.List;

public class Province {
    private String name;// 省名
    private List<City> cities = new ArrayList<City>();
    private String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city) {
        cities.add(city);
    }
}