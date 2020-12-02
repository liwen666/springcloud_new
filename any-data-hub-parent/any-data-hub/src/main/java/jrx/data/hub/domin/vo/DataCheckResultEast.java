
package jrx.data.hub.domin.vo;

import com.google.common.collect.Maps;
import jrx.anyest.table.jpa.dto.DataCheckResult;
import jrx.data.hub.domain.enums.DataDetail;
import jrx.data.hub.domain.enums.DataImportExportStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/12/2 11:02
 */

@Getter
@Setter
public class DataCheckResultEast {
    private DataImportExportStatus dataImportExportStatus;
    private String errorMsg;
    private DataCheckResult dataCheckResult;
    /**
     * 导入导出数据明细
     */
    private Map<DataDetail, List<? extends BaseDetailInfo>> detailMap = Maps.newConcurrentMap();

    /**
     * 数据包唯一检验码
     */
    private String sign;
    /**
     * 缓存数据Key
     */
    private String dataKey;

    public DataCheckResultEast() {
    }
}
