package com.yg.struct.tree.huffman;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

/**
 * 利用Huffman树编解码文件
 *
 * @author Y.G
 * @date 2020/4/7 15:09
 **/
public class ZipFile {

    public static void zipFile(String fileName) throws IOException {
        InputStream in = new FileInputStream(fileName);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        System.out.println(Arrays.toString(bytes));
        in.close();
        HuffManTree huffManTree = new HuffManTree(bytes);
        byte[] encodeArr = huffManTree.encode(bytes);
        System.out.println(Arrays.toString(encodeArr));
        OutputStream os = new FileOutputStream(fileName.concat(".huf"));
        ObjectOutputStream objOs = new ObjectOutputStream(os);
        objOs.writeObject(encodeArr);
        objOs.writeObject(huffManTree.huffmanDecode());
        objOs.close();
        os.close();
    }

    public static void unzipFile(String fileName, String newFileName) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(is);
        byte[] encodeArr = (byte[]) ois.readObject();
        Map<String, Byte> decodeMap = (Map<String, Byte>) ois.readObject();
        ois.close();
        is.close();
        byte[] decodeArr = HuffManTree.decode(encodeArr, decodeMap);
        OutputStream os = new FileOutputStream(newFileName);
        os.write(decodeArr);
        os.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "D:\\YG\\project\\leecode\\src\\main\\resources\\test.png";
        zipFile(fileName);
        unzipFile(fileName + ".huf", "D:\\YG\\project\\leecode\\src\\main\\resources\\zipTest1.png");
    }
}
