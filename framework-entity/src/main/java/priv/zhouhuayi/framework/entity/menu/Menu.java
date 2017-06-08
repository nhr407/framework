package priv.zhouhuayi.framework.entity.menu;

import java.io.Serializable;

import priv.zhouhuayi.framework.util.anno.FieldAnno;
import priv.zhouhuayi.framework.util.anno.TableAnno;

/**
 * 菜单类
 * 
 * @author zhy
 *
 */
@TableAnno(tableName="MENU")
public class Menu implements Serializable {

	private static final long serialVersionUID = -8287105643540534205L;
	
	private Integer id;
	
	@FieldAnno(fieldName="MENU_NAME")
	private String menuName;
	
	@FieldAnno(fieldName="MENU_IMG")
	private String menuImg;
	
	@FieldAnno(fieldName="MENU_INTRO")
	private String menuIntro;
	
	@FieldAnno(fieldName="MENU_PRICE")
	private Double menuPrice;
	
	@FieldAnno(fieldName="MENU_SOURCE")
	private String menuSource;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuImg() {
		return menuImg;
	}

	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
	}

	public String getMenuIntro() {
		return menuIntro;
	}

	public void setMenuIntro(String menuIntro) {
		this.menuIntro = menuIntro;
	}

	public Double getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(Double menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getMenuSource() {
		return menuSource;
	}

	public void setMenuSource(String menuSource) {
		this.menuSource = menuSource;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
