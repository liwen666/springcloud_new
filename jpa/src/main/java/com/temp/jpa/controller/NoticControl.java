package com.temp.jpa.controller;

import com.temp.jpa.jpa.biz.impl.NoticBiz;
import com.temp.jpa.jpa.dto.TreeNode;
import com.temp.jpa.jpa.entity.Notic;
import com.temp.jpa.utils.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("notic")
public class NoticControl extends BaseControl<NoticBiz, Notic> {
	Logger logger = LoggerFactory.getLogger(NoticControl.class);

	private NoticBiz noticBiz;


	@RequestMapping(value = "/tree")
	@ResponseBody
	public ResponseEntity<List<TreeNode>> tree() {
		List<TreeNode> tree = new ArrayList<>();
		List<Notic> busisyses = noticBiz.getEntitiesByJpql("from Busisys b order by b.orderNO asc");
		for (Notic busisys : busisyses) {
			TreeNode node = new TreeNode();
			node.setId(busisys.getId());
			node.setData(busisys);
			node.setText(busisys.getTitle());
			node.setLeaf(false);
//			List<Portlet> portlets = busisys.getPortlets();
//			List<TreeNode> children = new ArrayList<>();
//			for (Portlet portlet : portlets) {
//				TreeNode child = new TreeNode();
//				child.setId(portlet.getId());
//				child.setData(portlet);
//				child.setText(portlet.getName());
//				child.setLeaf(true);
//				children.add(child);
//			}
//			node.setChildren(children);
			tree.add(node);
		}
		return ResponseEntity.ok(tree);
	}


	@PostMapping(value = "/insert")
	@ResponseBody
	public ResponseEntity<String> insert(HttpServletRequest request) {
		Notic notic = HttpRequestUtils.coversion(request, Notic.class);
		 noticBiz.createEntity(notic);
		return ResponseEntity.ok("SUCCESS");
	}
	@PostMapping(value = "/update")
	@ResponseBody
	public ResponseEntity<Notic> update(@RequestParam("noticId")String noticId) {
		Notic notic = noticBiz.readEntity(noticId);
		return ResponseEntity.ok(notic);
	}

	/**
     * 根据用
	 * @param userId
     * @return
     */
	@RequestMapping(value = "/selectNoticById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Notic>> select(@RequestParam("userId") String userId,@RequestParam("noticType") String noticType) {
		logger.debug("用户消息查询--->",userId);
		List<Notic> notics = noticBiz.listEntities();
		System.out.println(ResponseEntity.ok(notics));
		return ResponseEntity.ok(notics);
	}
	@RequestMapping(value = "/listCommonList", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Notic>> listCommonList() {
		List<Notic> notics = noticBiz.listEntities();
		System.out.println(ResponseEntity.ok(notics));
		return ResponseEntity.ok(notics);
	}

}
