package com.yg.struct.tree.avl;

import java.util.Objects;

/**
 * AVL树 (AVL Adelson Velsky Landis:阿德尔森·维尔斯基·兰迪斯), 平衡二叉树(BalancedBinaryTree)
 * 1、前提是二叉排序树BST
 *
 * @author Y.G
 * @date 2020/4/9 22:32
 **/
public class AVLTree {
    private BinaryNode root;

    public AVLTree() {
    }

    public AVLTree addNode(int data) {
        if (root == null) {
            root = new BinaryNode(data);
            return this;
        }
        BinaryNode tmpNode = root;
        while (true) {
            if (data <= tmpNode.getData()) {
                if (tmpNode.leftNode() == null) {
                    tmpNode.setLeftNode(new BinaryNode(data));
                    break;
                } else {
                    tmpNode = tmpNode.leftNode();
                }
            } else {
                if (tmpNode.rightNode() == null) {
                    tmpNode.setRightNode(new BinaryNode(data));
                    break;
                } else {
                    tmpNode = tmpNode.rightNode();
                }
            }
        }
        // 构建平衡二叉树
        BinaryNode rotateNode = checkAVL(root);
        if (rotateNode != null) {
            System.out.println(rotateNode);
            BinaryNode avlParent = searchParent(rotateNode);
            // 左子树高度大于右子树
            if (rotateNode.leftHeight() - rotateNode.rightHeight() > 1) {
                // 双旋转：满足右旋转时，且其左节点的 左子树高度 < 右子树高度
                if (rotateNode.leftNode().leftHeight() < rotateNode.leftNode().rightHeight()) {
                    leftRotate(rotateNode.leftNode(), rotateNode);
                }
                // 单旋转：最高子树的叶子节点在左边
                rightRotate(rotateNode, avlParent);
                // 左子树高度小于右子树
            } else {
                // 双旋转：满足左旋转时，且其右子节点的 左子树高度 > 右子树高度
                if (rotateNode.rightNode().leftHeight() > rotateNode.rightNode().rightHeight()) {
                    rightRotate(rotateNode.rightNode(), rotateNode);
                }
                // 单旋转：最高子树的叶子节点在右边
                leftRotate(rotateNode, avlParent);
            }
        }
        return this;
    }

    /**
     * 检查AVL树，返回不满足的子树根节点
     *
     * @param parentNode 根节点
     * @return 子树根节点
     */
    private BinaryNode checkAVL(BinaryNode parentNode) {
        if (parentNode == null) {
            return null;
        }
        BinaryNode notAVL = null;
        if (Math.abs(parentNode.leftHeight() - parentNode.rightHeight()) > 1) {
            notAVL = parentNode;
        }
        if (notAVL == null && parentNode.leftNode() != null) {
            notAVL = checkAVL(parentNode.leftNode());
        }
        if (notAVL == null && parentNode.leftNode() != null) {
            notAVL = checkAVL(parentNode.rightNode());
        }
        return notAVL;
    }

    /**
     * 左旋转
     *
     * @param rotateNode 需要旋转的节点
     * @param avlParent  旋转节点的父节点
     */
    private void leftRotate(BinaryNode rotateNode, BinaryNode avlParent) {
        BinaryNode rotateRight = rotateNode.rightNode();
        rotateNode.setRightNode(rotateRight.leftNode());
        rotateRight.setLeftNode(rotateNode);
        if (avlParent == null) {
            root = rotateRight;
        } else {
            if (rotateNode == avlParent.leftNode()) {
                avlParent.setLeftNode(rotateRight);
            } else {
                avlParent.setRightNode(rotateRight);
            }
        }
    }

    /**
     * 右旋转
     *
     * @param rotateNode 需要旋转的节点
     * @param avlParent  需要旋转节点的父节点
     */
    private void rightRotate(BinaryNode rotateNode, BinaryNode avlParent) {
        BinaryNode rotateLeft = rotateNode.leftNode();
        rotateNode.setLeftNode(rotateLeft.rightNode());
        rotateLeft.setRightNode(rotateNode);
        if (avlParent == null) {
            root = rotateLeft;
        } else {
            if (rotateNode == avlParent.leftNode()) {
                avlParent.setLeftNode(rotateLeft);
            } else {
                avlParent.setRightNode(rotateLeft);
            }
        }
    }


