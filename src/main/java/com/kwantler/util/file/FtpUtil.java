package com.kwantler.util.file;


import com.kwantler.util.common.ConfigProperties;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FtpUtil {

    public static boolean uploadFile(String password, String username, String host,
                                     int port, String basePath, String filePath, String fileName, FileInputStream input){
        filePath = filePath.replace("/","");
        FTPClient ftpClient = new FTPClient();
        try {
            //建立连接
            ftpClient.connect(host,port);
            //开启被动模式
            ftpClient.enterLocalPassiveMode();
            //登录
            ftpClient.login(username,password);
            //检验是否连接成功
            int reply = ftpClient.getReplyCode();
            //检查连接状态
            if (!FTPReply.isPositiveCompletion(reply)){
                System.out.println(host+":"+port+" 连接失败,用户名/密码： "+ username +"/" + password);
            }
            //切换工作目录
            if (CWD(basePath, filePath, ftpClient)) return false;

            //上传,以二进制方式进行传送
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //该代码的作用是将文件编码由ISO-889-1转为utf-8，也可以转为其他的，例如GBK
            ftpClient.setControlEncoding("utf-8");
            //FTP协议里面，规定文件名编码为iso-8859-1，所以目录名或文件名需要转码。所以如果是中文需要用户手动设置。
            ftpClient.storeFile(new String(fileName.getBytes("GBK"),"iso-8859-1"), input);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            try {
                input.close();
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private static boolean CWD(String basePath, String filePath, FTPClient ftpClient) throws IOException {
        if (ftpClient.changeWorkingDirectory(basePath)){
            if (!ftpClient.changeWorkingDirectory(filePath)){
                System.out.println("存放文件的目录："+filePath+" 不存在");
                if (ftpClient.makeDirectory(filePath)){
                    ftpClient.changeWorkingDirectory(filePath);
                }else{
                    System.out.println("创建文件失败！"+ftpClient.getReplyString());
                    return true;
                }
            }
        }else{
            System.out.println("基础目录："+basePath+" 不存在目录");
            if (ftpClient.makeDirectory(basePath)){
                if (!ftpClient.changeWorkingDirectory(filePath)){
                    System.out.println("存放文件的目录："+filePath+" 不存在");
                    if (ftpClient.makeDirectory(filePath)){
                        ftpClient.changeWorkingDirectory(filePath);
                    }else{
                        System.out.println("创建文件失败！");
                        return true;
                    }
                }
            }else{
                System.out.println("创建文件夹:"+basePath+" 失败");
                return true;
            }

        }
        return false;
    }

    public static boolean downloadFile(String password, String username, String host,
                                   int port, String basePath, String filePath, String localPath, String fileName){
        filePath = filePath.replace("/","");
        FTPClient fc = new FTPClient();
    try {
        fc.connect(host,port);
        fc.enterLocalPassiveMode();
        //该代码的作用是将文件编码由ISO-889-1转为utf-8，也可以转为其他的，例如GBK
        fc.setControlEncoding("utf-8");
        fc.login(username,password);

        if (!FTPReply.isPositiveCompletion(fc.getReplyCode())){
            System.out.println(host+":"+port+" 连接失败,用户名/密码： "+ username +"/" + password);
        }
        if (!fc.changeWorkingDirectory(basePath)){
            return false;
        }

        File fileDir = new File(localPath);
        if (!fileDir.exists()){
            fileDir.mkdirs();
        }

        FTPFile[] listFiles = fc.listFiles();
        for (FTPFile f : listFiles) {
//            String fn = new String(f.getName().getBytes("iso-8859-1"),"UTF-8");
            String fn = f.getName();
            System.out.println(fn);
            if (fn.compareTo(fileName) > 0){
                OutputStream os = new FileOutputStream(new File(localPath+File.separator+fn));
                fc.setFileType(FTPClient.BINARY_FILE_TYPE);
                fc.retrieveFile(f.getName(), os);
                os.close();
            }
        }
    } catch (IOException e) {
        try {
            fc.logout();
            fc.disconnect();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        e.printStackTrace();
    }
    return false;
}
    public static void main(String[] args) {
        String ceshi_ftp_password = ConfigProperties.getProperties("ceshi_ftp_password");
        String ceshi_ftp_username = ConfigProperties.getProperties("ceshi_ftp_username");
        String ceshi_ftp_host = ConfigProperties.getProperties("ceshi_ftp_host");
        int ceshi_ftp_port = Integer.valueOf(ConfigProperties.getProperties("ceshi_ftp_port"));
        String ceshi_ftp_upload_basePath = ConfigProperties.getProperties("ceshi_ftp_upload_basePath");
        String ceshi_ftp_download_basePath = ConfigProperties.getProperties("ceshi_ftp_download_basePath");
        String ceshi_ftp_filePath = ConfigProperties.getProperties("ceshi_ftp_filePath");
        String ceshi_ftp_localPath = ConfigProperties.getProperties("ceshi_ftp_localPath");

        FileInputStream input = null;
        try {
            input = new FileInputStream(new File("D:/work/other/uml类图脑图.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        FtpUtil.uploadFile(ceshi_ftp_password,ceshi_ftp_username,ceshi_ftp_host,ceshi_ftp_port,ceshi_ftp_upload_basePath,ceshi_ftp_filePath,ceshi_ftp_fileName,input);
        FtpUtil.downloadFile(ceshi_ftp_password,ceshi_ftp_username,ceshi_ftp_host,ceshi_ftp_port,ceshi_ftp_download_basePath,
                ceshi_ftp_filePath,ceshi_ftp_localPath,"你3.txt");
    }

    /**
     * 如果文件名称是一个文件，那么这个文件会直接传送到ftp服务器上对应的目录下
     * 如果是文件夹，那么文件夹目录下的所有文件包括子目录中的所有文件将会传送到ftp服务器上
     * @param filename 文件或者文件夹全路径
     * @return
     */
    public static boolean upload(String filename){

        return false;
    }



}
