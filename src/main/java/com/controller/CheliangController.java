
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
 * 车辆
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/cheliang")
public class CheliangController {
    private static final Logger logger = LoggerFactory.getLogger(CheliangController.class);

    @Autowired
    private CheliangService cheliangService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private JiaolianService jiaolianService;

    @Autowired
    private XueyuanService xueyuanService;


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
        PageUtils page = cheliangService.queryPage(params);

        //字典表数据转换
        List<CheliangView> list =(List<CheliangView>)page.getList();
        for(CheliangView c:list){
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
        CheliangEntity cheliang = cheliangService.selectById(id);
        if(cheliang !=null){
            //entity转view
            CheliangView view = new CheliangView();
            BeanUtils.copyProperties( cheliang , view );//把实体数据重构到view中

                //级联表
                JiaolianEntity jiaolian = jiaolianService.selectById(cheliang.getJiaolianId());
                if(jiaolian != null){
                    BeanUtils.copyProperties( jiaolian , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJiaolianId(jiaolian.getId());
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
    public R save(@RequestBody CheliangEntity cheliang, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,cheliang:{}",this.getClass().getName(),cheliang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("教练".equals(role))
            cheliang.setJiaolianId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<CheliangEntity> queryWrapper = new EntityWrapper<CheliangEntity>()
            .eq("jiaolian_id", cheliang.getJiaolianId())
            .eq("cheliang_uuid_number", cheliang.getCheliangUuidNumber())
            .eq("cheliang_name", cheliang.getCheliangName())
            .eq("cheliang_types", cheliang.getCheliangTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CheliangEntity cheliangEntity = cheliangService.selectOne(queryWrapper);
        if(cheliangEntity==null){
            cheliang.setInsertTime(new Date());
            cheliang.setCreateTime(new Date());
            cheliangService.insert(cheliang);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CheliangEntity cheliang, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,cheliang:{}",this.getClass().getName(),cheliang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("教练".equals(role))
//            cheliang.setJiaolianId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<CheliangEntity> queryWrapper = new EntityWrapper<CheliangEntity>()
            .notIn("id",cheliang.getId())
            .andNew()
            .eq("jiaolian_id", cheliang.getJiaolianId())
            .eq("cheliang_uuid_number", cheliang.getCheliangUuidNumber())
            .eq("cheliang_name", cheliang.getCheliangName())
            .eq("cheliang_types", cheliang.getCheliangTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CheliangEntity cheliangEntity = cheliangService.selectOne(queryWrapper);
        if(cheliangEntity==null){
            cheliangService.updateById(cheliang);//根据id更新
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
        cheliangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<CheliangEntity> cheliangList = new ArrayList<>();//上传的东西
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
                            CheliangEntity cheliangEntity = new CheliangEntity();
//                            cheliangEntity.setJiaolianId(Integer.valueOf(data.get(0)));   //教练 要改的
//                            cheliangEntity.setCheliangUuidNumber(data.get(0));                    //车辆编号 要改的
//                            cheliangEntity.setCheliangName(data.get(0));                    //车辆名称 要改的
//                            cheliangEntity.setCheliangTypes(Integer.valueOf(data.get(0)));   //车辆类型 要改的
//                            cheliangEntity.setCheliangContent("");//照片
//                            cheliangEntity.setInsertTime(date);//时间
//                            cheliangEntity.setCreateTime(date);//时间
                            cheliangList.add(cheliangEntity);


                            //把要查询是否重复的字段放入map中
                                //车辆编号
                                if(seachFields.containsKey("cheliangUuidNumber")){
                                    List<String> cheliangUuidNumber = seachFields.get("cheliangUuidNumber");
                                    cheliangUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> cheliangUuidNumber = new ArrayList<>();
                                    cheliangUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("cheliangUuidNumber",cheliangUuidNumber);
                                }
                        }

                        //查询是否重复
                         //车辆编号
                        List<CheliangEntity> cheliangEntities_cheliangUuidNumber = cheliangService.selectList(new EntityWrapper<CheliangEntity>().in("cheliang_uuid_number", seachFields.get("cheliangUuidNumber")));
                        if(cheliangEntities_cheliangUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(CheliangEntity s:cheliangEntities_cheliangUuidNumber){
                                repeatFields.add(s.getCheliangUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [车辆编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        cheliangService.insertBatch(cheliangList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
