
package jrx.data.hub.domin.vo;

import jrx.anyest.table.jpa.dto.ImportDataResult;
import jrx.data.hub.domain.enums.DataImportExportStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/8/12 16:55
 */
@Getter
@Setter
public class ImportDataResultEast {
    private DataImportExportStatus dataImportExportStatus;
    private ImportDataResult importDataResult;
    private String  msg;

    public ImportDataResultEast() {
    }
}
