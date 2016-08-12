package org.ligson.searchbox.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * Created by ligson on 2016/8/12.
 */
public class ConfigHolder {
    private static final File configFile = new File(Thread.currentThread().getContextClassLoader().getResource("config.json").getFile());
    private static Logger logger = LoggerFactory.getLogger(ConfigHolder.class);
    private List<String> indexDirs;
    private static ConfigHolder instance;


    public ConfigHolder() {
    }

    public static synchronized ConfigHolder getInstance() {
        if (instance == null) {
            JSONReader reader = null;
            try {

                reader = new JSONReader(new FileReader(configFile));
                System.out.println(reader.readString());
                //instance = JSONObject.parseObject(new FileInputStream(configFile), ConfigHolder.class);
                reader.close();
            } catch (Exception e) {
                logger.error("读取配置文件失败:{}", e.getMessage());
            }

        }
        return instance;
    }

    public synchronized void refresh() {
        try {
            JSONWriter writer = new JSONWriter(new FileWriter(configFile));
            writer.writeObject(instance);
            writer.close();
        } catch (IOException e) {
            logger.error("写入配置文件失败:{}", e);
        }
    }

    public List<String> getIndexDirs() {
        return indexDirs;
    }

    public void setIndexDirs(List<String> indexDirs) {
        this.indexDirs = indexDirs;
    }

    @Override
    public String toString() {
        return "ConfigHolder{" +
                "indexDirs=" + indexDirs +
                '}';
    }

    public static void main(String[] args) throws Exception {
        ConfigHolder holder = getInstance();
        System.out.println(holder);
        holder.getIndexDirs().add("D:/test");
        holder.refresh();

    }
}
