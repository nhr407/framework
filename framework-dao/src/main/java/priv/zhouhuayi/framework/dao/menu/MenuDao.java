package priv.zhouhuayi.framework.dao.menu;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import priv.zhouhuayi.framework.dao.common.CommonDao;
import priv.zhouhuayi.framework.entity.menu.Menu;

@Repository("menuDao")
public class MenuDao extends CommonDao<Menu> {
	private Class<Menu> clazz = Menu.class;
	
	/**
	 * 添加菜单
	 * 
	 * @author zhy
	 * @param menuMap 添加的菜单数据
	 * @return
	 */
	public Long addMenu(Map<String, Object> menuMap) {
		return addClass(Menu.class, menuMap);
	}
	
	/**
	 * 获取菜单列表
	 * 
	 * @author zhy
	 * @return
	 */
	public List<Map<String, Object>> listMenu() {
		return getListByBean(clazz);
	}
	
	/**
	 * 修改菜单
	 * 
	 * @author zhy
	 * @param menuMap 修改的信息
	 * @return
	 */
	public boolean updateMenu(Map<String, Object> menuMap) {
		return updateMenu(menuMap);
	}
}
