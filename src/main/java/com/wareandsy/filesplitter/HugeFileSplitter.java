/**
 * 
 */
package com.wareandsy.filesplitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wareandsy.filesplitter.HugeFileSplitterEvent.EventType;

/**
 * @author fangbe
 *
 */
public class HugeFileSplitter {
	
	private String filePath;
	private ReaderConfig readerConfig;
	private MessagePrinter messagePrinter;
	
	private List<HugeFileSplitterEventListener> listeners;
	private AbortSignalSender abortSignalSender;

	/**
	 * 
	 * @param filePath
	 * @param readerConfig
	 */
	public HugeFileSplitter(String filePath, ReaderConfig readerConfig, MessagePrinter messagePrinter) {
		this.filePath = filePath;
		this.readerConfig = readerConfig;
		this.messagePrinter = messagePrinter;
		
		listeners = new ArrayList<>(1);
	}
	
	
	
	/**
	 * @return the listeners
	 */
	public List<HugeFileSplitterEventListener> getListeners() {
		return listeners;
	}



	/**
	 * @param listeners the listeners to set
	 */
	public void setListeners(List<HugeFileSplitterEventListener> listeners) {
		this.listeners = listeners;
	}


	/**
	 * @return the abortSignalSender
	 */
	public AbortSignalSender getAbortSignalSender() {
		return abortSignalSender;
	}



	/**
	 * @param abortSignalSender the abortSignalSender to set
	 */
	public void setAbortSignalSender(AbortSignalSender abortSignalSender) {
		this.abortSignalSender = abortSignalSender;
	}



	/**
	 * 
	 * @return
	 */
	public Result split(){
		Result r = new Result();
		r.setStartDate(new Date());
		
		File f = new File(filePath);
		messagePrinter.println("Start");
		
		if(abortSignalSender != null && !abortSignalSender.isAbort()) {
			try (FileInputStream in = new FileInputStream(f)) {
				long size = in.getChannel().size();
				setFileCount(r, size);
				
				fireFileCountedEvent(r);
				
				ReadableByteChannel inChannel = Channels.newChannel(in);
				long totalCount = 0;
				int p = 1;
				
				while(totalCount < size && abortSignalSender != null && !abortSignalSender.isAbort()) {
					String path = readerConfig.getFileSplitRootPath() + "_" + p + readerConfig.getFileSplitRootExtension();
					messagePrinter.println("creating : " + path);
					FileOutputStream out = new FileOutputStream(new File(path));
					
					long localTotalCount = 0;
					long count = 0;
					while(localTotalCount < readerConfig.getFileSplitSize() && 
							((count = out.getChannel().transferFrom(inChannel, localTotalCount, readerConfig.getBufferSize())) > 0) 
							&& abortSignalSender != null && !abortSignalSender.isAbort()) {
						localTotalCount += count;
						out.getChannel().position(localTotalCount);
					}
					
					out.close();
					r.addFilePath(path);
					
					fireFileCreatedEvent(p, path, 1d / r.getFileCount());
					
					totalCount += localTotalCount;
					p++;
				}
				
				
			} catch (Exception e){
				messagePrinter.error(e);
			}
		}
		
	
		
		r.setEndDate(new Date());
		messagePrinter.println("End");
		
		return r;
	}



	/**
	 * @param r
	 */
	protected void fireFileCountedEvent(Result r) {
		HugeFileSplitterEvent event1 = new HugeFileSplitterEvent(EventType.FILE_COUNTED);
		event1.addParameter("fileCount", r.getFileCount());
		fireEvent(event1);
	}



	/**
	 * @param p
	 * @param path
	 */
	protected void fireFileCreatedEvent(int p, String path, double part) {
		HugeFileSplitterEvent event = new HugeFileSplitterEvent(EventType.FILE_CREATED);
		event.addParameter("path", path);
		event.addParameter("index", p);
		event.addParameter("part", part);
		fireEvent(event);
	}

	/**
	 * 
	 * @param event
	 */
	private void fireEvent(HugeFileSplitterEvent event) {
		for(HugeFileSplitterEventListener listener : listeners){
			listener.onEvent(event);
		}
	}

	/**
	 * @param r
	 * @param size
	 */
	private void setFileCount(Result r, long size) {
		long remaining = size % readerConfig.getFileSplitSize();
		long fileCount = size / readerConfig.getFileSplitSize();
		r.setFileCount(remaining ==  0 ? fileCount : fileCount + 1);
	}
}
