package jrx.anyest.client.dashboard.base;

import com.alibaba.fastjson.JSON;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class Test
{


    /**
     * {
     * 	"filters": [{
     * 		"key": "817AF140",
     * 		"name": "下拉框",
     * 		"type": "select",
     * 		"interactionType": "column",
     * 		"operator": "=",
     * 		"cache": true,
     * 		"expired": 300,
     * 		"width": 0,
     * 		"relatedItems": {
     * 			" 146": {
     * 				"viewId": 139,
     * 				"checked": true
     *                        }* 		},
     * 		"relatedViews": {
     * 			"139": {
     * 				"name": " username",
     * 				"type": "VARCHAR"
     *            }
     *        },
     * 		"multiple": false,
     * 		"customOptions": false,
     * 		"options": [{
     * 			"text": "102",
     * 			"value": "102"
     *        }]
     *    }],
     * 	"queryMode": 0
     * }
     */
    @org.junit.Test
    public void name() {
        String filter = "{\"filters\":[{\"key\":\"817AF140\",\"name\":\"下拉框\",\"type\":\"select\",\"interactionType\":\"column\",\"operator\":\"=\",\"cache\":true,\"expired\":300,\"width\":0,\"relatedItems\":{\" 146\":{\"viewId\":139,\"checked\":true}},\"relatedViews\":{\"139\":{\"name\":\" username\",\"type\":\"VARCHAR\"}},\"multiple\":false,\"customOptions\":false,\"options\":[{\"text\":\"102\",\"value\":\"102\"}]}],\"queryMode\":0}";
        DashWidgetControDto dashWidgetControDto = JSON.parseObject(filter, DashWidgetControDto.class);

        System.out.println();
    }
}
