package cn.jzteam.dao.entity.util;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

import cn.jzteam.dao.entity.metadata.Metadata;

/**
 * 加载指定包所在文件夹里的所有的class文件
 * 
 * @author zhujz
 *
 */
public class AnnotationEntityScan {
    private static final Logger logger = Logger.getLogger(AnnotationEntityScan.class);

    private static final String JAVA_TYPE = "class";

    private Map<Class<?>, Metadata> primaryKeyMap = new HashMap<Class<?>, Metadata>();

    public AnnotationEntityScan(String... packageNames) {
        String dirPath = PathUtil.getWebPath();
        for (String packageName : packageNames) {
            String classPath = dirPath + "classes/" + packageName.replaceAll("\\.", "\\/");
            loadClasses(classPath, packageName);
            logger.info("load packageName:" + classPath + ":=>" + packageName);
        }
        logger.info("AnnotationEntityRefer init");
    }

    /**
     * 加在指定文件下的类
     */
    private void loadClasses(final String classPath, String packageName) {
        File dir = new File(classPath);
        if (!dir.isDirectory()) {
            return;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            if (!file.isHidden()) {
                if (file.isDirectory()) {
                    loadClasses(file.getAbsolutePath(), packageName);
                } else {
                    String className = file.getAbsolutePath().replaceAll("\\\\", "\\.").replaceAll("\\/", "\\.");
                    String fileType = className.substring(className.lastIndexOf(".") + 1, className.length());
                    if (JAVA_TYPE.equals(fileType)) {
                        className = className.substring(className.lastIndexOf(packageName), className.lastIndexOf("."));
                        loadClass(className);
                    }
                }
            }
        }
    }

    /**
     * 根据jar文件加载类
     */
    @SuppressWarnings({ "resource", "unused" })
    private void loadJar(String jarFileName) throws Exception {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarFileName);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        Enumeration<JarEntry> en = jarFile.entries();
        while (en.hasMoreElements()) {
            JarEntry je = en.nextElement();
            String fileName = je.getName();
            String className = fileName.substring(0, fileName.lastIndexOf(".")).replace('/', '.');
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            if (JAVA_TYPE.equals(fileType)) {
                loadClass(className);
            }
        }
    }

    /**
     * 根据类名加载类
     */
    private void loadClass(String className) {
        Class<?> clazz = null;

        try {
            // logger.info(className);
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }

        primaryKeyMap.put(clazz, AnnotationUtil.getMetadata(clazz));
    }

}
