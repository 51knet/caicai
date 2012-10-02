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

我们遵循统一的约定，这样查找对应关系就会变得相对清晰很多了

