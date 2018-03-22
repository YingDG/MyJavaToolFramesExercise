package yingdg.exercise.freemarker;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import freemarker.template.Version;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public abstract class FreemarkerUtil {
    private static final Version VERSION = new Version("2.3.25");
    private static final File TEMPLEATE_FILE = new File("./config/template");
    private static Configuration conf;

    static {
        conf = new Configuration(VERSION);
        try {
            conf.setDirectoryForTemplateLoading(TEMPLEATE_FILE);
            conf.setObjectWrapper(new DefaultObjectWrapper(VERSION));
            // 设置变量值可为空
            conf.setClassicCompatible(true);
            // 设置所有模板ftl文件共享变量
            conf.setSharedVariable("xmlHead", "<?xml version=\"1.0\" encoding=\"GBK\"?>");
        } catch (IOException | TemplateModelException e) {
            e.printStackTrace();
        }
    }

    private FreemarkerUtil() {

    }

    public static String generate(Map<?, ?> dataMap, boolean isXmlOrJson) {
        try (StringWriter stringWriter = new StringWriter()) {
            Template t;
            if (isXmlOrJson) {
                t = conf.getTemplate("TemplateXml.ftl");
            } else {
                t = conf.getTemplate("TemplateJson.ftl");
            }
            t.process(dataMap, stringWriter);

            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            return "";
        }
    }

}
