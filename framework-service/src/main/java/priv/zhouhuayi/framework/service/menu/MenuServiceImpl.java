package priv.zhouhuayi.framework.service.menu;

import java.util.Map;

import javax.annotation.Resource;

import priv.zhouhuayi.framework.api.menu.MenuService;
import priv.zhouhuayi.framework.dao.menu.MenuDao;
import priv.zhouhuayi.framework.entity.menu.Menu;
import priv.zhouhuayi.framework.service.rollback.RollBackException;
import priv.zhouhuayi.framework.util.backMsg.BackMsgUtil;
import priv.zhouhuayi.framework.util.convert.ConvertUtil;

public class MenuServiceImpl implements MenuService {
	@Resource
	private MenuDao menuDao;
	
	@Override
	public Map<String, Object> addMenu(Menu menu) {
		Map<String, Object> menuMap = ConvertUtil.convertMapByDb(menu);
		try {
			long menuId = menuDao.addMenu(menuMap);
			if(menuId > 0) {
				return BackMsgUtil.success();
			} else {
				return BackMsgUtil.fail();
			}
		} catch (Exception e) {
			new RollBackException(e);
			return BackMsgUtil.fail();
		}
	}
	
	@Override
	public Map<String, Object> menuList() {
		try {
			return BackMsgUtil.success(menuDao.listMenu());
		} catch (Exception e) {
			return BackMsgUtil.success(menuDao.listMenu());
		}
	}
	
	@Override
	public Map<String, Object> updateMenu(Menu menu) {
		Map<String, Object> menuMap = ConvertUtil.convertMapByDb(menu);
		try {
			boolean result = menuDao.updateMenu(menuMap);
			if(result) {
				return BackMsgUtil.success();
			} else {
				return BackMsgUtil.fail();
			}
		} catch (Exception e) {
			new RollBackException(e);
			return BackMsgUtil.fail();
		}
	}
}
