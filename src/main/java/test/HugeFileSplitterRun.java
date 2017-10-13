/**
 * 
 */
package test;

import com.wareandsy.filesplitter.HugeFileSplitter;
import com.wareandsy.filesplitter.MessagePrinter;
import com.wareandsy.filesplitter.ReaderConfig;
import com.wareandsy.filesplitter.Result;

/**
 * @author fangbe
 *
 */
public class HugeFileSplitterRun {
	
	static class SysOutMessagePrinter implements MessagePrinter {

		@Override
		public void print(String message) {
			System.out.print(message);
		}

		@Override
		public void println(String message) {
			System.out.println(message);
		}

		@Override
		public void error(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		ReaderConfig readerConfig = new ReaderConfig("/Temp/File_Test/split_result", ".txt");
		readerConfig.setBufferSize(4 * 1024 * 1024);
		MessagePrinter messagePrinter = new SysOutMessagePrinter();
		HugeFileSplitter splitter = new HugeFileSplitter("/Temp/File_Test/huge_file_2.txt", readerConfig, messagePrinter);
		Result r = splitter.split();
		messagePrinter.println("duration : " + (r.getEndDate().getTime() - r.getStartDate().getTime()) + " ms");
		messagePrinter.println("file count : " + r.getFileCount());
		messagePrinter.println("files : " + r.getFilePaths());
	}

}
