package com.temp.jpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.temp.jpa.jpa.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器基类
 * 
 * @param <T>
 * 
 * @param <T>
 *
 */
@SuppressWarnings("rawtypes")
public class BaseControl<Biz extends BaseBiz, T> {

	@Autowired
	protected Biz baseBiz;

	@Autowired
	protected ObjectMapper objectMapper;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<T> add(@RequestBody T entity) {
		baseBiz.createEntity(entity);
		return ResponseEntity.ok().body(entity);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/entity/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<T> get(@PathVariable("id") String id) {
		T entity = (T) baseBiz.readEntity(id);
		return ResponseEntity.ok(entity);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/entity/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<T> update(@RequestBody T entity) {
		baseBiz.updateEntity(entity);
		return ResponseEntity.ok(entity);
	}

	@RequestMapping(value = "/entity/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Boolean> remove(@PathVariable("id") String id) {
		baseBiz.deleteEntity(id);
		return ResponseEntity.ok(true);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/entity/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<T>> list() {
		List<T> entities = baseBiz.listEntities();
		return ResponseEntity.ok(entities);
	}
	
	protected Map<String, Object> getResult(boolean success,Object data){
		Map<String, Object> result=new HashMap<>();
		result.put("success", success);
		result.put("data", data);
		return result;
	}
}
