package jrx.anyest.table;

/**
 * 随门户启动而启动的服务
 * @author JACK
 *
 */
public interface Service {

	/**
	 * 返回服务名称
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 返回服务排序
	 * 
	 * @return
	 */
	public int getOrder();

	/**
	 * 执行服务
	 */
	public void execute();
}
