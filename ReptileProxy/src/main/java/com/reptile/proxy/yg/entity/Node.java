package com.reptile.proxy.yg.entity;

/**
 * 节点
 *
 * @author Y.G
 * @date 2020/4/10 14:15
 **/
public abstract class Node<T> {
    protected T data;
    protected Node next;

    /**
     * 获取下一个节点
     *
     * @return 下一个节点
     */
    public abstract Node next();

    /**
     * 添加节点
     *
     * @param node 新增节点
     */
    public abstract void setNext(Node node);

    /**
     * 获取节点中的数据
     *
     * @return 数据
     */
    public abstract T getData();
}
