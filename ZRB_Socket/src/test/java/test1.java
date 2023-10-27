import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

import static java.lang.Thread.sleep;

public class test1 {

    public static void main(String[] args) throws Exception {
//        Socket socket = new Socket("127.0.0.1",6600);
//        do {
//            String s = "1";
//            OutputStream outputStream = socket.getOutputStream();
//            outputStream.write(s.getBytes(Charset.defaultCharset()));
//            System.out.println("休眠开始");
//            sleep(2000);
//            System.out.println("休眠结束");
////            byte[] bytes = new byte[1024];
////            InputStream inputStream = socket.getInputStream();
////            int read = inputStream.read(bytes);
////            System.out.println(new String(bytes,0,read, Charset.defaultCharset()));
//        }while (true);
        Integer i = -1;
        Integer j = -1;
        System.out.println(i.equals(j));
    }

}
