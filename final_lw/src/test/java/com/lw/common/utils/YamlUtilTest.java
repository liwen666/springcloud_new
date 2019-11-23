package com.lw.common.utils;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.Assert.*;

public class YamlUtilTest {

    @Test
    public void name() throws FileNotFoundException {

        Map<?, ?> map = YamlUtil.loadYaml("application.yml");
        System.out.println(map);

    }
}