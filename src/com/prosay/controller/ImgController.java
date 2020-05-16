package com.prosay.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Part;

import com.alibaba.fastjson.JSON;
import com.prosay.core.BaseController;
import com.prosay.core.Uploadable;
import com.prosay.core.annotation.Controller;
import com.prosay.core.annotation.RequestMapping;
import com.prosay.core.annotation.ResponseBody;
@Controller
public class ImgController extends BaseController implements Uploadable {
	private static ThreadLocal<List<Part>> parts = new ThreadLocal<List<Part>>();
	private static ThreadLocal<List<String>> fileNames = new ThreadLocal<List<String>>();
	@RequestMapping("/imgUp")
	@ResponseBody
	public void imgUp(){
		//通过文件的名字拿到真实的路径
		String folder = getReq().getServletContext().getRealPath("static")+"/upload";
		String[] allowType = {"image/bmp","image/jpeg","image/png","image/gif"};
		List<String> names = fileNames.get();
		List<Part> files = parts.get();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String tmpFolder = sdf.format(Calendar.getInstance().getTime());
		File fold = new File(folder+"/"+tmpFolder);
		if(!fold.exists()){
			fold.mkdirs();
		}
		for(int i = 0 ; i < names.size();i++){
			Part file = files.get(i);
			String fileName = names.get(i);
			//只允许对应的上传
			if(!Arrays.asList(allowType).contains(file.getContentType())){
				this.writeToResponse("error|不支持的图片类型");
				return;
			}
			//限制图片的大小
			if(file.getSize()>5*1024*1024){
				this.writeToResponse("error|图片大小不能超过5M");
				return;
			}
			fileName = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
			try {
				file.write(folder+"/"+tmpFolder+"/"+fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.writeToResponse("error|文件存储到磁盘异常");
				return;
			}
			//返回引用路径
			this.writeToResponse("success|static/upload/"+tmpFolder+"/"+fileName);
			
		}
	}
	@RequestMapping("/imgUpload")
	@ResponseBody
	public void imgUpload(){
		//通过文件的名字拿到真实的路径
		String folder = getReq().getServletContext().getRealPath("static")+"/upload";
		String[] allowType = {"image/bmp","image/jpeg","image/png","image/gif"};
		List<String> names = fileNames.get();
		List<Part> files = parts.get();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String tmpFolder = sdf.format(Calendar.getInstance().getTime());
		File fold = new File(folder+"/"+tmpFolder);
		if(!fold.exists()){
			fold.mkdirs();
		}
		List<String> outPath = new ArrayList<String>();
				
		for(int i = 0 ; i < names.size();i++){
			Part file = files.get(i);
			String fileName = names.get(i);
			//只允许对应的上传
			if(!Arrays.asList(allowType).contains(file.getContentType())){
				this.writeToResponse("{\"errno\":\"1\"}");
				return;
			}
			//限制图片的大小
			if(file.getSize()>5*1024*1024){
				this.writeToResponse("{\"errno\":\"2\"}");
				return;
			}
			fileName = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
			try {
				file.write(folder+"/"+tmpFolder+"/"+fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.writeToResponse("error|文件存储到磁盘异常");
				return;
			}
			outPath.add("../static/upload/"+tmpFolder+"/"+fileName);
			
		}
		//返回引用路径
		this.writeToResponse("{\"errno\":\"0\",\"data\":"+JSON.toJSONString(outPath)+"}");
	}
	@Override
	public void setFileNames(List<String> fileNames) {
		
		this.fileNames.set(fileNames);
	}

	@Override
	public void setFileParts(List<Part> files) {
		this.parts.set(files);

	}

}
