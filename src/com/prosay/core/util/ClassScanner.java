package com.prosay.core.util;
/**
 * 
* @ClassName: ClassScanner 
* @Description: 扫描指定包名下的类并返回集合 
* @author Jame 
* @date 2017年12月15日 下午8:38:55 
*
 */

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassScanner {
	/**
	 * 
	* @Title: scannerClass 
	* @Description: 根据传入的包路径 扫描包下的所有类
	* @param packagePath com.prosay.controller
	* @param 
	* @return Map<String,Class<?>>    返回类型 
	* @throws
	 */
	public static Map<String,Class<?>> scannerClass(String packagePath){
		//把点换成/ 
		String filePath = packagePath.replace(".", "/");
		System.out.println(filePath);
		Map<String,Class<?>> result = new HashMap<String,Class<?>>();
		
		try {
			//通过包路径获得多个url的对象集合 类路径和jar包中都存在的情况下
			Enumeration<URL>  urls = Thread.currentThread().getContextClassLoader().getResources(filePath);
			//通过字符串截取获取一个根路径 类路径
			String rootPath = filePath;
			//迭代这个包名包含的所有类路径（jar）
			while(urls.hasMoreElements()){
				URL url = urls.nextElement();
				//通过url去判断这个是文件对象(文件夹)才进入这个操作
				if(url.getProtocol().equals("file")){//类路径的处理方式
					System.out.println(url.getPath());
					//  /E:/workspaceVip/prosay/build/classes/org/dom4j
					File folder = new File(url.getPath().substring(1)); 
					//扫描文件夹下的所有类 （包括子包-子文件夹）
					scannerFile(folder,rootPath,result);
				}else if(url.getProtocol().equals("jar")){
					//JarURLConnection
					JarURLConnection connection = (JarURLConnection)url.openConnection();
					if(connection!=null){
						//通过连接对象获取一个Jar文件对象
						JarFile jarFile = connection.getJarFile();
						if(jarFile!=null){
							Enumeration<JarEntry> jarEntrys = jarFile.entries();
							while(jarEntrys.hasMoreElements()){
								//jar包中的实体 
								JarEntry jarEntry = jarEntrys.nextElement();
								//System.out.println(jarEntry.getName());
								String entryName = jarEntry.getName();
								//筛选不需要 留下需要的
								if(entryName.endsWith(".class")&&entryName.startsWith(filePath)){
									String className = entryName.split("\\.")[0];
									className = className.replace("/", ".");
									result.put(className, Class.forName(className));
								}
							}
						}
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			XmlUtil.log(e.getMessage()+"初始化异常！");
		}
		
		return result;
	}
	/**
	 * 
	* @Title: scannerFile 
	* @Description: 扫描文件夹下的所有java类
	* @param @param folder
	* @param @param rootPath
	* @param @param result    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private static void scannerFile(File folder,String rootPath,Map<String,Class<?>> result){
		//获取当前folder文件夹下的所有子文件（目录）
		File[] files = folder.listFiles();
		for(File file : files){
			if(file.isDirectory()){//如果是文件夹则继续深入
				//递归调用自己
				scannerFile(file,rootPath+"/"+file.getName(),result);
			}else{//是文件的情况下
				//拿到当前文件的真实路径
				String path = file.getAbsolutePath();
				//判断是否是以.class结尾的文件
				if(path.endsWith(".class")){
					//E:\workspaceVip\prosay\build\classes\com\prosay\core\ApplicationContext.class
					//把反斜杠换成正斜杠
					path = path.replace("\\", "/");
					//System.out.println(path);
					//获得类路径 com/prosay/core/ApplicationContext
					String className = rootPath+path.substring(path.lastIndexOf("/"),path.indexOf(".class"));
					try {
						//将路径符换成.
						className = className.replace("/", ".");
						System.out.println(className);
						result.put(className, Class.forName(className));
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	public static void main(String [] args){
		//scannerClass("org.dom4j");
		//scannerClass("com.prosay.core");
	}
}
