package cn.edu.jxau.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: zark.zcl（思劼）
 * @date: 2018/11/30
 */
public class UserServiceTest {

    @org.junit.Test
    public void tets1() throws Exception {
        Map<String, String> extendInfo = new HashMap<>();
        Map<String, String> newextendInfo = null;
        extendInfo.put("a", "1");
        if (extendInfo instanceof Map) {
            String s = extendInfo.getClass().getName();
            newextendInfo = (Map<String, String>) Class.forName(s).newInstance();
            newextendInfo.putAll(extendInfo);
        }
        extendInfo.put("a", "2");
        System.out.println();

    }
}
