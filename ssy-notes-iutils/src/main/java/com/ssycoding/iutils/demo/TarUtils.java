package com.ssycoding.iutils.demo;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @Comments: tar压缩工具类
 * @Author: Sean
 * @Date: 2020-03-25 17:02
 */
public class TarUtils {
    private static final int BUFFER_SIZE = 2 * 1024;

    /**
     * 私有化构造器，避免创建不必要的对象
     * （不需要实例化的类，应该构造器私有化）
     */
    private TarUtils() {}

    /**
     * 压缩成TAR 方法1
     *
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出流
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         <p>
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toTar(String srcDir, OutputStream out, boolean KeepDirStructure)
            throws RuntimeException {

        long start = System.currentTimeMillis();
        TarOutputStream tos = null;
        try {
            tos = new TarOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile, tos, sourceFile.getName(), KeepDirStructure);
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");

        } catch (Exception e) {
            throw new RuntimeException("tar error from TarUtils", e);
        } finally {
            if (tos != null) {
                try {
                    tos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 压缩成TAR 方法2
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */

    public static void toTar(List<File> srcFiles, OutputStream out) throws RuntimeException {
        long start = System.currentTimeMillis();
        TarOutputStream tos = null;
        try {
            tos = new TarOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                TarEntry entry = new TarEntry(srcFile.getName());
                entry.setSize(srcFile.length());
                tos.putNextEntry(entry);
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    tos.write(buf, 0, len);
                }
                tos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("tar error from TarUtils", e);
        } finally {
            if (tos != null) {
                try {
                    tos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param tos              tar输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         <p>
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */

    private static void compress(File sourceFile, TarOutputStream tos, String name,
                                 boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向tar输出流中添加一个tar实体，构造器中name为tar实体的文件的名字
            TarEntry entry = new TarEntry(name);
            entry.setSize(sourceFile.length());
            tos.putNextEntry(entry);
            // copy文件到tar输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                tos.write(buf, 0, len);
            }
            // Complete the entry
            tos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    tos.putNextEntry(new TarEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    tos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, tos, sourceFile + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, tos, file.getName(), KeepDirStructure);
                    }
                }
            }
        }
    }
}
