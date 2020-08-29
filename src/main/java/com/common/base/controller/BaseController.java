package com.common.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.base.entity.BaseEntity;
import com.common.base.entity.Message;
import com.common.base.entity.Result;
import com.common.base.service.BaseService;
import com.common.entity.User;
import com.common.util.StringUtils;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 通用Controller
 * 
 * @author NANOHA
 *
 * @param <T>
 */
@Slf4j
@RestController
public abstract class BaseController<T> {

	@Autowired
	private BaseService<T> baseService;

	/**
	 * get 查询
	 * 
	 * @param t
	 * @return
	 */
	@GetMapping("/all")
	public Result<List<T>> all(T t) {
		return Result.SUCCESS(baseService.queryAll());
	}

	/**
	 * get 查询
	 * 
	 * @param t
	 * @return
	 */
	@GetMapping("/list")
	public Result<List<T>> list(T t) {
		return Result.SUCCESS(baseService.queryList(t));
	}

	/**
	 * get 统一页面分页查询
	 * 
	 * @param currentPage 当前页数
	 * @param rows        每页行数
	 * @param t
	 * @return
	 */
	@GetMapping("/list/{pageNum}/{pageSize}")
	public Result<PageInfo<T>> list(@PathVariable int pageNum, @PathVariable int pageSize, T t) {
		return Result.SUCCESS(baseService.queryPage(pageNum, pageSize, t));
	}

	/**
	 * get 统一页面分页模糊查询
	 * 
	 * @param request
	 * @param response
	 * @param pageNum
	 * @param pageSize
	 * @param t
	 * @return
	 */
	@GetMapping("/list/{pageNum}/{pageSize}/{fuzzy}")
	public Result<PageInfo<T>> fuzzy(@PathVariable int pageNum, @PathVariable int pageSize, @PathVariable Integer fuzzy, T t) {
		if (fuzzy != null && fuzzy > 0) {
			return Result.SUCCESS(baseService.queryPageFuzzy(pageNum, pageSize, t));
		}
		return Result.SUCCESS(baseService.queryPage(pageNum, pageSize, t));
	}

	/**
	 * get 查询单个
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("one/{id}")
	public Result<T> getById(@PathVariable int id) {
		return Result.SUCCESS(baseService.queryById(id));
	}

	/**
	 * post 保存
	 * 
	 * @param id
	 * @param t
	 * @return
	 */
	@PostMapping("save")
	public Result<T> save(@RequestBody T t) {
		baseService.save(t);
		return Result.SAVE_SUCCESS();
	}

	/**
	 * post 保存或更新
	 * 
	 * @param id
	 * @param t
	 * @return
	 */
	@PostMapping("update/{id}")
	public Result<T> update(@PathVariable Integer id, @RequestBody T t) {
		((BaseEntity) t).setId(id);
		if (baseService.checkExistById(id)) {
			baseService.update(t);
		} else {
			return Result.FAIL(Message.DATA_NOT_EXIST);
		}
		return Result.UPDATE_SUCCESS();
	}

	/**
	 * 统一删除 批量
	 * 
	 * @param ids 格式 1,2,3
	 * @return
	 */
	@GetMapping("delete/{id}")
	public Result<?> delete(@PathVariable int[] id) {
		baseService.deleteByIds(id);
		return Result.DELETE_SUCCESS();
	}

	@GetMapping("exist")
	public Result<Boolean> selectExist(T t) {
		return Result.SUCCESS(baseService.checkExist(t));
	}

	@GetMapping("/state/{id}/{state}")
	public Result<T> updateState(@PathVariable T t, @PathVariable short state) throws Exception {
		if (t instanceof BaseEntity) {
			Integer id = ((BaseEntity) t).getId();
			if (id == null || id == 0) {
				return Result.FAIL(Message.ID_CHECK_FAIL);
			}
			if (baseService.updateState(t, state)) {
				return Result.SAVE_SUCCESS();
			} else {
				return Result.SAVE_FAIL();
			}
		} else {
			return Result.FAIL("不支持此对象");
		}
	}
}
