/**
 * 
 */
package com.wareandsy.filesplitter;

/**
 * @author fangbe
 *
 */
public class ReaderConfig {
	
	private long fileSplitSize = 100 * 1024 * 1024; // 200 Mb converted into bytes (octets in french)
	private long bufferSize = 2 * 1024 * 1024; // 2 Mb converted into bytes (octets in french)
	private String fileSplitRootPath;
	private String fileSplitRootExtension;

	/**
	 * 
	 * @param fileSplitRootPath
	 * @param fileSplitRootExtension
	 */
	public ReaderConfig(String fileSplitRootPath, String fileSplitRootExtension) {
		this.fileSplitRootPath = fileSplitRootPath;
		this.fileSplitRootExtension = fileSplitRootExtension;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getFileSplitSizeMb() {
		return (double)fileSplitSize / (1024 * 1024);
	}
	
	/**
	 * 
	 * @return
	 */
	public double getBufferSizeMb() {
		return (double)bufferSize / (1024 * 1024);
	}


	/**
	 * @return the fileSplitSize
	 */
	public long getFileSplitSize() {
		return fileSplitSize;
	}

	/**
	 * @param fileSplitSize the fileSplitSize to set
	 */
	public void setFileSplitSize(long fileSplitSize) {
		this.fileSplitSize = fileSplitSize;
	}

	/**
	 * @return the bufferSize
	 */
	public long getBufferSize() {
		return bufferSize;
	}

	/**
	 * @param bufferSize the bufferSize to set
	 */
	public void setBufferSize(long bufferSize) {
		this.bufferSize = bufferSize;
	}

	/**
	 * @return the fileSplitRootPath
	 */
	public String getFileSplitRootPath() {
		return fileSplitRootPath;
	}

	/**
	 * @param fileSplitRootPath the fileSplitRootPath to set
	 */
	public void setFileSplitRootPath(String fileSplitRootPath) {
		this.fileSplitRootPath = fileSplitRootPath;
	}

	/**
	 * @return the fileSplitRootExtension
	 */
	public String getFileSplitRootExtension() {
		return fileSplitRootExtension;
	}

	/**
	 * @param fileSplitRootExtension the fileSplitRootExtension to set
	 */
	public void setFileSplitRootExtension(String fileSplitRootExtension) {
		this.fileSplitRootExtension = fileSplitRootExtension;
	}
	
	

}
