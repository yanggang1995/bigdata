/**
 * TODO
 *
 * @author Y.G
 * @date 2020/4/8 20:21
 **/
import java.io.*;
import java.util.*;

public class HaffmanCode {
    //存放对应字符的Ascci码,和对应路径
    private static Map<Byte, String> huffmanCode = new HashMap<Byte, String>();
    //补全最后一位需要加0的个数
    private static Integer zeloCount = 0;

    public static void main(String[] args) {
//        //文件压缩
//        String src = "D:/1.png";
//        String dst = "D:/src.zip";
//        zipFile(src, dst);
//        System.out.println("压缩完成!");
//
//        //解压
//        String zipFile = "D:/src.zip";
//        String dstFile = "D:/2.png";
//        unZipFile(zipFile, dstFile);
//        System.out.println("解压成功!");

//        String content = "i like java do you like a java very much";
//        byte[] contentBytes = content.getBytes();
//        //数据压缩
//        byte[] zipArr = huffmanZip(contentBytes);
//        System.out.println(Arrays.toString(zipArr));
//
//        //数据解压
//        byte[] decodeArr = decode(zipArr, huffmanCode);
//         System.out.println(new String(decodeArr).toString());
//        String str = "can you can a can as a caner can?";
        String str = "can you can a can as a can canner can a can.";
        byte[] encodeArr = huffmanZip(str.getBytes());
        byte[] decodeArr = decode(encodeArr, huffmanCode);
        System.out.println(new String(decodeArr));

    }

