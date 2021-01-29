package com.lagou.demo;/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: KouZhao
 * DATE: 2020/12/7
 * TIME: 13:58
 * JDK 11
 */

/**
 * @ClassName KouZhao
 * @Description TODO
 * @Auther xushu
 * @Date 2020/12/7
 * @Time 13:58
 * @Version v1.0
 **/
public class KouZhao {
    private String id;
    private String type;

    public KouZhao(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "KouZhao{" + "id='" + id + '\'' + ", type='" + type + '\'' + '}';
    }
}
