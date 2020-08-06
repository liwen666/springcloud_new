package jrx.anyest.table.jpa.dto;

import com.google.common.collect.Lists;
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
  * @since  2020/8/5 16:16
  */
 @Getter
 @Setter
public class ImportDataResult {
     /**
      * 导入结果
      */
    private boolean result=true;
     /**
      * 导入情况明细
      */
    private List<ImportData> importData= Lists.newArrayList();

}
