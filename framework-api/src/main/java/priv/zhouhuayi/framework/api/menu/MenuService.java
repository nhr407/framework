package priv.zhouhuayi.framework.api.menu;

import java.util.Map;

import priv.zhouhuayi.framework.entity.menu.Menu;

public interface MenuService {
	/**
	 * 添加菜单
	 * 
	 * @author zhy
	 * @param menu 菜单信息类
	 * @return
	 */
	public Map<String, Object> addMenu(Menu menu);
	
	/**
	 * 菜单列表
	 * 
	 * @author zhy
	 * @return
	 */
	public Map<String, Object> menuList();
	
	/**
	 * 修改菜单
	 * 
	 * @author zhy
	 * @param menu
	 * @return
	 */
	public Map<String, Object> updateMenu(Menu menu);
}
