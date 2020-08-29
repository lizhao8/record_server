package com.common.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper
 * 
 * @author Administrator
 *
 * @param <T>
 */
@Component
@Repository
@RegisterMapper
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
	/**
	 * 根据主键查询数据
	 * 
	 * @param id
	 * @return
	 */
	public default T queryById(int id) {
		return this.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据条件查询，多条件之间是 and 关系
	 * 
	 * @param t
	 * @return
	 */
	public default List<T> queryList(T t) {
		return this.select(t);
	}
	
	/**
	 * 根据条件查询单条数据
	 * 
	 * @param t
	 * @return
	 */	
	public default T query(T t) {
		return this.selectOne(t);
	}


	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public default List<T> queryAll() {
		return this.selectAll();
	}
	
	/**
	 * 新增数据，使用全部字段
	 * 
	 * @param t
	 */
	public default int saveFull(T t) {
		return this.insert(t);
	}

	/**
	 * 新增数据，使用不为null的字段
	 * 
	 * @param t
	 * @return
	 */
	public default int save(T t) {
		return this.insertSelective(t);
	}
	
	
	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	public default int deleteById(int id) {
		return this.deleteByPrimaryKey(id);
	}

	
	/**
	 * 根据主键id更新数据	
	 * 
	 * @param t
	 */
	public default int updateFull(T t) {
		return this.updateByPrimaryKey(t);
	}

	/**
	 * 根据主键id更新数据
	 * 
	 * @param t
	 */
	public default int update(T t) {
		return this.updateByPrimaryKeySelective(t);
	}
		
	
	
	
	/**
	 * 根据主键ID批量删除
	 * 
	 * @param key
	 * @return
	 */
	@DeleteProvider(type = BaseMapperProvider.class, method = "dynamicSQL")
	int deleteByIds(Object... id);

	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件，模糊查询
	 * 
	 * @param record
	 * @return
	 */
	@SelectProvider(type = BaseMapperProvider.class, method = "dynamicSQL")
	List<T> selectFuzzy(T t);

	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件，模糊查询
	 * 
	 * @param record
	 * @return
	 */
	@SelectProvider(type = BaseMapperProvider.class, method = "dynamicSQL")
	List<T> selectListEnable();
	
	List<Map<String, Object>> executeSql(String sql);

}
