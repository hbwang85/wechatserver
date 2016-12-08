# wechatserver

### Ubuntu Tomcat Directory
* Logs: /var/log/tomcat7  
* /etc/tomcat6 - 全局配置?   
* /usr/share/tomcat6/ - 程序主目录?   
* /usr/share/tomcat6/conf/Catalina/localhost/ - 本机部署的 Catalina 配置  
* /var/lib/tomcat6/ - 工作主目录  
* /var/lib/tomcat6/webapps - （应用文件实际存放于此）
* /var/lib/tomcat6/work - 动态工作目录（动态编译的 .jsp 存放于此)

### Data for signature testing
* Request: GET /wechatserver-1.0-SNAPSHOT/wechat?signature=57b8b3d75fa526519ffcf32531ebc736b7c6c971&echostr=5437234002469240765&timestamp=1481115964&nonce=1492784901 HTTP/1.0"  
* Response: 5437234002469240765


### Issue:
Server deploy failed, after deployed, the server still play as last package
#### Solution:
Go to the tomcat webapps folder(/var/lib/tomcat7/webapps) delete the package, and then deploy it again.
