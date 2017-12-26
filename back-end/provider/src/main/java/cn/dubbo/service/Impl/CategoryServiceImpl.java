package cn.dubbo.service.Impl;

import cn.dubbo.common.bean.Category;
import cn.dubbo.common.bean.CategoryExample;
import cn.dubbo.dao.CategoryMapper;
import cn.dubbo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategory() {
        return categoryMapper.selectByExample(new CategoryExample());
    }
}
