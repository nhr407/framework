package priv.zhouhuayi.framework.entity.record;

import java.io.Serializable;

import priv.zhouhuayi.framework.util.anno.TableAnno;

/**
 * 点单记录类
 * 
 * @author zhy
 *
 */
@TableAnno(tableName = "ORDER_RECORD")
public class OrderRecord implements Serializable {

	private static final long serialVersionUID = -8673539819051379153L;

	private Long id;

	private Integer menuId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

}
