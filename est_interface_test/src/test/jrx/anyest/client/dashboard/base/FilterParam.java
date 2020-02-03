package jrx.anyest.client.dashboard.base;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FilterParam {

    private String key;
    private String name;
    private String type;
    private String integeractionType;
    private String operator;
    private boolean cache;
    private Long expired;
    private Integer width;
    private Map<String,Map> relatedItems;
    private Map<String,Map> relatedViews;
    private boolean multiple;
    private boolean customOptions;
    private List<Map> options;




}
