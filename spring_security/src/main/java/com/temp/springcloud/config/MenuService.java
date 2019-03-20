package com.temp.springcloud.config;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    public List<Menu> getMenusByUserId(String id) {
        Menu m = new Menu();
        m.setUrl("/test/page");
        ArrayList <Menu> list = new ArrayList<Menu>(){ {add(m);}
        };
        return list;
    }
}
