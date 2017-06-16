package cn.jzteam.dao.entity.utils;

import java.io.File;
import java.net.URL;

public class PathUtil {

    /**
     * 描述：获得项目classes所在的文件夹的绝对路径
     */
    public static String getWebPath() {
        String path = null;
        String className = PathUtil.class.getName().replace('.', '/') + ".class";
        System.out.println("cn:" + className);
        ClassLoader cl = PathUtil.class.getClassLoader();
        if (cl != null) {
            URL url = cl.getResource(className);
            if (url != null) {
                path = url.getFile();
                System.out.println("path: " + path);
                int begin = 0;
                int isFile = path.indexOf("file:/");
                if (isFile >= 0) {
                    begin = isFile + 5;
                }
                int end = path.indexOf("classes/" + className);
                if (end < 0) {
                    end = path.indexOf("lib/");
                    if (end < 0) {
                        int tmpend = path.indexOf("!/");
                        end = path.substring(0, tmpend).lastIndexOf("/");
                    }
                }
                path = path.substring(begin, end);
                path = new File(path).getAbsolutePath().replace('\\', '/') + "/";
            }
        }

        return path;
    }

    public static String getSystemPath() {
        String _systemPath = null;
        if (_systemPath == null) {
            String res = getWebPath();
            if (res.indexOf("WEB-INF/") > 0 && res.length() > 10) {
                res = res.substring(0, res.lastIndexOf("/", res.length() - 12)) + "/";
            }
            _systemPath = res;
        }
        return _systemPath;
    }

    public static void main(String[] args) {
        String webPath = PathUtil.getWebPath();
        System.out.println(webPath);
        System.out.println(webPath.lastIndexOf("/", webPath.length() - 12));

        System.out.println(webPath.replaceAll("/", "."));
    }
}
