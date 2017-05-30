package feed.web.common.picture;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import feed.web.model.data.AvatarData;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

/**
 * 图片处理工具
 * 
 * @author yuanyj
 *
 */
public class PictureTool {
	private final static Logger log = Logger.getLogger(PictureTool.class);
	private final static int DEF_WIDTH = 180;
	private final static int DEF_HEIGHT = 180;

	public static PictureTool getInstance() {
		return PictureToolHolder.INSTANCE;
	}
	
	/**
	 * 裁剪头像(默认裁180*180)
	 * @param input 
	 * @param avatarData
	 * @return
	 */
	public byte[] cropAvatar(InputStream input, AvatarData avatarData) {
		Builder<? extends InputStream> builder = Thumbnails.of(input);
		builder.scale(avatarData.getScaleX(), avatarData.getScaleY()).rotate(avatarData.getRotate())
				.sourceRegion(avatarData.getX(), avatarData.getY(),DEF_WIDTH, DEF_HEIGHT);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			builder.toOutputStream(out);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return out.toByteArray();
	}

	private PictureTool() {

	}

	private static class PictureToolHolder {
		private final static PictureTool INSTANCE = new PictureTool();
	}

}
