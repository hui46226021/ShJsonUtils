# ShJsonUtils
android json解析框架
Download the latest JAR or grab via Maven:
~~~xml
<dependency>
  <groupId>com.sh.zsh.code</groupId>
  <artifactId>shjson</artifactId>
  <version>1.2.2</version>
  <type>pom</type>
</dependency>
~~~
or Gradle:
~~~gradle
compile 'com.sh.zsh.code:shjson:1.2.2'
~~~
#使用方法
~~~java
 String jsonStr ="{\"people\":[{\"firstName\":\"Brett\",\"lastName\":\"McLaughlin\",\"email\":\"aaaa\"},{\"firstName\":\"Jason\",\"lastName\":\"Hunter\",\"email\":\"bbbb\"},{\"firstName\":\"Elliotte\",\"lastName\":\"Harold\",\"email\":\"cccc\"}],\"data\":{\"people\":[{\"firstName\":\"Brett\",\"lastName\":\"McLaughlin\",\"email\":\"aaaa\"},{\"firstName\":\"Jason\",\"lastName\":\"Hunter\",\"email\":\"bbbb\"},{\"firstName\":\"Elliotte\",\"lastName\":\"Harold\",\"email\":\"cccc\"}],\"animal\":\"cat\",\"look\":{\"head\":\"1个\",\"tail\":\"1条\",\"leg\":4}}}";
   JSONUtil jsonUtil = new JSONUtil(jsonStr);
   List<People> peoples =jsonUtil.getList(People.class,Look.class,"people");
   List<People> peoples2 =jsonUtil.getList(People.class,"data.people");
   String animal = jsonUtil.getObject(String.class,"data.animal");
   Look look = jsonUtil.getObject(Look.class,"data.look");
~~~
* 创建 JSONUtil 对象，传入json字符串
* 通过getObject方法 获取 实体对象 ，层级用.隔开
* 通过getList方法 获取 数组对象 ，层级用.隔开