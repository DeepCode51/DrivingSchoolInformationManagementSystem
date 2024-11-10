
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
 * 教练
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiaolian")
public class JiaolianController {
    private static final Logger logger = LoggerFactory.getLogger(JiaolianController.class);

    @Autowired
    private JiaolianService jiaolianService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

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
        PageUtils page = jiaolianService.queryPage(params);

        //字典表数据转换
        List<JiaolianView> list =(List<JiaolianView>)page.getList();
        for(JiaolianView c:list){
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
        JiaolianEntity jiaolian = jiaolianService.selectById(id);
        if(jiaolian !=null){
            //entity转view
            JiaolianView view = new JiaolianView();
            BeanUtils.copyProperties( jiaolian , view );//把实体数据重构到view中

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
    public R save(@RequestBody JiaolianEntity jiaolian, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiaolian:{}",this.getClass().getName(),jiaolian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<JiaolianEntity> queryWrapper = new EntityWrapper<JiaolianEntity>()
            .eq("username", jiaolian.getUsername())
            .or()
            .eq("jiaolian_phone", jiaolian.getJiaolianPhone())
            .or()
            .eq("jiaolian_id_number", jiaolian.getJiaolianIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaolianEntity jiaolianEntity = jiaolianService.selectOne(queryWrapper);
        if(jiaolianEntity==null){
            jiaolian.setCreateTime(new Date());
            jiaolian.setPassword("123456");
            jiaolianService.insert(jiaolian);
            return R.ok();
        }else {
            return R.error(511,"账户或者教练手机号或者教练身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiaolianEntity jiaolian, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jiaolian:{}",this.getClass().getName(),jiaolian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<JiaolianEntity> queryWrapper = new EntityWrapper<JiaolianEntity>()
            .notIn("id",jiaolian.getId())
            .andNew()
            .eq("username", jiaolian.getUsername())
            .or()
            .eq("jiaolian_phone", jiaolian.getJiaolianPhone())
            .or()
            .eq("jiaolian_id_number", jiaolian.getJiaolianIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaolianEntity jiaolianEntity = jiaolianService.selectOne(queryWrapper);
        if("".equals(jiaolian.getJiaolianPhoto()) || "null".equals(jiaolian.getJiaolianPhoto())){
                jiaolian.setJiaolianPhoto(null);
        }
        if(jiaolianEntity==null){
            jiaolianService.updateById(jiaolian);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者教练手机号或者教练身份证号已经被使用");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        jiaolianService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JiaolianEntity> jiaolianList = new ArrayList<>();//上传的东西
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
                            JiaolianEntity jiaolianEntity = new JiaolianEntity();
//                            jiaolianEntity.setUsername(data.get(0));                    //账户 要改的
//                            //jiaolianEntity.setPassword("123456");//密码
//                            jiaolianEntity.setJiaolianName(data.get(0));                    //教练姓名 要改的
//                            jiaolianEntity.setJiaolianPhone(data.get(0));                    //教练手机号 要改的
//                            jiaolianEntity.setJiaolianIdNumber(data.get(0));                    //教练身份证号 要改的
//                            jiaolianEntity.setJiaolianPhoto("");//照片
//                            jiaolianEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            jiaolianEntity.setJiaolianEmail(data.get(0));                    //电子邮箱 要改的
//                            jiaolianEntity.setCreateTime(date);//时间
                            jiaolianList.add(jiaolianEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //教练手机号
                                if(seachFields.containsKey("jiaolianPhone")){
                                    List<String> jiaolianPhone = seachFields.get("jiaolianPhone");
                                    jiaolianPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> jiaolianPhone = new ArrayList<>();
                                    jiaolianPhone.add(data.get(0));//要改的
                                    seachFields.put("jiaolianPhone",jiaolianPhone);
                                }
                                //教练身份证号
                                if(seachFields.containsKey("jiaolianIdNumber")){
                                    List<String> jiaolianIdNumber = seachFields.get("jiaolianIdNumber");
                                    jiaolianIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jiaolianIdNumber = new ArrayList<>();
                                    jiaolianIdNumber.add(data.get(0));//要改的
                                    seachFields.put("jiaolianIdNumber",jiaolianIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<JiaolianEntity> jiaolianEntities_username = jiaolianService.selectList(new EntityWrapper<JiaolianEntity>().in("username", seachFields.get("username")));
                        if(jiaolianEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiaolianEntity s:jiaolianEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //教练手机号
                        List<JiaolianEntity> jiaolianEntities_jiaolianPhone = jiaolianService.selectList(new EntityWrapper<JiaolianEntity>().in("jiaolian_phone", seachFields.get("jiaolianPhone")));
                        if(jiaolianEntities_jiaolianPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiaolianEntity s:jiaolianEntities_jiaolianPhone){
                                repeatFields.add(s.getJiaolianPhone());
                            }
                            return R.error(511,"数据库的该表中的 [教练手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //教练身份证号
                        List<JiaolianEntity> jiaolianEntities_jiaolianIdNumber = jiaolianService.selectList(new EntityWrapper<JiaolianEntity>().in("jiaolian_id_number", seachFields.get("jiaolianIdNumber")));
                        if(jiaolianEntities_jiaolianIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiaolianEntity s:jiaolianEntities_jiaolianIdNumber){
                                repeatFields.add(s.getJiaolianIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [教练身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jiaolianService.insertBatch(jiaolianList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        JiaolianEntity jiaolian = jiaolianService.selectOne(new EntityWrapper<JiaolianEntity>().eq("username", username));
        if(jiaolian==null || !jiaolian.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(jiaolian.getId(),username, "jiaolian", "教练");
        R r = R.ok();
        r.put("token", token);
        r.put("role","教练");
        r.put("username",jiaolian.getJiaolianName());
        r.put("tableName","jiaolian");
        r.put("userId",jiaolian.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody JiaolianEntity jiaolian){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<JiaolianEntity> queryWrapper = new EntityWrapper<JiaolianEntity>()
            .eq("username", jiaolian.getUsername())
            .or()
            .eq("jiaolian_phone", jiaolian.getJiaolianPhone())
            .or()
            .eq("jiaolian_id_number", jiaolian.getJiaolianIdNumber())
            ;
        JiaolianEntity jiaolianEntity = jiaolianService.selectOne(queryWrapper);
        if(jiaolianEntity != null)
            return R.error("账户或者教练手机号或者教练身份证号已经被使用");
        jiaolian.setCreateTime(new Date());
        jiaolianService.insert(jiaolian);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        JiaolianEntity jiaolian = new JiaolianEntity();
        jiaolian.setPassword("123456");
        jiaolian.setId(id);
        jiaolianService.updateById(jiaolian);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        JiaolianEntity jiaolian = jiaolianService.selectOne(new EntityWrapper<JiaolianEntity>().eq("username", username));
        if(jiaolian!=null){
            jiaolian.setPassword("123456");
            boolean b = jiaolianService.updateById(jiaolian);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrJiaolian(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        JiaolianEntity jiaolian = jiaolianService.selectById(id);
        if(jiaolian !=null){
            //entity转view
            JiaolianView view = new JiaolianView();
            BeanUtils.copyProperties( jiaolian , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }





}
