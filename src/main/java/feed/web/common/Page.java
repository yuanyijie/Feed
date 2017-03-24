package feed.web.common;

/**
 * Page 对象，主要用于分页
 * @author Boxbox
 *
 */
public class Page {
	/**
	 * 页数
	 */
	int index;
	
	/**
	 * 一页的数目
	 */
	int chunk;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getChunk() {
		return chunk;
	}

	public void setChunk(int chunk) {
		this.chunk = chunk;
	}
	
	
}
