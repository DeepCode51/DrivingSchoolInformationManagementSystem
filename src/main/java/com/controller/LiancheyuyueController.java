
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 练车预约
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/liancheyuyue")
public class LiancheyuyueController {
    private static final Logger logger = LoggerFactory.getLogger(LiancheyuyueController.class);

    @Autowired
    private LiancheyuyueService liancheyuyueService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private CheliangService cheliangService;
    @Autowired
    private XueyuanService xueyuanService;

    @Autowired
    private JiaolianService jiaolianService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学员".equals(role))
            params.put("xueyuanId",request.getSession().getAttribute("userId"));
        else if("教练".equals(role))
            params.put("jiaolianId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = liancheyuyueService.queryPage(params);

        //字典表数据转换
        List<LiancheyuyueView> list =(List<LiancheyuyueView>)page.getList();
        for(LiancheyuyueView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LiancheyuyueEntity liancheyuyue = liancheyuyueService.selectById(id);
        if(liancheyuyue !=null){
            //entity转view
            LiancheyuyueView view = new LiancheyuyueView();
            BeanUtils.copyProperties( liancheyuyue , view );//把实体数据重构到view中

                //级联表
                CheliangEntity cheliang = cheliangService.selectById(liancheyuyue.getCheliangId());
                if(cheliang != null){
                    BeanUtils.copyProperties( cheliang , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCheliangId(cheliang.getId());
                }
                //级联表
                XueyuanEntity xueyuan = xueyuanService.selectById(liancheyuyue.getXueyuanId());
                if(xueyuan != null){
                    BeanUtils.copyProperties( xueyuan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXueyuanId(xueyuan.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody LiancheyuyueEntity liancheyuyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,liancheyuyue:{}",this.getClass().getName(),liancheyuyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学员".equals(role))
            liancheyuyue.setXueyuanId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<LiancheyuyueEntity> queryWrapper = new EntityWrapper<LiancheyuyueEntity>()
            .eq("xueyuan_id", liancheyuyue.getXueyuanId())
            .eq("cheliang_id", liancheyuyue.getCheliangId())
            .eq("liancheyuyue_uuid_number", liancheyuyue.getLiancheyuyueUuidNumber())
            .eq("kaoshi_types", liancheyuyue.getKaoshiTypes())
            .eq("shenqing_time", new SimpleDateFormat("yyyy-MM-dd").format(liancheyuyue.getShenqingTime()))
            .eq("xiaoshi", liancheyuyue.getXiaoshi())
            .eq("liancheyuyue_yesno_types", liancheyuyue.getLiancheyuyueYesnoTypes())
            .eq("liancheyuyue_yesno_text", liancheyuyue.getLiancheyuyueYesnoText())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LiancheyuyueEntity liancheyuyueEntity = liancheyuyueService.selectOne(queryWrapper);
        if(liancheyuyueEntity==null){
            liancheyuyue.setInsertTime(new Date());
            liancheyuyue.setLiancheyuyueYesnoTypes(1);
            liancheyuyue.setCreateTime(new Date());
            liancheyuyueService.insert(liancheyuyue);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LiancheyuyueEntity liancheyuyue, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,liancheyuyue:{}",this.getClass().getName(),liancheyuyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学员".equals(role))
//            liancheyuyue.setXueyuanId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<LiancheyuyueEntity> queryWrapper = new EntityWrapper<LiancheyuyueEntity>()
            .notIn("id",liancheyuyue.getId())
            .andNew()
            .eq("xueyuan_id", liancheyuyue.getXueyuanId())
            .eq("cheliang_id", liancheyuyue.getCheliangId())
            .eq("liancheyuyue_uuid_number", liancheyuyue.getLiancheyuyueUuidNumber())
            .eq("kaoshi_types", liancheyuyue.getKaoshiTypes())
            .eq("shenqing_time", liancheyuyue.getShenqingTime())
            .eq("xiaoshi", liancheyuyue.getXiaoshi())
            .eq("insert_time", liancheyuyue.getInsertTime())
            .eq("liancheyuyue_yesno_types", liancheyuyue.getLiancheyuyueYesnoTypes())
            .eq("liancheyuyue_yesno_text", liancheyuyue.getLiancheyuyueYesnoText())
            .eq("update_time", liancheyuyue.getUpdateTime())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LiancheyuyueEntity liancheyuyueEntity = liancheyuyueService.selectOne(queryWrapper);
        liancheyuyue.setUpdateTime(new Date());
        if(liancheyuyueEntity==null){
            liancheyuyueService.updateById(liancheyuyue);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        liancheyuyueService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<LiancheyuyueEntity> liancheyuyueList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            LiancheyuyueEntity liancheyuyueEntity = new LiancheyuyueEntity();
//                            liancheyuyueEntity.setXueyuanId(Integer.valueOf(data.get(0)));   //学员 要改的
//                            liancheyuyueEntity.setCheliangId(Integer.valueOf(data.get(0)));   //车辆 要改的
//                            liancheyuyueEntity.setLiancheyuyueUuidNumber(data.get(0));                    //预约编号 要改的
//                            liancheyuyueEntity.setKaoshiTypes(Integer.valueOf(data.get(0)));   //预约科目 要改的
//                            liancheyuyueEntity.setLiancheyuyueContent("");//照片
//                            liancheyuyueEntity.setShenqingTime(new Date(data.get(0)));          //申请日期 要改的
//                            liancheyuyueEntity.setXiaoshi(Integer.valueOf(data.get(0)));   //申请练车时长(小时) 要改的
//                            liancheyuyueEntity.setInsertTime(date);//时间
//                            liancheyuyueEntity.setLiancheyuyueYesnoTypes(Integer.valueOf(data.get(0)));   //申请状态 要改的
//                            liancheyuyueEntity.setLiancheyuyueYesnoText(data.get(0));                    //审核理由 要改的
//                            liancheyuyueEntity.setUpdateTime(new Date(data.get(0)));          //回复时间 要改的
//                            liancheyuyueEntity.setCreateTime(date);//时间
                            liancheyuyueList.add(liancheyuyueEntity);


                            //把要查询是否重复的字段放入map中
                                //预约编号
                                if(seachFields.containsKey("liancheyuyueUuidNumber")){
                                    List<String> liancheyuyueUuidNumber = seachFields.get("liancheyuyueUuidNumber");
                                    liancheyuyueUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> liancheyuyueUuidNumber = new ArrayList<>();
                                    liancheyuyueUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("liancheyuyueUuidNumber",liancheyuyueUuidNumber);
                                }
                        }

                        //查询是否重复
                         //预约编号
                        List<LiancheyuyueEntity> liancheyuyueEntities_liancheyuyueUuidNumber = liancheyuyueService.selectList(new EntityWrapper<LiancheyuyueEntity>().in("liancheyuyue_uuid_number", seachFields.get("liancheyuyueUuidNumber")));
                        if(liancheyuyueEntities_liancheyuyueUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LiancheyuyueEntity s:liancheyuyueEntities_liancheyuyueUuidNumber){
                                repeatFields.add(s.getLiancheyuyueUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [预约编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        liancheyuyueService.insertBatch(liancheyuyueList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
