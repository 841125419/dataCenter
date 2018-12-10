package com.kwantler.util.file.ftp;

import com.kwantler.util.common.ConfigProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.Arrays;

public class FtpTool {
    FTPClient ftp = new FTPClient();
    private String username;
    private String password;
    private String basePath;
    private String hostname;
    private int port;

    /**
     *
     * @return
     */
    public boolean connectFtp(){
        try {
            //连接ftp服务器
            ftp.connect(hostname,port);
            //开启被动模式
            ftp.enterLocalPassiveMode();
            //登录，并判断是否连接成功
            if (!ftp.login(username,password)){
                System.out.println(ftp.getReplyString());
                return false;
            }
            //上传,以二进制方式进行传送
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            //该代码的作用是将文件编码由ISO-889-1转为utf-8，也可以转为其他的，例如GBK
            ftp.setControlEncoding("utf-8");
            this.cwdOper();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 切换目录
     * @throws Exception
     */
    private void cwdOper() throws Exception{
        cwdOper(basePath);
    }

    /**
     * 切换目录
     * @param filePath
     * @throws Exception
     */
    public void cwdOper(String filePath) throws Exception{
        String ftpTypeFileName = localFileName2ftp(filePath);
        if (!ftp.changeWorkingDirectory(ftpTypeFileName)){

            if (!ftp.makeDirectory(ftpTypeFileName)){
                throw new Exception(ftp.getReplyString());
            }
            ftp.changeWorkingDirectory(ftpTypeFileName);
        }
    }

    /**
     * 如果文件名称是一个文件，那么这个文件会直接传送到ftp服务器上对应的目录下
     * 如果是文件夹，那么文件夹目录下的所有文件包括子目录中的所有文件将会传送到ftp服务器上
     * @param f 文件或者文件夹
     * @return
     */
    public boolean upload(File f) throws Exception{
        if (f.isDirectory()){
            cwdOper(f.getName());
            String[] files = f.list();
            for (String fstr: files) {
                File f2 = new File(f.getPath() + File.separator + fstr);
                if (f.isDirectory()){
                    upload(f2);
                }else{
                    FileInputStream fis = new FileInputStream(f2);
                    System.out.println(f2.getPath());
                    ftp.storeFile(localFileName2ftp(f2.getName()),fis);
                }
            }
            ftp.changeWorkingDirectory("..");
            return false;
        } else{
            FileInputStream fis = new FileInputStream(f);
            System.out.println(f.getPath());
            ftp.storeFile(localFileName2ftp(f.getName()),fis);
        }
        return true;
    }

    /**
     *  将会按照对应的目录进行存放下载的文件
     * @param localPath 本地存放路径
     * @param remotePath 远程路径
     * @return
     */
    public boolean download(String localPath, String remotePath){
        try {
            if (!StringUtils.isEmpty(remotePath) && !remotePath.equals("/") && !remotePath.equals("\\") && !ftp.changeWorkingDirectory(remotePath)){
                System.out.println(ftp.getReplyString());
                return false;
            }
            FTPFile[] ftpFiles = ftp.listFiles();
            if (ftpFiles.length == 0){
                System.out.println("没有任何的文件存在！");
                return false;
            }
            for (FTPFile f : ftpFiles) {
                startDownload(f,localPath,remotePath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean startDownload(FTPFile f, String localPath, String remotePath) {
        //判断本地是否存在该目录
        File localFile = new File(localPath);
        if (!localFile.exists()){
            localFile.mkdirs();
        }

        if (f.isDirectory()){
            boolean isRootPath = StringUtils.isEmpty(remotePath) || remotePath.replace("//","/").equals("/") || remotePath.replace("\\\\","\\").equals("\\");
            String newRemotePath = remotePath + (isRootPath ?  "" : File.separator) + f.getName();
            String newLocalPath = localPath + File.separator + f.getName();
            try {
                //切换ftp远程目录
                cwdOper(newRemotePath);
                FTPFile[] fs = ftp.listFiles();
                for (FTPFile f2 : fs) {
                    startDownload(f2, newLocalPath, newRemotePath);
                }
                //返回上一层目录
                ftp.changeWorkingDirectory("..");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            downloadFile(f, localPath);
        }
        return true;
    }

    private void downloadFile(FTPFile f, String localPath) {
        try {
            System.out.println(new String(Arrays.toString(ftp.doCommandAsStrings("pwd","")).getBytes("iso-8859-1"),"utf-8"));
            File localFile = new File(localPath + File.separator +  f.getName());
            if (localFile.exists()){
                return;
            }
            FileOutputStream fos = new FileOutputStream(localFile);
            ftp.retrieveFile(localFileName2ftp(f.getName()),fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String localFileName2ftp(String filename){
        try {
            filename =  new String(filename.getBytes("utf-8"),"iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return filename;
    }


    public boolean closeFtp(){
        if (ftp!= null && ftp.isConnected()){
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private FtpTool(){}

    /**
     * 如果没有端口默认是21
     * @param username 用户名
     * @param password 密码
     * @param hostname IP地址
     * @param basePath 服务器基路径
     */
    public FtpTool(String username, String password, String hostname,String basePath) {
        this(username, password, hostname, basePath, 21);
    }

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @param hostname ip地址
     * @param basePath 服务器基路径
     * @param port 端口（命令端口，默认是21）
     */
    public FtpTool(String username, String password, String hostname, String basePath, int port) {
        this.username = username;
        this.password = password;
        this.hostname = hostname;
        this.basePath = basePath;
        this.port = port;
    }


    public static void main(String[] args) {
        testdownload();
//        testupload();
    }

    public static void testdownload(){
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
        try {
            FtpTool ft = new FtpTool(ceshi_ftp_username,ceshi_ftp_password,ceshi_ftp_host,ceshi_ftp_download_basePath);
            ft.connectFtp();
            ft.download(ceshi_ftp_localPath,"");
            ft.closeFtp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void testupload(){
        String ceshi_ftp_password = ConfigProperties.getProperties("ceshi_ftp_password");
        String ceshi_ftp_username = ConfigProperties.getProperties("ceshi_ftp_username");
        String ceshi_ftp_host = ConfigProperties.getProperties("ceshi_ftp_host");
        int ceshi_ftp_port = Integer.valueOf(ConfigProperties.getProperties("ceshi_ftp_port"));
        String ceshi_ftp_upload_basePath = ConfigProperties.getProperties("ceshi_ftp_upload_basePath");
        String ceshi_ftp_download_basePath = ConfigProperties.getProperties("ceshi_ftp_download_basePath");
        String ceshi_ftp_filePath = ConfigProperties.getProperties("ceshi_ftp_filePath");
        String ceshi_ftp_localPath = ConfigProperties.getProperties("ceshi_ftp_localPath");

        try {
            FtpTool ft = new FtpTool(ceshi_ftp_username,ceshi_ftp_password,ceshi_ftp_host,ceshi_ftp_upload_basePath);
            ft.connectFtp();
            ft.upload(new File(ceshi_ftp_localPath));
            ft.closeFtp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
