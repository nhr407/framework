package priv.zhouhuayi.framework.dao.common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import priv.zhouhuayi.framework.mapper.common.CommonMapper;
import priv.zhouhuayi.framework.util.bean.BeanUtil;
import priv.zhouhuayi.framework.util.pojo.Params;
import priv.zhouhuayi.framework.util.sql.SqlUtil;

/**
 * 类名称：通用DAO实现层 
 * 描述：通用数据处理层 
 * 创建人：周化益 
 * 创建时间：2015-08-05
 */
@Repository("commonDao")
public class CommonDao<T> {
	@Resource
	private CommonMapper commonMapper;
	
	/**
	 * 增删改SQL操作
	 * 
	 * @param sql 操作的sql语句
	 * @return
	 */
	public long executeAction(String sql) {
		return commonMapper.executeAction(sql);
	}

	/**
	 * 查询单条语句
	 * 
	 * @param sql 操作的sql语句
	 * @return
	 */
	public Map<String, Object> findOneData(String sql) {
		return commonMapper.findOneData(sql);
	}

	/**
	 * 查询多条语句
	 * 
	 * @param sql 操作的sql语句
	 * @return
	 */
	public List<Map<String, Object>> findManyData(String sql) {
		return commonMapper.findManyData(sql);
	}

	/**
	 * 查找数量
	 * 
	 * @author zhy
	 * @param sql 查询的sql 语句
	 * @return
	 */
	public long findCount(String sql) {
		return commonMapper.findCount(sql);
	}
	
	/**
	 * 查询单个
	 * 
	 * @author zhy
	 * @param sql 查询的sql 语句
	 * @return
	 */
	public Object findOneValue(String sql) {
		return commonMapper.findOneValue(sql);
	}
	
	/**
	 * 查询数字集合
	 * 
	 * @param sql
	 * @return
	 */
	public List<Long> findListBySql(String sql) {
		return commonMapper.findListBySql(sql);
	}
	
	/**
	 * 查询数字集合
	 * 
	 * @param sql
	 * @return
	 */
	public List<Object> findListObjBySql(String sql) {
		return commonMapper.findListObjBySql(sql);
	}
	
	/**
	 * 添加实体
	 * 
	 * @author 周化益
	 * @param entityName 实体Class
	 * @param addData 添加的数据
	 * @return 主键ID
	 */
	public Long addClass(Class<T> entityName, Map<String, Object> addData) {
		Params params = new Params();
		params.setTables(BeanUtil.getTableName(entityName));
		params.setInsertMap(addData);
		commonMapper.addEntity(params);
		return params.getId();
	}
	
	/**
	 * 批量添加数据
	 * 
	 * @author zhy
	 * @param entityName 实体Class
	 * @param listMap	批量数据集合
	 * @return
	 */
	public int batchAdd(Class<T> entityName, List<Map<String, Object>> listMap) {
		Params params = new Params();
		params.setTables(BeanUtil.getTableName(entityName));
		params.setInsertMap(listMap.get(0));
		params.setBacthInsertMap(listMap);
		return commonMapper.batchAdd(params);
	}
	
	/**
	 * 通过条件修改实体
	 * 
	 * @author 周化益
	 * @param entityName 实体Class
	 * @param updataData 修改数据
	 * @param whereSql 条件语句
	 * @return 成功或失败
	 */
	public boolean updateByWhere(Class<T> entityName, Map<String , Object> updateData, String whereSql){
		boolean bool = false;
		StringBuffer sb = new StringBuffer("update ");
		sb.append(BeanUtil.getTableName(entityName)).append(" set ");
		Iterator<String> it = updateData.keySet().iterator();
		StringBuffer updateBuffer = new StringBuffer();
		
		while (it.hasNext()) {
			String key = it.next();
			if(updateData.get(key) == null) {
				updateBuffer.append(key).append('=').append("null").append(',');
			} else{
				updateBuffer.append(key).append('=').append(':'+key).append(',');
			}
		}
		
		sb.append(updateBuffer.substring(0, updateBuffer.length() - 1)).append(whereSql);
		bool = commonMapper.executeAction(SqlUtil.sqlAppend(sb.toString(), updateData)) > 0;
		return bool;
	}
	
