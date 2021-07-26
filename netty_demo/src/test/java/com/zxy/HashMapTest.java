package com.zxy;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    @Test
    public void testMap() {
        Map map = new HashMap();
        map.put("1","1");
        map.get("1");
    }
}
