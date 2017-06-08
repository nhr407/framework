package priv.zhouhuayi.framework.util.pojo;


/**
 * 分页查询Pojo类
 * 
 * @author zhy
 *
 */
public class PaddingPojo<T>
{
	/** 起始页 */
	private Integer page;

	/** 每页显示的条数 */
	private Integer rows;

	/** 排序字段 */
	private String sort;

	/** 排序方式 */
	private String order;

	/** 查询的条件 */
	private T searchClass;

	/** 分页的开始位置 */
	private Integer start;

	private String search;

	public Integer getPage()
	{
		return page;
	}

	public void setPage(Integer page)
	{
		this.page = page;
	}

	public Integer getRows()
	{
		return rows;
	}

	public void setRows(Integer rows)
	{
		this.rows = rows;
	}

	public String getSort()
	{
		return sort;
	}

	public void setSort(String sort)
	{
		this.sort = deleteSpoit(sort);
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = deleteSpoit(order);
	}

	public T getSearchClass()
	{
		return searchClass;
	}

	public void setSearchClass(T searchClass)
	{
		this.searchClass = searchClass;
	}

	public Integer getStart()
	{
		start = (page - 1) * rows;
		return start;
	}

	public void setStart(Integer start)
	{
		this.start = start;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	protected String deleteSpoit(String value) {
		return value.replaceAll("'", "");
	}
}
