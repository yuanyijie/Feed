package feed.web.model.data;

/**
 * 头像上传截取附带的参数
 * @author BoxBox
 *
 */
public class AvatarData {
	/**
	 * scrop框 X坐标
	 */
	private int x;
	
	/**
	 * scrop框 Y坐标
	 */
	private int y;
	
	/**
	 * 裁剪区域的宽度
	 */
	private int width;
	
	/**
	 * 裁剪区域的高度
	 */
	private int height;
	
	/**
	 * 旋转系数
	 */
	private double rotate;
	
	/**
	 * X方向缩放系数
	 */
	private double scaleX;
	
	/**
	 * Y方向缩放系数
	 */
	private double scaleY;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getRotate() {
		return rotate;
	}

	public void setRotate(double rotate) {
		this.rotate = rotate;
	}

	public double getScaleX() {
		return scaleX;
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}
	
}
