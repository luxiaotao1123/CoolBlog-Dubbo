package cn.dubbo.controller;


import cn.dubbo.common.bean.Blog;
import cn.dubbo.service.BlogService;
import cn.dubbo.service.TokenService;
import cn.dubbo.utils.R1;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "admin/")
public class AdminController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TokenService tokenService;

    @ApiOperation(value = "拿到所有博客（分页）",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示个数",required = true,dataType = "Integer")
    })
    @GetMapping(value = "blogs")
    public R1 ajaxBlog(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList = blogService.getALLBlog();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogList);
        return R1.add("blog",pageInfo);
    }

    @ApiOperation(value = "拿到所有博客（分类）",notes = "在blog/{标签}后面在分类路由")
    @ApiImplicitParam(name = "label",value = "博客标签",required = true,dataType = "String")
    @GetMapping(value = "blogs/{label}")
    public R1 getLableBlogs(@PathVariable("label")String label){
        List<Blog> blogList = blogService.selectBlogsByLabel(label);
        return R1.add("blogbylabel",blogList);
    }

    @ApiOperation(value = "拿到一篇博客的详情",notes = "成功返回blog对象，失败返回500")
    @ApiImplicitParam(name = "blogId",value = "博客Id",required = true,dataType = "Integer")
    @GetMapping(value = "blog/{blogId}")
    public R1 getOneBlog(@PathVariable("blogId")Integer blogId){
        Blog blog = blogService.getOneBlog(blogId);
        return R1.add("blog",blog);
    }

    @ApiOperation(value = "删除一篇博客",notes = "成功返回200，失败返回500")
    @ApiImplicitParam(name = "blogId",value = "博客Id",required = true,dataType = "Integer")
    @DeleteMapping(value = "blog/{blogId}")
    public R1 deleteBlog(@PathVariable("blogId")Integer blogId){
        try {
            blogService.deleteOneBlog(blogId);
        }catch (MethodArgumentTypeMismatchException exception){
            R1.error(500,"服务器内部错误");
        }
        return R1.success(200,"删除博客成功");
    }

    @ApiOperation(value = "删除多篇博客 ",notes = "成功返回200，失败返回500")
    @ApiImplicitParam(name = "blogIds",value = "博客Ids",required = true,dataType = "Array")
    @DeleteMapping(value = "blogs")
    public R1 deleteBlogs(@RequestParam("blogIds")Integer[] blogIds){
        try {
            blogService.updateStatus(blogIds);
        }catch (MethodArgumentTypeMismatchException exception){
            R1.error(500,"服务器内部错误");
        }
        return R1.success(200,"删除博客成功");
    }

    @ApiOperation(value = "创建一篇博客 ",notes = "成功返回201，失败返回500,无权限返回401")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "标题",required = true,dataType = "String"),
            @ApiImplicitParam(name = "label",value = "标签分类",required = true,dataType = "String"),
            @ApiImplicitParam(name = "content",value = "文章内容",required = true,dataType = "String"),
            @ApiImplicitParam(name = "userid",value = "用户Id",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "summary",value = "摘要",required = true,dataType = "String"),
            @ApiImplicitParam(name = "preview",value = "预览图",required = true,dataType = "String")
    })
    @PostMapping(value = "blog")
    public R1 postBlog(@RequestBody Blog blog, HttpServletRequest request){
        String token = request.getHeader("token");
        int userId = tokenService.getUserId(token);
        Date updateTime = new Date();
        blog.setStatus(1);
        blog.setUpdatetime(updateTime);
        blog.setUserid(userId);
        try {
            blogService.saveBlog(blog);
        }catch (Exception e){
            R1.error(500,"服务器内部错误");
        }
        return R1.success(201,"创建博客成功");
    }

    @ApiOperation(value = "修改一篇博客",notes = "成功返回blog对象，失败返回500")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "标题",required = true,dataType = "String"),
            @ApiImplicitParam(name = "label",value = "标签分类",required = true,dataType = "String"),
            @ApiImplicitParam(name = "content",value = "文章内容",required = true,dataType = "String"),
            @ApiImplicitParam(name = "summary",value = "摘要",required = true,dataType = "String"),
            @ApiImplicitParam(name = "preview",value = "预览图",required = true,dataType = "String")
    })
    @PutMapping(value = "blog")
    public R1 updateBlog(@RequestBody Blog blog){
        blog.setUpdatetime(new Date());
        return R1.add("blog",blogService.updateOneBlog(blog));
    }

    @ApiOperation(value = "拿到垃圾箱里的所有博客",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示个数",required = true,dataType = "Integer")
    })
    @GetMapping(value = "dustbin")
    public R1 readDustbin(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList = blogService.getDustbin();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogList);
        return R1.add("dustbinBlogs",pageInfo);
    }

    @ApiOperation(value = "删除垃圾箱里的博客",notes = "")
    @ApiImplicitParam(name = "blogIds",value = "要删除的所有博客id",required = true,dataType = "Integer[]")
    @DeleteMapping(value = "dustbin")
    public R1 deleteDustbin(@RequestParam("blogIds")Integer[] blogIds){
        if (blogIds==null){
            return R1.error(500,"传入的blogIds为空");
        }
        try {
            blogService.deleteDustbinBlogs(blogIds);
        }catch (Exception e){
            e.printStackTrace();
        }
        return R1.success(200,"删除成功");
    }
}
