package org.jerry.light4j.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	/**
	 * 判断目录是否存在
	 * @param path
	 * @return
	 */
	public static boolean isDirExit(String path){
		File file = new File(path);
		return file.exists();
	}
	
	/**
	 * 创建目录（需要创建多级目录）
	 * @param path
	 * @return
	 */
	public static boolean createDirPath(String path){
		boolean result = false;
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		return result;
	}
	
	/**
	 * 根据主目录获取目录下所有模板文件
	 * @param fromPath
	 * @return
	 */
	public static List<File> queryAllFiles(String fromPath){
		File fromFile = new File(fromPath);
		List<File>  filePaths = new ArrayList<File>();
		if(fromFile.exists()){
			File[] fileList = fromFile.listFiles();
			for (File file : fileList) {
				if(file.exists()&&file.isFile()){
					String fileAbsolutePath = file.getAbsolutePath();
						filePaths.add(file);
				}
				if(file.exists()&&file.isDirectory()){
					String fileAbsolutePath = file.getAbsolutePath();
					List<File> sonFilePaths = queryAllFiles(fileAbsolutePath);
					if(null != sonFilePaths && sonFilePaths.size()>0){
						filePaths.addAll(sonFilePaths);
					}
				}
			}
		}
		return filePaths;
	}
	
}
