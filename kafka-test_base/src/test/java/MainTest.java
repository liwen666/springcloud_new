import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

	@Test
	public void testFoo() {
		System.out.println("hello word");
	}

	@Test
	public void name() {

		System.out.println(9999);

		String deleteSqlString = getDeleteSqlString("db_", "table", new ArrayList<String>() {{
			add("jjjjjj");
			add("pppppp");
		}});
		System.out.println(deleteSqlString);

		System.out.println(deleteByIn("db_", "table", "field",new ArrayList<String>() {{
			add("jjjjjj");
			add("pppppp");
		}}));

	}

	private String getDeleteSqlString(String dbCode, String tableCode, List<String> deleteSqls) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("delete from "+dbCode+"."+tableCode );
		if(CollectionUtils.isEmpty(deleteSqls)){
			return null;
		}
		stringBuffer.append("  where ");
		int end = 0;
		for(String sql :deleteSqls){
			if(end==deleteSqls.size()-1){
				stringBuffer.append(sql);
				continue;
			}
			end++;
			stringBuffer.append(sql+" OR ");
		}
		return stringBuffer.toString();
	}

	@Test
	public void TEST1() {
		String sql ="delete from ods_db_anytask_cm_app_case where 1=1 and case_id='23bce253-758c-11ea-b' and casetype='4110' or case_id='23bce253-758c-11ea-b' and casetype='4110'";
		String where = sql.substring(sql.indexOf("where")+5);
		System.out.println(where);


	}

	private String deleteByIn( String dbCode, String tableCode,String field, List deleteSqls) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("delete from " + dbCode + "." + tableCode);
		if (CollectionUtils.isEmpty(deleteSqls)) {
			return null;
		}
		stringBuffer.append("  where field in( ");
		int end = 0;
		for (Object value : deleteSqls) {
			if(value == null ){
				continue;
			}else if(value instanceof Long || value instanceof Integer
					|| value instanceof Double|| value instanceof BigDecimal || value instanceof Float
			){
				stringBuffer.append(value);
			}else if(value instanceof String) {
				stringBuffer.append("'").append(value).append("' ");
			}else{
				stringBuffer.append("'").append(value).append("' ");
			}
			if (end == deleteSqls.size() - 1) {
				continue;
			}
			stringBuffer.append(",");
			end++;
		}
		stringBuffer.append( ")");
		return stringBuffer.toString();
	}

}
