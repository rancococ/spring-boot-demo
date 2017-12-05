package com.catvgd.springbootdemo.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.IOUtils;

/**
 * IO工具
 * 
 * @author zhuyong
 * @date 2016年12月22日
 */
public class IOUtil extends IOUtils {

    /**
     * byteToObject
     * 
     * @param bytes
     * @return
     */
    public static Object byteToObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            // bytearray to object
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (Exception e) {
            return null;
        } finally {
            if (bis != null) {
                try {
                    IOUtils.closeQuietly(bis);
                } catch (Exception e) {
                }
            }
            if (ois != null) {
                try {
                    IOUtils.closeQuietly(ois);
                } catch (Exception e) {
                }
            }
        }
        return obj;
    }

    /**
     * objectToByte
     * 
     * @param obj
     * @return
     */
    public static byte[] objectToByte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            bytes = bos.toByteArray();
        } catch (Exception e) {
            return null;
        } finally {
            if (bos != null) {
                try {
                    IOUtils.closeQuietly(bos);
                } catch (Exception e) {
                }
            }
            if (oos != null) {
                try {
                    IOUtils.closeQuietly(oos);
                } catch (Exception e) {
                }
            }
        }
        return bytes;
    }

    /**
     * byteToFile
     * 
     * @param bytes
     * @param filePathAndName
     * @return
     */
    public static File byteToFile(byte[] bytes, String filePathAndName) {
        File file = null;
        FileOutputStream fos = null;
        try {
            file = new File(filePathAndName);
            fos = new FileOutputStream(file);
            IOUtils.write(bytes, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    IOUtils.closeQuietly(fos);
                } catch (Exception e) {
                }
            }
        }
        return file;
    }

    /**
     * fileToByte
     * 
     * @param file
     * @return
     */
    public static byte[] fileToByte(File file) {
        byte[] bytes = null;
        if (file == null) {
            return null;
        }
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            IOUtils.copy(fis, bos);
            bytes = bos.toByteArray();
        } catch (IOException e) {
            return null;
        } finally {
            if (fis != null) {
                try {
                    IOUtils.closeQuietly(fis);
                } catch (Exception e) {
                }
            }
            if (bos != null) {
                try {
                    IOUtils.closeQuietly(bos);
                } catch (Exception e) {
                }
            }
        }
        return bytes;
    }
}
