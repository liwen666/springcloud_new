package jrx.data.hub.domain.enums;

public enum DataImportExportStatus {
    SUCCESS("成功"),
    FAIL("失败");

    private String desc;

    DataImportExportStatus(String desc) {
        this.desc = desc;
    }
}
