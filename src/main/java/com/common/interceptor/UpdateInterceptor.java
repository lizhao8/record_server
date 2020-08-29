package com.common.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import com.common.base.entity.Base;

@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class UpdateInterceptor implements Interceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

		// if ("update".equals(methodName)) {
		Object object = invocation.getArgs()[1];
		if (object instanceof Base) {
			// 对有要求的字段填值
			if (SqlCommandType.INSERT.equals(sqlCommandType)) {
				((Base) object).setInsertId(1);
			} else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
				((Base) object).setUpdateId(1);
			}
		}
		// }
		return invocation.proceed();
	}
}
