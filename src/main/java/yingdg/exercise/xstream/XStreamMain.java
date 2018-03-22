package yingdg.exercise.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.util.Date;

/**
 * Created by zdm on 2018/3/20.
 */
public class XStreamMain {
    public static void main(String[] args) {
        /*
         basic
          */
        // toXml
        XStream xs = new XStream();
        xs.autodetectAnnotations(true);
//        xs.registerConverter(new CustomElementConvertor()); // 注册自定义转换器
        // 日期格式化
        xs.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss", null));
//        String xml = xs.toXML(new MyXmlModel(1, "xml", new Date()).setName2("NAME"));
//        xs.addImplicitCollection(MyXmlModel.class, "myxml"); // 省略集合根节点
//        xs = new XStream(new JettisonMappedXmlDriver()); // json格式，需要jettison依赖
        String xml = xs.toXML(new MyXmlModel(1, "xml", new Date()));
        System.out.println(xml);

        // fromXml
        XStream xs2 = new XStream(new XppDriver());
        xs2.autodetectAnnotations(true);
        MyXmlModel fromXML = (MyXmlModel) xs.fromXML(xml);
        System.out.println(fromXML);
    }
}
