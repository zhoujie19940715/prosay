package com.prosay.core.util;
/**
 * Prosay工具-操作xml的api 解析xml
 * @author Jame
 * @version 1.0
 * 
 */

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil {

	/**
	 * 通过输入流拿到一个document对象
	 * @param in
	 * @return
	 */
	public static Document getDocFromStream(InputStream in){
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(in);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	/**
	 * 拿根节点
	 * @param document
	 * @return
	 */
	public static Element getRootElement(Document document){
		if(document==null){
			return null;
		}
		Element root = document.getRootElement();
		return root;
	}
	/**
	 * 拿父节点下面的所有子元素
	 * @param parent 父元素
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getChildElements(Element parent){
		if(parent==null){
			return null;
		}
		return (List<Element>)parent.elements();
	}
	public static void log(String log){
		System.out.println(log);
	}
}
