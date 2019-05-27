//import com.tjhq.portal.data.biz.impl.PortalBiz;
//import com.tjhq.portal.data.entity.Portal;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class PortlalBizTest extends BaseTest {
//
//	@Autowired
//	private PortalBiz portalBiz;
//
//	@Test
//	public void testCreate() {
//		Portal portal = portalBiz.getEntityByJpql("from Portal b where b.id=?0", "thisisademoportalfortest");
//		if (portal == null) {
//			portal = new Portal();
//			portal.setId("thisisademoportalfortest");
//			portal.setTitle("门户测试页面");
//			portal.setFitMode("both");
//			portal.setWidth(1500);
//			portal.setHeight(1200);
//			portalBiz.createEntity(portal);
//		}
//	}
//
//	@Test
//	public void testDelete() {
//
//	}
//}