	/**
	 * 通过ID删除数据
	 * 
	 * @author zhy
	 * @param entityName 实体Class
	 * @param id 删除的ID
	 * @return
	 */
	public long deleteById(Class<T> entityName, Object id) {
		String sql = "delete from " + BeanUtil.getTableName(entityName) + " where id = " + SqlUtil.validateValue(id.toString());
		return commonMapper.executeAction(sql);
	}
	
	/**
	 * 通过实体class获取实体列表数据（多条无条件）
	 * 
	 * @author zhy
	 * @param clazz 实体Class
	 * @return
	 */
	public List<Map<String, Object>> getListByBean(Class<?> clazz) {
		StringBuffer sql = new StringBuffer();
		//查询部分
		sql.append("SELECT ").append(BeanUtil.getTableColumnsByFind(clazz)).append(" FROM ").append(BeanUtil.getTableName(clazz));
		
		return commonMapper.findManyData(sql.toString());
	}
	
	/**
	 * 通过实体class获取实体数据（单条无条件）
	 * 
	 * @author zhy
	 * @param clazz 实体Class
	 * @return
	 */
	public Map<String, Object> getByBean(Class<?> clazz) {
		StringBuffer sql = new StringBuffer();
		//查询部分
		sql.append("SELECT ").append(BeanUtil.getTableColumnsByFind(clazz)).append(" FROM ").append(BeanUtil.getTableName(clazz));
		
		return commonMapper.findOneData(sql.toString());
	}
	
	/**
	 * 通过实体class获取实体数据
	 * 
	 * @author zhy
	 * @param clazz 实体Class
	 * @param whereSql 查询条件
	 * @return
	 */
	public Map<String, Object> getBean(Class<?> clazz, String whereSql) {
		StringBuffer sql = new StringBuffer();
		//查询部分
		sql.append("SELECT ").append(BeanUtil.getTableColumnsByFind(clazz)).append(" FROM ").append(BeanUtil.getTableName(clazz));
		//条件
		sql.append(' ').append(whereSql);
		
		return commonMapper.findOneData(sql.toString());
	}
	
	/**
	 * 通过实体class获取实体列表数据
	 * 
	 * @author zhy
	 * @param clazz 实体Class
	 * @param whereSql 查询条件
	 * @return
	 */
	public List<Map<String, Object>> getListByBean(Class<?> clazz, String whereSql) {
		StringBuffer sql = new StringBuffer();
		//查询部分
		sql.append("SELECT ").append(BeanUtil.getTableColumnsByFind(clazz)).append(" FROM ").append(BeanUtil.getTableName(clazz));
		//条件
		sql.append(' ').append(whereSql);
		
		return commonMapper.findManyData(sql.toString());
	}
	
	/**
	 * 通过实体class获取实体列表数据
	 * 
	 * @author zhy
	 * @param clazz 实体Class
	 * @param whereSql 查询条件
	 * @param page 开始页
	 * @param rows 查询的条数
	 * @return
	 */
	public List<Map<String, Object>> getListByBean(Class<?> clazz, String whereSql, long page, long rows) {
		StringBuffer sql = new StringBuffer();
		//查询部分
		sql.append("SELECT ").append(BeanUtil.getTableColumnsByFind(clazz)).append(" FROM ").append(BeanUtil.getTableName(clazz));
		//条件
		sql.append(' ').append(whereSql);
		//分页
		sql.append(" limit ").append((page - 1) * rows).append(',').append(rows);
		
		return commonMapper.findManyData(sql.toString());
	}
	
	/**
	 * 通过实体class获取实体列表数据
	 * 
	 * @author zhy
	 * @param clazz 实体Class
	 * @param whereSql 查询条件
	 * @param page 开始页
	 * @param rows 查询的条数
	 * @param sortColumn 排序字段
	 * @param sort 排序方式
	 * @return
	 */
	public List<Map<String, Object>> getListByBean(Class<?> clazz, String whereSql, long page, long rows, String sortColumn, String sort) {
		StringBuffer sql = new StringBuffer();
		//查询部分
		sql.append("SELECT ").append(BeanUtil.getTableColumnsByFind(clazz)).append(" FROM ").append(BeanUtil.getTableName(clazz));
		//条件
		sql.append(' ').append(whereSql);
		//排序
		sql.append(" ORDER BY ").append(sortColumn).append(' ').append(sort);
		//分页
		sql.append(" LIMIT ").append((page - 1) * rows).append(',').append(rows);
		
		return commonMapper.findManyData(sql.toString());
	}
	
