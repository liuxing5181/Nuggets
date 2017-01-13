package com.nuggets.util;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by dss on 2016/11/15.
 *
 * @author Mr. Deng
 */


public class Framework {
//    架构提示
    /**
     * 抽象层面上：

     1/提高架构的拓展性是有必要的。
     2/提高架构的稳定性
     3/架构的文档也是必不可少的。

     具体操作时：
     1/baseActivity baseFragment 的必要性
     2/注释的必要性
     3/数据提供统一的入口。   无论是在mvp，mvc，还是mvvm中，提供一个统一的数据入口，都可以让代码变得更加易于维护
     比如，我使用的DataManager，里面的http还是preference，还是eventpost ，还是database ，都在DataManger里面进行操作，我们只需要与DataManger打交道。
     4、多用组合，少用继承
     5、去重
     6、引入Dagger2 减少模块之间的耦合性
     7、为你的项目引入Rxjava+RxAndroid这些响应式编程
     8、通过引入 Event Bus（事件总线，这个项目使用的是otto）。
     9、添加日志打印，用于查找错误等。
     */

    //注释
         /*
      * @(#): Document.java
      * @project：IndentObjectNatation
      * @version: v1.1
      * @copyright: Copyright (C) 2013-2014 The Emerald Education
      * @description:
      *         This file is a part of Indent Object Notation project.
      *
      * @modify:
      * ---- No.1 Modified By Mr. Tang At 2014-05-06 11:32 Based On 1.0 ----
      *      Create this file.
      * ---- No.2 Modified By Mr. Zhang At 2014-05-06 11:32 Based On 1.0 ----
      *      Make the class Document extend from the class Node.
      */

    /** This view is invisible.*/
    public static final int INVISIBLE = 0x00000004;

    //    对于未完成的方法，使用TODO加以标记，例如：
    void write(byte[] buf, File file) {
        // TODO: Write buf to file
    }

    //    若功能已完成，但存在效率等潜在问题时，使用XXX加以标记，例如：
    void parseXML(File file) {
        // XXX: Maybe SAX is better
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//    若代码存在严重问题或仅用于调试，使用FIXME加以标记（
boolean login(String name, String pwd) {
    //FIXME: Remove this line before publishing
    System.out.println("name=" + name + ", password=" + pwd);
    if (true)
        return true;
    return false;
}
}
