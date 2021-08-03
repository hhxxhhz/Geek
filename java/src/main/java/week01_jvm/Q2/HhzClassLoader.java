package week01_jvm.Q2;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;

/**
 * @author hhz
 * Created on 2021-08-03
 */
public class HhzClassLoader extends ClassLoader {

    private static final String PATH = "java/src/main/java/week01_jvm/Q2/Hello.xlass";

    public static void main(String[] args) {
        try {
            Class hello = new HhzClassLoader().findClass("Hello");
            Method method = hello.getMethod("hello");
            method.invoke(hello.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Class<?> findClass(String name) {
        byte[] byteArr = readFile(PATH);
        for (int i = 0; i < byteArr.length; i++) {
            byteArr[i] = (byte) (255 - byteArr[i]);
        }
        return defineClass(name, byteArr, 0, byteArr.length);
    }

    private static byte[] readFile(String path) {
        try {
            return Files.readAllBytes(new File(path).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
