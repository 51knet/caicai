caicai
======
version 1.0



# URLs 重构参考
大家根据下面的建议重构相关的路由信息

## 注册登陆

| url         
|   
| /            
| /signin      
| /commonregister  -> /register/common   
| /registerpage    -> /register  
| /mail/{randomUrl}/{idString}  


## 学生页面
| url         
|         
| /student/{user_id}         
| /studentDetailInfo -> /admin/student/details         
| /studentInfoPage   -> /admin/student/basic         

## 普通用户
| url          
|         
| /user/{id}         
| /usertype       -> /user/dispatcher         
| /userDetailInfo -> /admin/user/details         
| /userInfoPage   -> /admin/user/basic         

## 教师用户
| url         
|        
| /teacherDetailInfo -> /admin/teacher/details         
| /teacherInfoPage   -> /admin/teacher/basic         

## 教师博客
| url         
|    
| /admin/blog/list         
| /admin/blog/new         
| /admin/blog/edit/{blog_post_id}         
| /admin/blog/view/{blog_post_id}         
| /admin/blog/destroy         
| /admin/blog/category/list         
| /admin/blog/category/new         
| /admin/blog/category/edit         
| /admin/blog/category/destroy         

## 其他
| url         
|         
| /debug         
| /one2one/{user_id}/{student_collage}         

## URLs和Tiles视图的命名如何对应以方便查找？
**重构前:** URL:userInfoPage, Tiles视图定义如下
>     <definition name="userInfoPage" extends="homePage-layout">
>		<put-attribute name="title" value="Detail Info" />
>		<put-attribute name="left"
>			value="/WEB-INF/views/user_home/user_item_panel.jsp"></put-attribute>
>		<put-attribute name="right"
			value="/WEB-INF/views/user_home/userInfoPage.jsp"></put-attribute>
>	  </definition>

**重构建议:** URL:userInfoPage -> /admin/user/basic, Tiles视图名称修改为*admin.user.basic*
>     <definition name="admin.user.basic" extends="homePage-layout">
>		<put-attribute name="title" value="Detail Info" />
>		<put-attribute name="left"
>			value="/WEB-INF/views/user_home/user_item_panel.jsp"></put-attribute>
>		<put-attribute name="right"
			value="/WEB-INF/views/user_home/userInfoPage.jsp"></put-attribute>
>	  </definition>

我们遵循统一的约定，这样查找对应关系就会变得相对清晰很多了，也就是url和view的映射就明确对应，controller 的代码：
>     @RequestMapping(value= "/admin/user/basic", method=RequestMethod.GET)    
>	  public String basic() {    
>          return "admin.user.basic";    
>	  }    

# 关于WEB-INF/views目录的重构参考
我们遵循约定的命名规范以获得清晰的代码结构

* 在此目录下的文件推荐使用这样的目录命 *teacher-info* 或 *teacher_info* 而不是 *teacherInfo*
* 添加某route对应的jsp页面时，使用 *teacher/front*  *teacher/home* 而不是使用这样两个目录 *teacher_front*  *teacher_home*
