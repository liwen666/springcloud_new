package jrx.data.hub.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author Songyc5
 * @date: 2020/11/25
 */
@Getter
@Setter
public class ExecuteTableData {

//    private String tableName;

    private String[] columns;

    private List<Map<String, Object>> data;

}
