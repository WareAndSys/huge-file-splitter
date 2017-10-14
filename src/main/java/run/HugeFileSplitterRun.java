/**
 * 
 */
package run;

import com.wareandsy.filesplitter.AbortSignalSender;
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
	
	static class DefaultAbortSignalSender implements AbortSignalSender {

		@Override
		public boolean isAbort() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		String usage = "=================================================================================================\n"
		 + "*\n"
		 + "* Usage Parameters\n"
		 + "* imputFilePath resultRootPath resultFileExtension [splitFileSize(optional)] [bufferSize (optional)]\n"
		 + "* Example : /Temp/File_Test/huge_file.txt /Temp/File_Test/split_result .txt 250 4\n"
		 + "*\n"
		 + "===================================================================================================\n";
		
		if(args.length < 2){
			System.out.println(usage);
			System.exit(1);
		}
		
		String inputFile = args[0];
		String resultRootPath = args[1];
		String extension = args[2];
		int splitFileSize = args.length >= 4 ? (int) (Double.parseDouble(args[3]) * 1024 * 1024) : 100 * 2014 * 1024;
		int bufferSize = args.length >= 5 ? (int) (Double.parseDouble(args[4]) * 1024 * 1024) : 4 * 1024 * 1024;
		
		ReaderConfig readerConfig = new ReaderConfig(resultRootPath, extension);
		readerConfig.setBufferSize(bufferSize);
		readerConfig.setFileSplitSize(splitFileSize);
		
		MessagePrinter messagePrinter = new SysOutMessagePrinter();
		HugeFileSplitter splitter = new HugeFileSplitter(inputFile, readerConfig, messagePrinter);
		splitter.setAbortSignalSender(new DefaultAbortSignalSender());
		
		Result r = splitter.split();
		messagePrinter.println("duration : " + (r.getEndDate().getTime() - r.getStartDate().getTime()) + " ms");
		messagePrinter.println("file count : " + r.getFileCount());
		messagePrinter.println("files : " + r.getFilePaths());
	}

}
