# ApiCensus
基于spring aop 实现接口统计基础通用服务


1、简单快速无侵入地实现api接口统计
   只需简单地为方法添加
   @ApiRequestMonitor(type = 1, isPersist = true, handler = ApiMHCountHandler.class)即可。
   1.1 实现简单统计api接口调用次数
   
   1.2 实现最大次数限制 ，协定 -1 为无限制
   
2、自定义实现api接口统计handler
   2.1 继承 ApiMHCountHandler
   2.2 定义为自定义handler 类添加@Component("xxx"),其中xxx 为该handler的注入标志，协定为类名的全小写
   2.3 重写checkApi 方法即可。参考demo 中的Max3ApiMonitorHandler
   
3、集群化场景下存储，使用redis

4、持久化场景下存储，使用mysql

5、集群化与持久化场景下应用