    /**
     * 根据指定权值，查找节点
     *
     * @param data 权值
     * @return 节点
     */
    public BinaryNode search(int data) {
        BinaryNode nowNode = root;
        while (Objects.nonNull(nowNode)) {
            int nowData = nowNode.getData();
            if (data == nowData) {
                return nowNode;
            }
            nowNode = data < nowData ? nowNode.leftNode() : nowNode.rightNode();
        }
        return null;
    }

    /**
     * 查找权值对应的节点及其父节点
     *
     * @param data 权值
     * @return [父节点，查找到的节点]，若没有查找到返回null
     */
    public BinaryNode[] searchAndParent(int data) {
        BinaryNode parentNode = null;
        BinaryNode nowNode = root;
        while (Objects.nonNull(nowNode)) {
            int nowData = nowNode.getData();
            if (data == nowData) {
                return new BinaryNode[]{parentNode, nowNode};
            }
            parentNode = nowNode;
            nowNode = data < nowData ? nowNode.leftNode() : nowNode.rightNode();
        }
        return null;
    }

    /**
     * 查找权值对应的节点及其父节点
     *
     * @param node 节点
     * @return [父节点，查找到的节点]，若没有查找到返回null
     */
    public BinaryNode searchParent(BinaryNode node) {
        BinaryNode parentNode = null;
        BinaryNode nowNode = root;
        while (Objects.nonNull(nowNode)) {
            if (node == nowNode) {
                return parentNode;
            }
            parentNode = nowNode;
            nowNode = node.getData() < nowNode.getData() ? nowNode.leftNode() : nowNode.rightNode();
        }
        return null;
    }

    /**
     * 删除排序二叉树中的节点
     *
     * @param value 删除的权值
     * @return 删除结果
     */
    public boolean delete(int value) {
        BinaryNode[] nodeAndParent = searchAndParent(value);
        if (Objects.isNull(nodeAndParent)) {
            return false;
        }
        BinaryNode parent = nodeAndParent[0];
        BinaryNode node = nodeAndParent[1];
        BinaryNode newNode;
        // 被删除节点 左右子树都存在，以右子树最左节点替换，最左节点的子树为其父节点的左子树
        // 被删除节点，有一个或0个子树时，直接用子树替换
        if (Objects.nonNull(node.leftNode()) && Objects.nonNull(node.rightNode())) {
            // 寻找
            BinaryNode tmpParent = null;
            BinaryNode tmpNode = node.rightNode();
            while (tmpNode.leftNode() != null) {
                tmpParent = tmpNode;
                tmpNode = tmpNode.leftNode();
            }
            // tmpParent == null 最左子树父节点为被删除节点，最左节点左子树为被删除节点左子树
            // tmpParent != null 最左节点左子树为被删除节点左子树，最左节点右子树为被删除节点右子树，最左节点父节点左子树为最左节点右子树
            if (Objects.isNull(tmpParent)) {
                newNode = tmpNode;
                tmpNode.setLeftNode(node.leftNode());
            } else {
                tmpParent.setLeftNode(tmpNode.rightNode());
                tmpNode.setLeftNode(node.leftNode());
                tmpNode.setRightNode(node.rightNode());
                newNode = tmpNode;
            }
        } else {
            newNode = Objects.nonNull(node.leftNode()) ? node.leftNode() : node.rightNode();
        }
        // 父节点不为空时，用新的子节点替换被删除的子节点
        // 父节点为空时，说明被删除节点为根节点，用新的子节点替换根节点
        if (Objects.nonNull(parent)) {
            // 不是左节点就是右节点
            if (parent.leftNode() != null && parent.leftNode() == node) {
                parent.setLeftNode(newNode);
            } else {
                parent.setRightNode(newNode);
            }
        } else {
            this.root = newNode;
        }
        return true;
    }

    /**
     * 中序遍历打印
     */
    public void middlePrint() {
        if (root == null) {
            return;
        }
        middle(root);
        System.out.println();
    }

    /**
     * 前序遍历
     * 1、根节点 =》左节点 =》右节点
     *
     * @param node 节点
     */
    private void middle(BinaryNode node) {
        if (Objects.nonNull(node.leftNode())) {
            middle(node.leftNode());
        }
        System.out.print(node.getData() + ":" + node.height() + " ");
        if (Objects.nonNull(node.rightNode())) {
            middle(node.rightNode());
        }
    }
}
