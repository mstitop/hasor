/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.more.services.freemarker.xml;
import java.io.IOException;
import org.more.core.xml.XmlElementHook;
import org.more.core.xml.XmlStackDecorator;
import org.more.core.xml.stream.EndElementEvent;
import org.more.core.xml.stream.StartElementEvent;
import org.more.hypha.context.xml.XmlDefineResource;
import org.more.services.freemarker.FreemarkerService;
/**
 * 用于解析fk:addClassPath标签。
 * @version : 2011-8-15
 * @author 赵永春 (zyc@byshell.org)
 */
public class TagFK_AddClassPath extends TagFK_NS implements XmlElementHook {
    /**创建{@link TagFK_AddClassPath}对象 */
    public TagFK_AddClassPath(XmlDefineResource configuration, FreemarkerService service) {
        super(configuration, service);
    }
    /**开始标签解析expression属性。*/
    public void beginElement(XmlStackDecorator<Object> context, String xpath, StartElementEvent event) throws IOException {
        String classpath = event.getAttributeValue("classpath");
        if (classpath == null || classpath.equals("") == true) {} else
            this.getService().addClassPath(classpath);
    }
    public void endElement(XmlStackDecorator<Object> context, String xpath, EndElementEvent event) {}
}