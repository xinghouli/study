package com.lance.study.classload;

import java.util.Properties;

//launcher.Class
public class BootstrapClassLoader extends ClassLoader{
    //加载器顺序
    private static String bootClassPath = System.getProperty("sun.boot.class.path");

    private static String extClassPath = System.getProperty("java.ext.dirs");

    private static String appClassPath = System.getProperty("java.class.path");

    public static void main(String[] args) {
        System.out.println(bootClassPath);
        System.out.println(extClassPath);
        System.out.println(appClassPath);

        // sun.misc.Launcher$AppClassLoader
        ClassLoader classLoader = BootstrapClassLoader.class.getClassLoader();
        System.out.println(classLoader);

        // sun.misc.Launcher$ExtClassLoader
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);

        // null
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        //null
        ClassLoader intClassLoader = int.class.getClassLoader();
        System.out.println(intClassLoader);

        Properties properties = System.getProperties();
        System.out.println(properties);


    }
	//如果不想打破双亲委派模型，那么只需要重写findClass方法即可
	//如果想打破双亲委派模型，那么就重写整个loadClass方法
    //双亲委派
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    //加载类
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
