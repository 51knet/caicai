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