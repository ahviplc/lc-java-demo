package com.lc;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;


public class PrintDemo {
	
	public static void main(String[] args) throws IOException {
	/*	Runtime.getRuntime().exec(
				"cmd.exe /C start acrord32 /h /p "
				+ "D:\\1.pdf");*/
		
		
		 // System.out.println("Value:"+test());
        // 打印pdf的一个方法，首先安装下PDFCreator软件
		//D:\\PDFPDF\\20140908_图纸\\PDF文件\\
        try {
            printFile("D:\\PDFPDF\\");
        } catch (Exception e) {
            System.out.println("打印文件异常：" + e.getMessage());
            e.printStackTrace();
        }
	}
	
	
	 public static void printFile(String path) throws Exception {
	        File file = new File(path);
	        File[] fies = file.listFiles();
	        for (File f : fies) {
	            System.out.println("file " + f.getName());
	            String fileExt = f.getName().substring(
	                    f.getName().indexOf(".") + 1, f.getName().length());
	            if ("pdf".equalsIgnoreCase(fileExt)) {
	                String filepath = path + File.separator + f.getName();
	                File pdfFile = new File(filepath);
	                // 构建打印请求属性集
	                PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
	                pras.add(new JobName(f.getName(), null));
	                 
	                HashAttributeSet has = new HashAttributeSet();
//	                has.add(new PrinterName("Officejet J5500 series", null)); // 添加打印机名称
	 
	                // 设置打印格式，因为未确定文件类型，这里选择AUTOSENSE
	                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
	                 
	                // 查找所有的可用打印服务
	                PrintService printService[] = PrintServiceLookup
	                        .lookupPrintServices(flavor, has);
	 
	                if (printService[1] != null) {
	                    //获得打印服务的文档打印作业
	                    DocPrintJob job = printService[1].createPrintJob(); // 创建打印任务
	                     
	                    DocAttributeSet das=new HashDocAttributeSet();               
	                     
	                    InputStream fis = new FileInputStream(pdfFile); // 构造待打印的文件流
	                    Doc doc = new SimpleDoc(fis, flavor, das); // 建立打印文件格式
	                    job.print(doc, pras); // 进行文件的打印
	                }
	            }
	        }
	    }

}
