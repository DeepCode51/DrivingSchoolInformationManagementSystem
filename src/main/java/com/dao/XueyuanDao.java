package com.dao;

import com.entity.XueyuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XueyuanView;

/**
 * 学员 Dao 接口
 *
 * @author 
 */
public interface XueyuanDao extends BaseMapper<XueyuanEntity> {

   List<XueyuanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
