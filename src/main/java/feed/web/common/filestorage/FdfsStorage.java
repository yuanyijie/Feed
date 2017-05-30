package feed.web.common.filestorage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.stereotype.Component;

/**
 * 
 * @author yuanyj
 *
 */
@Component
public class FdfsStorage implements FileStorage {
	private final static Logger log = Logger.getLogger(FdfsStorage.class);
	private final static String FDFS_FILE_NAME = "fastdfs-client.properties";

	static {
		try {
			ClientGlobal.initByProperties(FDFS_FILE_NAME);
		} catch (IOException | MyException e) {
			log.error("fdfs load property failed" + e.getMessage(), e);

		}
	}

	@Override
	public String save(byte[] fileBytes, String extName) {

		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = null;
		String fileId = null;
		try {
			trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer, storageServer);
			// 不设置MetaData
			fileId = client.upload_file1(fileBytes, extName, null);
		} catch (IOException | MyException e) {
			log.error("upload file failed" + e.getMessage(), e);
		} finally {
			if (trackerServer != null) {
				try {
					trackerServer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return fileId;
	}

	@Override
	public void delete(String fileId) {
		
	}

}
