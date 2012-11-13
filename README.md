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
| /admin -> /admin/teacher | /admin/user       
| / -> /user/{id} | /teacher/{id}         

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

# 统一的分页的解决方案
## Controller 层

要点是声明此方法接受pageNumber和pageSize这两个参数，有默认值，所以第一次访问可以不指定，例子

    @Transactional
	@RequestMapping(value= "/admin/blog/list", method=RequestMethod.GET)
	public String list(@RequestParam(value="pageNumber",defaultValue="0") int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize, Model model, HttpSession session) {
		Long id = getUserId(session);
		Teacher teacher = teacherService.findOne(id);
		Page<BlogPost> page = blogService.findAllBlogs(pageNumber, pageSize, teacher);
		model.addAttribute("page", page);
		return "admin.blog.list";
	}
	
##Service 层

要点是构造一个Pageable对象用来做分页查找，例子

    @Override
    public Page<BlogPost> findAllBlogs(int pageNumber, int pageSize, Teacher teacher) {
    	Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
    	Page<BlogPost> onePage = blogPostRepository.findByAuthor(teacher, dateDesc);
    	return onePage;
    }
    
##View 层

要点是**include这个通用的分页实现**，可以参考/views/admin/blog/list/list.jsp的例子，例子

    <tfoot>
    	<tr><td colspan="5">
    		<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
    	</td></tr>
    </tfoot>
    
如果有兴趣看看这个源文件，就知道通用性是怎么实现的了。

### 所以如果你有拷贝分页的实现，是时候修改一下list页面了，include上面这一行就行了！
