package com.catvgd.springbootdemo.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * XML工具类
 * 
 * @author zhuyong
 * @date 2016年12月22日
 */
public class XmlUtil {

    /**
     * from object to xml
     * 
     * @param clazz
     * @param configFile
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T XmlToObject(Class<T> clazz, File configFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller localUnmarshaller = context.createUnmarshaller();
        return (T) localUnmarshaller.unmarshal(configFile);
    }

    /**
     * from object to xml
     * 
     * @param clazz
     * @param configUrl
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T XmlToObject(Class<T> clazz, URL configUrl) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller localUnmarshaller = context.createUnmarshaller();
        return (T) localUnmarshaller.unmarshal(configUrl);
    }

    /**
     * from object to xml file
     * 
     * @param ob
     * @param path
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public static void ObjectToXmlFile(Object ob, String path) throws JAXBException, FileNotFoundException {
        Class<? extends Object> local = ob.getClass();
        JAXBContext context = JAXBContext.newInstance(new Class[] { local });
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(ob, new FileOutputStream(new File(path)));
    };
}
