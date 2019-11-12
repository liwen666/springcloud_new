import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamUtil {
 
	/**
	 * 将bean转为xml
	 * 
	 * @param obj
	 * @return
	 */
	public static String ObjectToXML(Object obj) {
		XStream xStream = new XStream();
		// xstream使用注解转换
		xStream.processAnnotations(obj.getClass());
		return xStream.toXML(obj);
	}
 
	/**
	 * 将xml转换为bean
	 * 
	 * @param xml
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlToObject(String xml, Class<T> clazz) {
		XStream xStream = new XStream(new DomDriver());
		xStream.processAnnotations(clazz);
		return (T) xStream.fromXML(xml);
	}
}