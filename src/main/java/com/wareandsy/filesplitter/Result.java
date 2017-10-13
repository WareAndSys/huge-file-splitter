/**
 * 
 */
package com.wareandsy.filesplitter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fangbe
 *
 */
public class Result {
	
	private long fileCount;
	private List<String> filePaths;
	private Date startDate;
	private Date endDate;

	/**
	 * 
	 */
	public Result() {
		
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}



	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	/**
	 * @return the fileCount
	 */
	public long getFileCount() {
		return fileCount;
	}

	/**
	 * @param fileCount the fileCount to set
	 */
	public void setFileCount(long fileCount) {
		this.fileCount = fileCount;
	}

	/**
	 * @return the filePaths
	 */
	public List<String> getFilePaths() {
		return filePaths;
	}

	/**
	 * @param filePaths the filePaths to set
	 */
	public void setFilePaths(List<String> filePaths) {
		this.filePaths = filePaths;
	}

	/**
	 * 
	 * @param path
	 */
	public void addFilePath(String path) {
		if(filePaths == null){
			if(fileCount != 0){
				filePaths = new ArrayList<>((int)fileCount);
			} else {
				filePaths = new ArrayList<>();
			}
		}
		filePaths.add(path);
	}
	
}
