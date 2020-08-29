package com.common.base.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.base.entity.BaseEntity;
import com.common.base.mapper.BaseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 通用service
 * 
 * @author zhao_li
 * @ClassName: BaseService
 * @param <T>
 */
@Slf4j
@Service
@Transactional
public abstract class BaseService<T> {

	@Autowired
	public BaseMapper<T> baseMapper;

	/**
	 * 根据主键查询数据
	 * 
	 * @param id
	 * @return
	 */
	public T queryById(int id) {
		return baseMapper.queryById(id);
	}

	/**
	 * 查询启用的数据
	 * 
	 * @return
	 */
	public List<T> queryListEnable() {
		return baseMapper.selectListEnable();
	}

	/**
	 * 根据条件查询，多条件之间是 and 关系
	 * 
	 * @param t
	 * @return
	 */
	public List<T> queryList(T t) {
		return baseMapper.queryList(t);
	}

	/**
	 * 根据条件模糊查询，多条件之间是 and 关系
	 * 
	 * @param t
	 * @return
	 */
	public List<T> queryListFuzzy(T t) {
		return baseMapper.selectFuzzy(t);
	}

	/**
	 * 根据条件查询单条数据
	 * 
	 * @param t
	 * @return
	 */
	public T query(T t) {
		return baseMapper.query(t);
	}

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public List<T> queryAll() {
		return baseMapper.queryAll();
	}

	/**
	 * 新增数据，使用全部字段
	 * 
	 * @param t
	 */
	public int saveFull(T t) {
		return baseMapper.saveFull(t);
	}

	/**
	 * 新增数据，使用不为null的字段
	 * 
	 * @param t
	 * @return
	 */
	public int save(T t) {
		return baseMapper.save(t);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(int id) {
		return baseMapper.deleteById(id);
	}

	/**
	 * 根据ids删除
	 * 
	 * @param ids
	 * @return
	 */
	public int deleteByIds(int... id) {
		return baseMapper.deleteByIds(id);
	}

	/**
	 * 根据条件删除
	 * 
	 * @param t
	 */
	public int delete(T t) {
		return baseMapper.delete(t);
	}

	/**
	 * 根据主键id更新数据
	 * 
	 * @param t
	 */
	public int updateFull(T t) {
		return baseMapper.updateFull(t);
	}

	/**
	 * 根据主键id更新数据
	 * 
	 * @param t
	 */
	public int update(T t) {
		return baseMapper.update(t);
	}

	/**
	 * 执行sql
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> execute(String sql) {
		return baseMapper.executeSql(sql);
	}

	/**
	 * 查询分页 精确查找
	 * 
	 * @param page
	 * @param rows
	 * @param t
	 * @return
	 */
	public PageInfo<T> queryPage(int pageNum, int pageSize, T t) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<T>(baseMapper.select(t));
	}

	/**
	 * 查询分页 模糊查询
	 * 
	 * @param page
	 * @param rows
	 * @param t
	 * @return
	 */
	public PageInfo<T> queryPageFuzzy(int pageNum, int pageSize, T t) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<T>(baseMapper.selectFuzzy(t));
	}

	/**
	 * 查询限定条数
	 * 
	 * @param t
	 * @param limit
	 * @return
	 */
	public List<T> queryLimit(T t, int limit) {
		PageHelper.startPage(0, limit);
		return baseMapper.select(t);
	}

	/**
	 * 查询限定条数
	 * 
	 * @param t
	 * @param limit
	 * @return
	 */
	public List<T> queryLimit(T t, int start, int limit) {
		PageHelper.startPage(start, limit);
		return baseMapper.select(t);
	}

	public boolean updateState(T t, short flag) throws Exception {
		T update = null;
		try {
			update = (T) t.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		((BaseEntity) update).setId(((BaseEntity) t).getId());
		((BaseEntity) update).setStatus(flag);

		int row = baseMapper.updateByPrimaryKeySelective(update);
		if (row == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkExistById(int id) {
		return queryById(id) != null ? true : false;
	}

	public boolean checkExist(T t) {
		if (baseMapper.selectCount(t) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取泛型的class
	 */
	@SuppressWarnings("unchecked")
	private Class<T> getTClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