    //压缩文件
    private static void zipFile(String src, String dst) {
        InputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(src);
            //创建一个和文件一样大的字节数组
            byte[] b = new byte[is.available()];
            //将文件以字节流的形式读到字节数组中
            is.read(b);
            byte[] huffmanCodeArr = huffmanZip(b);
            os = new FileOutputStream(dst);
            oos = new ObjectOutputStream(os);
            //将字节数组,和哈夫曼编码以对象的形式输出去
            oos.writeObject(huffmanCodeArr);
            oos.writeObject(huffmanCode);
            //将字节数组最后一位要补0的个数传过去
            oos.writeObject(zeloCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    //解压文件
    /*
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压的路径
     * @return : void
     * @date : 2020/3/10 12:18
     */
    private static void unZipFile(String zipFile, String dstFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            //readObject()是按照你存入时的顺序依次读取对象
            byte[] huffmanCodeArr = (byte[]) ois.readObject();
            huffmanCode = (Map<Byte, String>) ois.readObject();
            zeloCount = (Integer) ois.readObject();
            // System.out.println("zeloCount:"+zeloCount);
            byte[] contentArr = decode(huffmanCodeArr, huffmanCode);
            os=new FileOutputStream(dstFile);
            os.write(contentArr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                ois.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //解码
    /*
     * @param bytes  压缩后的字节数组
     * @param huffmanCode 字符,和字符对应的路径组成的map
     * @return : byte[] 返回元数组
     * @date : 2020/3/9 14:26
     */
    private static byte[] decode(byte[] bytes, Map<Byte, String> huffmanCode) {
        //将压缩后的字节数组变为用二进制路径表示字符的字符串,然后将字符串中路径变成对应字符
        StringBuilder stringBuilder = byteArrtransfertoString(bytes);
        Map<String, Byte> map = new HashMap<String, Byte>();
        //将huffmanCode反转,'a'->101 编程101->'a',将路径转为对应的字符
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //将路径翻译成对应的字符
        List<Byte> list = getChar(map, stringBuilder);
        //将list变为数组
        byte[] contenArr = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            contenArr[i] = list.get(i);
        }
        return contenArr;
    }

    //将压缩后的字节数组变为用二进制路径表示字符的字符串
    private static StringBuilder byteArrtransfertoString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转化为二进制字符串
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            boolean flag = true;
            String str = byteTransfertoBinaryString(b);
            if (i == bytes.length - 1) {
                //   System.out.println("str1:"+str);
                str = str.substring(0, 8-zeloCount);
                //   System.out.println("str2:"+str);
            }
            stringBuilder.append(str);
        }
        //System.out.println("stringBuilder2:"+stringBuilder);
        return stringBuilder;
    }

    private static List getChar(Map<String, Byte> map, StringBuilder stringBuilder) {
        List<Byte> list = new ArrayList<Byte>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            //取出路径对应的字符
            while (flag) {
                if (!map.containsKey(stringBuilder.substring(i, i + count))) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(map.get(stringBuilder.substring(i, i + count)));
            i += count;
        }
        return list;
    }

    //将byte转化为二进制存入String
    private static String byteTransfertoBinaryString(byte b) {
        int temp = b;
        temp |= 256;
        String str = Integer.toBinaryString(temp);
        int len = str.length();
        return str.substring(len - 8);

    }

    //将所有步骤封装
    /*
     * @param bytes 原字符串对应的byte数组
     * @return : byte[] 压缩完后后将要发送的数组
     * @date : 2020/3/9 12:30
     */
    private static byte[] huffmanZip(byte[] bytes) {
        //现将数组变为装有node节点的list集合
        List<Node> nodes = getNodeList(bytes);
        //得到对应的huffman树
        Node root = getHuffman(nodes);
        getAllCodes(root);
        byte[] huffmanZipArr = getZipArr(bytes, huffmanCode);
        return huffmanZipArr;
    }

    //得到压缩后的字节数组
    /*
     * @param bytes 原字符串对应的byte数组,按照这个数组字符排列顺序打印字符对应的路径
     * @param huffmanCode 存放字符,和字符路径的map
     * @return : byte[] 将所有路劲汇总每8个当成一位
     * @date : 2020/3/9 12:07
     */
    private static byte[] getZipArr(byte[] bytes, Map<Byte, String> huffmanCode) {
        //按照bytes中的字符排序顺序用路径替代字符存入stringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCode.get(b));
        }
        //将stringBuilder每8个二进制当做一个byte类型存入字节数组
        int len = 0;
        //用于补齐不足8位的地方
        String str = "";
        //确定字节数组长度
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
            //不足8位的地方需要补0的个数
            zeloCount = 8 - (stringBuilder.length() % 8);
            for (int i = 0; i < zeloCount; i++) {
                str += "0";
                //  System.out.println("zelo:"+str);
            }
            stringBuilder.append(str);
        }
        // System.out.println("stringBuilder1:"+stringBuilder);
        //将stringbuild里面的二进制路径字符串每8位进行翻译成十进制
        return stringTransfertoByteArr(stringBuilder, len);
    }

    //将stringbuild里面的二进制路径字符串每8位进行翻译成十进制
    private static byte[] stringTransfertoByteArr(StringBuilder stringBuilder, int len) {
        byte[] huffmanCodeArr = new byte[len];
        //huffmanCodeArr的索引
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            if (i + 8 > stringBuilder.length()) {//不够8位
                huffmanCodeArr[index] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
            } else {
                huffmanCodeArr[index] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
            }
            index++;
        }
        return huffmanCodeArr;
    }

    //得到所有字符的路径
    private static void getAllCodes(Node root) {
        if (root == null) {
            return;
        }
        //记录路径,遍历huffman树时往左为0,往右为1直到找到对应字符;
        StringBuilder stringBuilder = new StringBuilder();
        getcode(root.left, "0", stringBuilder);
        getcode(root.right, "1", stringBuilder);

    }

    /*
     * @param node 当前需要遍历的节点
     * @param code 值为"0"或者"1"代表向左或者向右
     * @param stringBuilder
     * @return : void
     * @date : 2020/3/8 13:20
     */
    private static void getcode(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.value == null) {//证明这个点点为非叶子节点
                //向左递归
                getcode(node.left, "0", stringBuilder2);
                //向右递归
                getcode(node.right, "1", stringBuilder2);

            } else {
                huffmanCode.put(node.value, stringBuilder2.toString());
            }
        }
    }

    private static Node getHuffman(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //将数组变为List<Node>
    private static List<Node> getNodeList(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        //遍历bytes,记录每个value出现的次数,出现的次数就是huffman树的权值
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        for (Map.Entry<Byte, Integer> map : counts.entrySet()) {
            nodes.add(new Node(map.getKey(), map.getValue()));
        }
        return nodes;
    }

    //前序遍历
    private static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        root.preOrder();
    }

}

class Node implements Comparable<Node> {
    public Byte value;
    public int weight;
    Node left;
    Node right;

    public Node(Byte value, int weight) {
        this.value = value;
        this.weight = weight;

    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
