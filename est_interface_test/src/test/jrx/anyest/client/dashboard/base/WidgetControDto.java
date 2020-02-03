package jrx.anyest.client.dashboard.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yxy
 * @date 2018/12/4
 */
@Getter
@Setter
public class WidgetControDto {

    private Integer widgetId;
    private Integer topicInfoId;
    private ObjectType objectType;
    private List<String> columns;
    private String table;
}