	/**
	 * 通过实体class获取实体列表数据
	 * 
	 * @author zhy
	 * @param clazz 实体Class
	 * @param page 开始页
	 * @param rows 查询的条数
	 * @return
	 */
	public List<Map<String, Object>> getListByBean(Class<?> clazz, long page, long rows) {
		StringBuffer sql = new StringBuffer();
		//查询部分
		sql.append("SELECT ").append(BeanUtil.getTableColumnsByFind(clazz)).append(" FROM ").append(BeanUtil.getTableName(clazz));
		//分页
		sql.append(" limit ").append((page - 1) * rows).append(',').append(rows);
		
		return commonMapper.findManyData(sql.toString());
	}
	
	/**
	 * 通过实体class获取实体列表数据
	 * 
	 * @author zhy
	 * @param clazz 实体Class
	 * @param whereSql 查询条件
	 * @param page 开始页
	 * @param rows 查询的条数
	 * @param sortColumn 排序字段
	 * @param sort 排序方式
	 * @return
	 */
	public List<Map<String, Object>> getListByBean(Class<?> clazz, long page, long rows, String sortColumn, String sort) {
		StringBuffer sql = new StringBuffer();
		//查询部分
		sql.append("SELECT ").append(BeanUtil.getTableColumnsByFind(clazz)).append(" FROM ").append(BeanUtil.getTableName(clazz));
		//排序
		sql.append(" ORDER BY ").append(sortColumn).append(' ').append(sort);
		//分页
		sql.append(" LIMIT ").append((page - 1) * rows).append(',').append(rows);
		
		return commonMapper.findManyData(sql.toString());
	}
	
	/**
	 * 通过实体class获取实体列表数据
	 * 
	 * @author zhy
	 * @param sqlStr 自己写的SQL语句
	 * @param whereSql 查询条件
	 * @param page 开始页
	 * @param rows 查询的条数
	 * @return
	 */
	public List<Map<String, Object>> getListByBean(String sqlStr, String whereSql, long page, long rows) {
		StringBuffer sql = new StringBuffer();
		//查询部分
		sql.append(sqlStr);
		//条件
		sql.append(' ').append(whereSql);
		//分页
		sql.append(" limit ").append((page - 1) * rows).append(',').append(rows);
		return commonMapper.findManyData(sql.toString());
	}
	
	/**
	 * 通过实体class获取实体列表数据
	 * 
	 * @author zhy
	 * @param sqlStr 自己写的SQL语句
	 * @param whereSql 查询条件
	 * @param page 开始页
	 * @param rows 查询的条数
	 * @param sortColumn 排序字段
	 * @param sort 排序方式
	 * @return
	 */
	public List<Map<String, Object>> getListByBean(String sqlStr, String whereSql, long page, long rows, String sortColumn, String sort) {
		StringBuffer sql = new StringBuffer();
		//查询部分
		sql.append(sqlStr);
		//条件
		sql.append(' ').append(whereSql);
		//排序
		sql.append(" ORDER BY ").append(sortColumn).append(' ').append(sort);
		//分页
		sql.append(" LIMIT ").append((page - 1) * rows).append(',').append(rows);
		
		return commonMapper.findManyData(sql.toString());
	}
	
	/**
	 * 通过实体class获取实体列表数据
	 * 
	 * @author zhy
	 * @param sqlStr 自己写的SQL语句
	 * @param page 开始页
	 * @param rows 查询的条数
	 * @return
	 */
	public List<Map<String, Object>> getListByBean(String sqlStr, long page, long rows) {
		StringBuffer sql = new StringBuffer();
		//查询部分
		sql.append(sqlStr);
		//分页
		sql.append(" limit ").append((page - 1) * rows).append(',').append(rows);
		
		return commonMapper.findManyData(sql.toString());
	}
	
	/**
	 * 批量删除
	 * 
	 * @author zhy
	 * @param clazz 实体Class
	 * @param deleteList 删除的集合
	 * @param deleteColumnName 批量删除条件字段
	 * @return
	 */
	public int batchDelete(Class<?> clazz, List<?> deleteList, String deleteColumnName) {
		Params params = new Params();
		params.setDeleteCoulumnName(deleteColumnName);
		params.setDeleteList(deleteList);
		params.setTables(BeanUtil.getTableName(clazz));
		
		return commonMapper.batchDelete(params);
	}
}
