package yingdg.exercise.javapoet;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by yingdg on 2017/8/30.
 */
public class HelloJavaPoet {
    /*
    构建并生成java源文件
     */
    public static void main(String[] args) throws IOException {
        // 添加注解
        AnnotationSpec spec = AnnotationSpec
                .builder(Override.class)
                .build();

        // 添加形参
        ParameterSpec parameterSpec = ParameterSpec
                .builder(String.class, "str")
                .addModifiers(Modifier.FINAL)
                .build();

        // 添加方法
        MethodSpec methodSpec = MethodSpec
                // 添加方法名
                .methodBuilder("main")
                // 添加修饰符
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                // 添加方法参数
                .addParameter(String[].class, "args")
                // 添加返回参数
                .returns(TypeName.VOID)
                // 添加方法体
                .addStatement("$T.out.println($S)", System.class, "Hello JavaPoet") // 会产生import java.lang.System;
                .addCode("System.out.println(name);\n") // 不会引入import
                // 添加一段代码
                .beginControlFlow("for (int i = 0; i < 10; i++)")
                .addStatement("System.out.println(i)")
                .endControlFlow()
                // 生成方法
                .build();
        /*
        $L代表的是字面量
        $S for Strings
        $T for Types
        $N for Names
         */

        // 添加成员变量
        FieldSpec fieldSpec = FieldSpec
                // 添加属性名
                .builder(String.class, "name")
                // 添加修饰符
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                // 初始化值
                .initializer("$S + $L", "a", 2)
                // 生成属性
                .build();

        // 添加类
        TypeSpec typeSpec = TypeSpec
//                .anonymousClassBuilder("") // 匿名内部类
//                .annotationBuilder("") // 注解
//                .enumBuilder("")  // 枚举
//                .interfaceBuilder("") // 接口
                // 添加类名
                .classBuilder("MyJavaPoet")
                // 添加修饰符
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                // 添加类方法
                .addMethod(methodSpec)
                // 添加类成员
                .addField(fieldSpec)
                // 生成类
                .build();

        // 生成类
        JavaFile javaFile = JavaFile
                // 添加包名和类
                .builder("yingdg.exercise.javapoet", typeSpec)
                // 静态引入
                .addStaticImport(List.class, "*")
                // 生成顶级类
                .build();

        // 输出至控制台
        javaFile.writeTo(System.out);
        // 生成源文件
        javaFile.writeTo(new File(".\\src\\main\\java"));
    }
}
