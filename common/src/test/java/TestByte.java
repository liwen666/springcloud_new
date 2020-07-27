import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class TestByte {

    @Test
    public void name() {

        byte[] byte_1 = "aaa".getBytes();
        byte[] byte_2 = "bbb".getBytes();

        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        System.out.println(new String(byte_3, 0, byte_3.length, Charset.forName("UTF8")));
    }

    /**
     * 截取byte数组   不改变原数组
     * @param b 原数组
     * @param off 偏差值（索引）
     * @param length 长度
     * @return 截取后的数组
     */
    public byte[] subByte(byte[] b,int off,int length){
        byte[] b1 = new byte[length];
        System.arraycopy(b, off, b1, 0, length);
        return b1;
    }


    /**
     * 将所有的字节数组全部写入内存中，之后将其转化为字节数组
     */
    public static void main(String[] args) throws IOException {
        String str1 = "132";
        String str2 = "asd";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(str1.getBytes());
        os.write(str2.getBytes());
        byte[] byteArray = os.toByteArray();
        System.out.println(new String(byteArray));
        os.close();
    }

}
