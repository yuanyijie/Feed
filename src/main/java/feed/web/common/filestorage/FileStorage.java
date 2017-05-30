package feed.web.common.filestorage;

/**
 * 文件存储接口
 * @author yuanyj
 *
 */
public interface FileStorage {
	
	/**
	 * 存储文件，返回一个path
	 * @param fileBytes 字节数组
	 * @param extName 文件扩展名
	 * @return fileId 或者 null
	 */
	String save(byte[] fileBytes, String extName);
	
	/**
	 * 根据文件的Id/path删除文件
	 * @param fileId
	 */
	void delete(String fileId);
}
