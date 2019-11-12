package com.temp.springboot.common.util;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CollerctionsUtilsBatch {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
//        System.out.println(list);
//        //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
//
//        //获取元素3-7
//        List<Integer> subList = list.subList(3, 8);
//        System.out.println(subList);
//        //[3, 4, 5, 6, 7]
//
//        //将子集中的每个元素扩大10倍？
//        for(int i=0;i<subList.size();i++){
//            int n = subList.get(i);
//            n *= 10;
//            subList.set(i, n);
//            //合成一句
//            //subList.set(i,subList.get(i)*10);
//        }
//        System.out.println(subList);
//        /*
//         * 对子集的修改是在原集合的基础上进行的
//         * 对子集的任何修改都会直接反映到原集合的相应内容上。
//         */
//        System.out.println(list);  //[0, 1, 2, 30, 40, 50, 60, 70, 8, 9]
//
//        /*
//         * 应用：批量删除元素
//         * 删除集合中2-8的元素
//         */
//        list.subList(2, 9).clear();
//        System.out.println(list);
        int totlePage = getTotlePage(list.size(), 7);
        for(int i=1;i<totlePage+1;i++){
            Page<Integer> integers = CollerctionsUtilsBatch.collectToPage(list, i, 7);
            System.out.println(JSON.toJSONString(integers));

        }



    }


    public static int getTotlePage(int totle, int size) {
        Assert.state(size != 0, " Pagination 分页参数的 size 不能为0 ");
        int totalPage = ((totle - 1) + size) / size;
        return totalPage;
    }

    public static <T> Page<T> collectToPage(List<T> collection, int page, int size) {
        Page<T> pager = new Page<T>(page, size);
        if (CollectionUtils.isEmpty(collection)) {
            pager.close();
            return pager;
        }
        int totlePage = getTotlePage(collection.size(), size);
        if(totlePage==page){
            pager.addAll(collection.subList((page-1)*size,collection.size()));
        }else{
            pager.addAll(collection.subList((page-1)*size,page*size));
        }
        pager.setTotal(collection.size());
        pager.close();
        return pager;
    }

}