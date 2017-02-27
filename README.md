# ShJsonUtils
android json解析框架
Download the latest JAR or grab via Maven:
~~~xml
<dependency>
  <groupId>com.sh.zsh.code</groupId>
  <artifactId>shjson</artifactId>
  <version>1.2.3</version>
  <type>pom</type>
</dependency>
~~~
or Gradle:
~~~gradle
compile 'com.sh.zsh.code:shjson:1.2.3'
~~~
#使用方法
~~~java

   JSONUtil jsonUtil = new JSONUtil(jsonStr);
   //二维数组
   List<People> peoples =jsonUtil.getList(People.class,"people",Look.class);
   //三维数组
   List<People> peoples =jsonUtil.getList(People.class,"people",Look.class,En.class);
   List<People> peoples2 =jsonUtil.getList(People.class,"data.people");
   String animal = jsonUtil.getObject(String.class,"data.animal");
   Look look = jsonUtil.getObject(Look.class,"data.look");
~~~
* 创建 JSONUtil 对象，传入json字符串
* 通过getObject方法 获取 实体对象 ，层级用.隔开
* 通过getList方法 获取 数组对象 ，层级用.隔开