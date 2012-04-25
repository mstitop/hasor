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
package org.more.services.remote.assembler.creater;
import java.rmi.Remote;
import org.more.core.error.LoadException;
import org.more.services.remote.RemoteService;
import org.more.services.remote.assembler.AbstractRmiBeanCreater;
/**
 * 配置文件代理
 * @version : 2011-8-15
 * @author 赵永春 (zyc@byshell.org)
 */
public class XmlRmiBeanCreater extends AbstractRmiBeanCreater {
    private RemoteService remoteService = null;
    private Class<?>[]    faces         = null;
    private String        refBean       = null;
    //
    //
    public XmlRmiBeanCreater(String faces, String refBean, RemoteService remoteService) {
        try {
            this.faces = new Class<?>[] { Thread.currentThread().getContextClassLoader().loadClass(faces) };
        } catch (Exception e) {
            throw new LoadException("装载错误，RMI Creater无法装载‘" + faces + "’类型。");
        }
        this.refBean = refBean;
        this.remoteService = remoteService;
    };
    public Class<?>[] getFaces() throws Throwable {
        return this.getRemoteFaces(this.faces);
    };
    public Remote create() throws Throwable {
        //1.创建对象
        Object obj = this.remoteService.getApplicationContext().getBean(this.refBean);
        //2.生成Remote代理
        return super.getRemoteByFaces(obj, this.getFaces());
    };
};