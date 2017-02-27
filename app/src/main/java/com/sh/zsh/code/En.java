package com.sh.zsh.code;

/**
 * Created by zhush on 2017/2/27.
 * E-mail 405086805@qq.com
 * PS
 */
public class En {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "En{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
