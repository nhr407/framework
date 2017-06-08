package priv.zhouhuayi.framework.controller.menu;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import priv.zhouhuayi.framework.api.menu.MenuService;
import priv.zhouhuayi.framework.entity.menu.Menu;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

@Controller
@RequestMapping("menu")
public class MenuController {
	
	Logger log = LoggerFactory.getLogger(MenuController.class);
	
	@Resource
	private MenuService menuService;
	
	/**
	 * 添加菜单
	 * 
	 * @author zhy
	 * @param menu 菜单信息
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> addMenu(Menu menu) {
		return menuService.addMenu(menu);
	}
	
	/**
	 * 获取菜单列表
	 * 
	 * @author zhy
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> listMenu(HttpServletRequest request) {
		request.getSession().setAttribute("aa", 111);
		return menuService.menuList();
	}
	
	/**
	 * 修改菜单
	 * 
	 * @author zhy
	 * @param menu 菜单信息
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> updateMenu(Menu menu) {
		return menuService.updateMenu(menu);
	}
}
