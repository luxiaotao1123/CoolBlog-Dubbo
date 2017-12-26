package cn.dubbo.dao;

import cn.dubbo.common.bean.Archive;
import cn.dubbo.common.bean.Blog;
import cn.dubbo.common.bean.BlogExample;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface BlogMapper {
    long countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int deleteByPrimaryKey(Integer blogid);

    int insert(Blog record);

    int insertSelective(Blog record);

    List<Blog> selectByExample(BlogExample example);

    Blog selectByPrimaryKey(Integer blogid);

    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    Blog queryByblogId(int blogId);

    int updateByBlog(Blog blog);

    List<Blog> selectBlogsByLabel(String label);

    int deleteMoreBlogs(Integer[] blogIds);

    int updateStatus(Integer[] blogIds);

    List<Blog> getDustbinBlogs();

    int deleteDustbinBlogs(Integer[] blogIds);

    ArrayList<Blog> getAllBlog();

    List<String> queryYears();

    List<String> queryMonths();

    List<String> queryMonthsByYear(String year);

    List<Archive> queryArchives(String month);

    List<Blog> queryByKeywords(String keywords);
}