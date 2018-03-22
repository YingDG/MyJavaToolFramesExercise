package yingdg.exercise.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * 处理既有属性值又有节点值的情况
 */
/*
SingleValueConverter：单值转换接口
AbstractSingleValueConverter：单值转换抽象类
Converter：常规转换器接口
 */
public class CustomElementConvertor implements Converter {
    @Override
    public boolean canConvert(Class type) {
        return type.equals(MyXmlModel.class);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        MyXmlModel pr = new MyXmlModel();
        pr.setName(reader.getAttribute("NAME"));
        pr.setName2(reader.getValue());
        return pr;
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        MyXmlModel pr = (MyXmlModel) source;
        writer.addAttribute("NAME", pr.getName());
        writer.setValue(pr.getName2());
    }
}
