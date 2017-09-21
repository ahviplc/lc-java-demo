package com.code.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/*
 * 动态生成、编译、运行java文件
 */
public class CodeGenerate {

	/**
	 * 编译java类
	 * @param writerPath
	 */
	public void javac(String writerPath){
		//java编译器
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		//文件管理器，参数1：diagnosticListener  监听器,监听编译过程中出现的错误
		StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
		//java文件转换到java对象，可以是多个文件
		Iterable<? extends JavaFileObject> it = manager.getJavaFileObjects(writerPath);
		//编译任务,可以编译多个文件
		CompilationTask t = compiler.getTask(null, manager, null, null, null, it);
		//执行任务
		t.call();
		try {
			manager.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 利用反射，实例化对象，此方法可指定class路径，放在classpath下可能会和jdk编译的文件冲突
	 * @param packPath
	 */
	public void java(String packPath){
		URL[] urls = null;
		try {
			//类路径,url的本地文件格式需要加file:/
			urls = new URL[] {new URL("file:/"+System.getProperty("user.dir")+"/src/")};
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		//类加载器
		URLClassLoader url = new URLClassLoader(urls);
		Class clazz = null;
		try {
			//加载到内存
			clazz = url.loadClass(packPath);
			//实例化对象
			clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * 读文件
	 * @param readerPath
	 * @return
	 */
	public BufferedReader fileReader(String readerPath){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(readerPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return br;
	}
	
	/**
	 * 写文件
	 * @param br
	 * @param writerPath
	 */
	public void fileWriter(BufferedReader br,String writerPath){
		String line;
		BufferedWriter bw = null;
		try {
			line = br.readLine();
			bw = new BufferedWriter(new FileWriter(writerPath));
			while(line != null){
				bw.write(line+"\r\n");
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/*
	 * 获取控制台上输出的信息,输出
	 */
	public void sysoConsole(){
		
		 ByteArrayOutputStream baoStream = new ByteArrayOutputStream(1024);
	        // cache stream
	        PrintStream cacheStream = new PrintStream(baoStream);
	        // old stream
	        PrintStream oldStream = System.out;
	 
	        System.setOut(cacheStream);
	 
	        System.out.print("hello world!");
	 
	        String message = baoStream.toString();
	 
	        message = "<-- " + message + " -->";
	 
	        // Restore old stream
	        System.setOut(oldStream);
	 
	        System.out.println(message);
		
	}
	
	
	
}
