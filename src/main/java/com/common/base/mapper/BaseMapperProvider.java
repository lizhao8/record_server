package com.common.base.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.reflection.MetaObject;

import com.common.base.annotation.Fuzzy;
import com.common.base.entity.BaseEntity;
import com.common.util.ReflectionUtils;

import tk.mybatis.mapper.annotation.Version;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.util.MetaObjectUtil;

/**
 * mapper sql拼接
 * 
 * @author Administrator
 *
 */
public class BaseMapperProvider extends MapperTemplate {

	public BaseMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	/**
	 * 通过id数组批量删除
	 * 
	 * @param ms
	 * @return
	 */
	public String deleteByIds(MappedStatement ms) {
		final Class<?> entityClass = getEntityClass(ms);
		StringBuilder sql = new StringBuilder();
		if (SqlHelper.hasLogicDeleteColumn(entityClass)) {
			sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
			sql.append("<set>");
			sql.append(SqlHelper.logicDeleteColumnEqualsValue(entityClass, true));
			sql.append("</set>");
			MetaObjectUtil.forObject(ms).setValue("sqlCommandType", SqlCommandType.UPDATE);
		} else {
			sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
		}

		/*
		 * // 一般以第一个找到的主键（一般是id）为条件 Set<EntityColumn> entityColumns =
		 * EntityHelper.getPKColumns(entityClass); EntityColumn column =
		 * entityColumns.iterator().next(); List<SqlNode> sqlNodes = new
		 * ArrayList<SqlNode>(); // 静态SQL部分 sqlNodes.add(new StaticTextSqlNode(sql +
		 * " WHERE " + column.getColumn() + " IN ")); // 构造foreach sql SqlNode foreach =
		 * new ForEachSqlNode(ms.getConfiguration(), new StaticTextSqlNode("#{" +
		 * column.getProperty() + "}"), "ids", "index", column.getProperty(), "(", ")",
		 * ","); sqlNodes.add(foreach); DynamicContext context = new
		 * DynamicContext(ms.getConfiguration(), null); new
		 * MixedSqlNode(sqlNodes).apply(context); sql.append(context);
		 */
		return sql.toString();
	}

	/**
	 * 字符串模糊查询实现
	 * 
	 * @param ms
	 * @return
	 */

	public String selectFuzzy(MappedStatement ms) {
		Class<?> entityClass = getEntityClass(ms);
		// 修改返回值类型为实体类型
		setResultType(ms, entityClass);
		StringBuilder sql = new StringBuilder();
		sql.append(SqlHelper.selectAllColumns(entityClass));
		sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
		sql.append(SqlHelper.whereAllIfColumns(entityClass, isNotEmpty()));
		sql.append(SqlHelper.orderByDefault(entityClass));
		return sql.toString();
	}

	/**
	 * where所有列的条件，会判断是否!=null
	 *
	 * @param entityClass
	 * @param empty
	 * @param useVersion
	 * @return
	 */
	public static String whereAllIfColumns(Class<?> entityClass, boolean empty, boolean useVersion) {
		StringBuilder sql = new StringBuilder();
		boolean hasLogicDelete = false;

		sql.append("<where>");
		// 获取全部列
		Set<EntityColumn> columnSet = EntityHelper.getColumns(entityClass);
		// 获取所有标记了模糊查询注解的属性
		List<Field> fieldList = ReflectionUtils.getAnnotationField(entityClass, Fuzzy.class);
		EntityColumn logicDeleteColumn = SqlHelper.getLogicDeleteColumn(entityClass);
		// 当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
		for (EntityColumn column : columnSet) {
			if (!useVersion || !column.getEntityField().isAnnotationPresent(Version.class)) {
				// 逻辑删除，后面拼接逻辑删除字段的未删除条件
				if (logicDeleteColumn != null && logicDeleteColumn == column) {
					hasLogicDelete = true;
					continue;
				}
				sql.append(SqlHelper.getIfNotNull(column, " AND " + getColumnEqualsHolderFuzzy(column, fieldList), empty));
			}
		}
		if (useVersion) {
			sql.append(SqlHelper.whereVersion(entityClass));
		}
		if (hasLogicDelete) {
			sql.append(SqlHelper.whereLogicDelete(entityClass, false));
		}

		sql.append("</where>");
		return sql.toString();
	}

	/**
	 * 模糊查询实现
	 * 
	 * @param column
	 * @param fieldList
	 * @return
	 */
	private static String getColumnEqualsHolderFuzzy(EntityColumn column, List<Field> fieldList) {
		String property = column.getProperty();
		for (Field field : fieldList) {
			if (property.equals(field.getName())) { // 匹配到模糊查询注解属性
				return column.getColumn() + " like '%" + column.getColumnHolder(null) + "%'";
			}
		}
		return column.getColumnEqualsHolder(null);
	}

	/**
	 * 查询全部结果已启用
	 *
	 * @param ms
	 * @return
	 */
	public String selectListEnable(MappedStatement ms) {
		final Class<?> entityClass = getEntityClass(ms);
		// 修改返回值类型为实体类型
		setResultType(ms, entityClass);
		StringBuilder sql = new StringBuilder();
		sql.append(SqlHelper.selectAllColumns(entityClass));
		sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));

		// 逻辑删除的未删除查询条件
		sql.append("<where>");
		if (BaseEntity.class.isAssignableFrom(entityClass)) {
			sql.append(BaseEntity.SELECT_ENABLE);
		}
		sql.append(SqlHelper.whereLogicDelete(entityClass, false));
		sql.append("</where>");

		sql.append(SqlHelper.orderByDefault(entityClass));
		return sql.toString();
	}

}
