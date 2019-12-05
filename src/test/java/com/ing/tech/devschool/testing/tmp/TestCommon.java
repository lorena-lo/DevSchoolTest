package com.ing.tech.devschool.testing.tmp;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TestCommon {
    private final HashMap<String, Object> testDataMap = new HashMap<>();

    public Object get(String key) {
        return testDataMap.get(key);
    }

    public Object put(String key, Object value) {
        return testDataMap.put(key, value);
    }

    public Object remove(String key) {
        return testDataMap.remove(key);
    }

    public void clean() {
        testDataMap.clear();
    }
}
