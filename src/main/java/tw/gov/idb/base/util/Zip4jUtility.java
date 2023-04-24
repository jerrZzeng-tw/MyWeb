package tw.gov.idb.base.util;

import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Zip4jUtility {
    
    private Zip4jUtility() {}

    /**
     * [壓縮]
     * 
     * @param fileMap 檔案Map (key: filename, value: 檔案內容byte[]) 必傳
     * @param pw 選傳
     * @param charset 選傳
     * 
     * @return byte[] 壓縮後的位元組
     * 
     * @throws IOException
     */
    public static byte[] zip(Map<String, byte[]> fileMap, String password, Charset charset) throws IOException {
        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }
        boolean needEncrypt = false;
        if (StringUtils.isNotBlank(password)) {
            needEncrypt = true;
        }
        ByteArrayOutputStream baos = null;
        ZipOutputStream zos = null;
        try {
            baos = new ByteArrayOutputStream();
            if (needEncrypt) {
                zos = new ZipOutputStream(baos, password.toCharArray(), charset);
            } else {
                zos = new ZipOutputStream(baos, charset);
            }
            //進行壓縮
            for (Entry<String, byte[]> e : fileMap.entrySet()) {
                final ZipParameters zipParameters = createZipParameters(e.getKey(), needEncrypt);
                zos.putNextEntry(zipParameters);
                zos.write(e.getValue());
                zos.flush();
                zos.closeEntry();
            }
            zos.flush();
            baos.flush();
            zos.close();
            
            return baos.toByteArray();
        } catch (IOException e) {
            throw e;
        } finally {
            quietlyClose(zos);
            quietlyClose(baos);
        }
    }
    
    /**
     * [解壓縮]
     * 
     * @param byte[] data 壓縮檔位元組 必傳
     * @param pw 選傳
     * @param charset 選傳
     * 
     * @return Map<String, byte[]> 資料集(key: filename, value: 檔案內容byte[])
     * 
     * @throws IOException
     */
    public static Map<String, byte[]> unzip(byte[] data, String password, Charset charset) throws IOException {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(data);
            return unzip(bais, password, charset);
        } catch (IOException e) {
            throw e;
        } finally {
            quietlyClose(bais);
        }
    }
    
    /**
     * [解壓縮]
     * 
     * @param InputStream is 壓縮檔資料輸入流 必傳
     * @param pw 選傳
     * @param charset 選傳
     * 
     * @return Map<String, byte[]> 資料集(key: filename, value: 檔案內容byte[])
     * 
     * @throws IOException
     */
    public static Map<String, byte[]> unzip(InputStream is, String password, Charset charset) throws IOException {
        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }
        boolean needDecrypt = false;
        if (StringUtils.isNotBlank(password)) {
            needDecrypt = true;
        }
        
        ZipInputStream zis = null;
        try {
            if (needDecrypt) {
                zis = new ZipInputStream(is, password.toCharArray(), charset);
            } else {
                zis = new ZipInputStream(is, charset);
            }
            
            Map<String, byte[]> map = new HashMap<>();
            
            LocalFileHeader zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                String entryName = zipEntry.getFileName();
                if (!zipEntry.isDirectory()) {
                    byte[] fileContent = StreamUtils.copyToByteArray(zis);
                    map.put(entryName, fileContent);
                }
            }
            return map;
        } catch (IOException e) {
            throw e;
        } finally {
            quietlyClose(zis);
        }
    }
    
    private static ZipParameters createZipParameters(String filename, boolean needEncrypt) {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
        zipParameters.setEncryptFiles(needEncrypt);
        zipParameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
        zipParameters.setFileNameInZip(filename);
        return zipParameters;
    }

    private static void quietlyClose(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
    
//    public static void main(String[] args) {
//        Map<String, byte[]> m = new HashMap<>();
//        byte[] data = null;
//        
//        int buflen = 4096;
//        byte[] buffer = new byte[buflen];
//        File file = new File("D:/test.txt");
//        if (!file.exists()) {
//            System.out.println("D:/test.txt檔案不存在");
//            return;
//        }
//        try (FileInputStream fis = new FileInputStream(file);
//             BufferedInputStream bis = new BufferedInputStream(fis);
//             ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ) {
//            while (bis.read(buffer, 0, buflen) != -1) {
//                baos.write(buffer);
//            }
//            baos.flush();
//            data = baos.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (data == null) {
//            return;
//        }
//        for (byte b : data) {
//            System.out.print(b + ", ");
//        }
//        System.out.println();
//        System.out.println("-----");
//        System.out.println("解壓縮得:\n");
//        
//        m.put("測試testfile1.txt", data);
//        m.put("測試testfile2.txt", data);
//        
//        final String pass = "xxxx";
//        try {
//            byte[] zipped = Zip4jHelper.zip(m, pass, StandardCharsets.UTF_8);
//            
//            Map<String, byte[]> unzipped = Zip4jHelper.unzip(zipped, pass, StandardCharsets.UTF_8);
//            
//            for (Entry<String, byte[]> e : unzipped.entrySet()) {
//                final String filename = e.getKey();
//                final byte[] content = e.getValue();
//                System.out.println("filename:" + filename);
//                for (byte b : content) {
//                    System.out.print(b + ", ");
//                }
//                System.out.println("\n========================");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
