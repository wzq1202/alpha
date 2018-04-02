package com.alpha.java;

import java.io.*;

/**
 * Created by ssports on 2018/3/30.
 */
public class AlphaClassLoader extends ClassLoader {
    private String path;
    public AlphaClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = getCls(name);
        return defineClass(name,data,0,data.length);
    }

    private byte[] getCls(String name) {
        String fileName = getFileName(name);

        File file = new File(path,fileName);
        FileInputStream is = null;
        ByteArrayOutputStream bos = null;
        byte[] data = null;
        try {
            is = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            int len = 0;
            byte b = 0;
            try {
                while ((len = is.read()) != -1) {
                    b = (byte) len;
                    bos.write(b);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            data  = bos.toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                is.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    //获取要加载 的class文件名
    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if(index == -1){
            return name+".class";
        }else{
            return name.substring(index+1)+".class";
        }
    }

}
