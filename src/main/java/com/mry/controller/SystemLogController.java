package com.mry.controller;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mry.exception.CommonException;
import com.mry.model.SystemLog;

@RestController
@RequestMapping("/log")
public class SystemLogController {

	// 获取当前系统的日志列表取
	@GetMapping("/list")
	@ResponseBody
	public Map<String, Object> getSystemLogList() {
		Map<String, Object> result = new HashMap<String, Object>();
		File logDir = new File("/alidata/log/mry");
		if (logDir.exists() && logDir.isDirectory()) {
			List<File> logs = new ArrayList<File>();
			for(File file : logDir.listFiles()) {
				if(file.getName().endsWith(".log") || file.getName().endsWith(".gz")) {
					logs.add(file);
				}
			}
			result.put("logs", logs);
		}
		return result;
	}
	
	// 下载日志文件
	@GetMapping("/download")
	public void downloadLog(String fileName, String filePath, HttpServletResponse response) {
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(filePath);
			response.reset();
			response.setContentType("bin");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			byte[] b = new byte[1024];
			int len;
			while ((len = inStream.read(b)) > 0) {
				response.getOutputStream().write(b, 0, len);
			}
		} catch (FileNotFoundException e) {
			throw new CommonException(404, "download log fail, " + e.getMessage());
		} catch (IOException e) {
			throw new CommonException(500, "download log fail, " + e.getMessage());
		} finally {
			closeIO(inStream);
		}
	}
	
	// 删除日志文件
	@GetMapping("/delete")
	public Map<String, Object> deleteLog(String fileName, String filePath) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(filePath.endsWith(".gz")) {
				// 删除 log.gz 文件
				File logGizFile = new File(filePath);
				logGizFile.delete();
				
				// 删除解压后的临时文件
				String tempLogFilePath = filePath.replace(".0.gz", "");
				File tempLogFile = new File(tempLogFilePath);
				tempLogFile.delete();
			} else if(filePath.endsWith(".log")) {
				// 删除 log.log 文件
				File logTxtFile = new File(filePath);
				logTxtFile.delete();
			}
			result.put("msg", "delete Log: " + fileName + " successful !");
		} catch (Exception e) {
			result.put("msg", "delete Log: " + fileName + " fail, Exception: " + e.getMessage());
		}
		return result;
	}
	
	// 批量删除日志文件
	@PostMapping("/batch/delete")
	public Map<String, Object> batchDeleteLogs(@RequestBody List<SystemLog> systemLogs) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			for(SystemLog log : systemLogs) {
				deleteLog(log.getLogName(), log.getLogPath());
			}
			result.put("msg", "delete logs successful, total: " + systemLogs.size());
		} catch(Exception e) {
			result.put("msg", "delete logs fail, Exception: " + e.getMessage());
		}
		return result;
	}
	
	// 在线打开 log 日志文件
	@GetMapping("/open")
	public Map<String, Object> openLog(String filePath) {
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		try {
			if(filePath.endsWith(".gz")) {
				// 如果文件已经被解压过，就直接返回解压过的文件路径
				String tempLogFilePath = filePath.replace(".0.gz", "");
				File tempLogFile = new File(tempLogFilePath);
				if(!tempLogFile.exists()) {
					filePath = uncompressGzipFile(filePath);
				} else {
					filePath = tempLogFilePath;
				}
			}
			File log = new File(filePath);
			reader = new BufferedReader(new FileReader(log));
			String tempString = null;
			while((tempString = reader.readLine()) != null) {
				builder.append("<p>")
					   .append(tempString)
					   .append("</p>");
			}
			result.put("logs", builder.toString());
		} catch (IOException e) {
			throw new CommonException(500, "open log fail, " + e.getMessage());
		} finally {
			closeIO(reader);
		}
		return result;
	}
	
	// 解压 gzip 文件
	public String uncompressGzipFile(String filePath) {
		FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        GZIPInputStream gzipInputStream = null;
        String targetFilePath = "";
        try {  
        	fileInputStream = new FileInputStream(filePath);   
        	gzipInputStream = new GZIPInputStream(fileInputStream);   
        	targetFilePath = filePath.substring(0,filePath.lastIndexOf('.'));
        	targetFilePath = targetFilePath.substring(0,targetFilePath.lastIndexOf('.'));
            fileOutputStream = new FileOutputStream(targetFilePath);   
            int num;
            byte[] buf=new byte[1024];
            while ((num = gzipInputStream.read(buf,0,buf.length)) != -1) {
            	fileOutputStream.write(buf,0,num);   
            }
        } catch (Exception e){  
        	throw new CommonException(500, "uncompress log fail, " + e.getMessage());
        }  finally {
        	closeIO(fileOutputStream, gzipInputStream, fileInputStream);
		}
        return targetFilePath;
    }
	
	// 关闭 IO 流
	public void closeIO(Closeable ... ios) {
		try{
			for(Closeable io : ios) {
				io.close();
			}	
		} catch (Exception e) {
			throw new CommonException(500, e.getMessage());
		}
	}
}
