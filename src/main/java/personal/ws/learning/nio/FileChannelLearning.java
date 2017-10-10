package personal.ws.learning.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/10/10
 * @project：WSService
 */
public class FileChannelLearning {

    public static String separator = File.separator;
    public static String name = "D:" + separator + "www" + separator + "recharge-manage-bs.2017-09-15.log";

    public static void main(String args[]) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(name, "rw");
        FileChannel inChannel = aFile.getChannel();

        int KB = 1024;
        ByteBuffer buf = ByteBuffer.allocate(4 * KB);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
            System.out.println();
        }
        aFile.close();
    }

}
