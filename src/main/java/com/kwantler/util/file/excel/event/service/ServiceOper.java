package com.kwantler.util.file.excel.event.service;

import java.util.Map;

public interface ServiceOper {
    public void handle(int curRow, Map<String, String> rowValueMap);
}
