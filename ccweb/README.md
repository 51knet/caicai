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
| /studentDetailInfo -> /student/admin/details
| /studentInfoPage   -> /student/admin/basic

## 普通用户
| url         
|
| /user/{id}
| /usertype       -> /user/dispatcher
| /userDetailInfo -> /user/admin/details
| /userInfoPage   -> /user/admin/basic

## 教师用户
| url         
|
| /teacherDetailInfo -> /teacher/admin/details
| /teacherInfoPage   -> /teacher/admin/basic

## 其他
| url         
|
| /debug
| /one2one/{user_id}/{student_collage}