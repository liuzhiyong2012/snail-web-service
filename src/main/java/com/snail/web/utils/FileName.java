package com.snail.web.utils;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author hehaiyang
 *
 */
public class FileName {
    static String newString = "rightp";//新字符串,如果是去掉前缀后缀就留空，否则写上需要替换的字符串
    static String oldString = "ga00";//要被替换的字符串
    //static String dir = "F:\\Java数据结构\\视频";//文件所在路径，所有文件的根目录，记得修改为你电脑上的文件所在路径
//    static String dir = "D:\\project\\graduation-design-exhibition-web\\src\\pages\\Home\\img\\ga";
    static String dir = "C:\\Users\\Administrator\\Desktop\\广美作品志勇上传\\48件作品封面图";



    public static void main(String[] args) throws IOException {
        recursiveTraversalFolder(dir);//递归遍历此路径下所有文件夹
    }
    /**
     * 递归遍历文件夹获取文件名称
     */
    public static void recursiveTraversalFolder(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                File newDir = null;//文件所在文件夹路径+新文件名
                String newName = "";//新文件名
                String fileName = null;//旧文件名
                File parentPath = new File("");//文件所在父级路径
                Integer count = 1;
                for (File file : fileArr) {
                    if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        recursiveTraversalFolder(file.getAbsolutePath());
                    } else {//是文件，判断是否需要重命名
                        fileName = file.getName();
                        parentPath = file.getParentFile();
                      //  if (fileName.contains(oldString)) {
                            //文件名包含需要被替换的字符串
                            newName = fileName.replaceAll(fileName, count.toString() + ".png");//新名字
                            newDir = new File(parentPath + "/" + newName);//文件所在文件夹路径+新文件名
                            file.renameTo(newDir);//重命名
                        count++;
                            System.out.println("修改后：" + newDir);
                       // }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
}
