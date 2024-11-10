
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
 * 学员
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xueyuan")
public class XueyuanController {
    private static final Logger logger = LoggerFactory.getLogger(XueyuanController.class);

    @Autowired
    private XueyuanService xueyuanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

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
        PageUtils page = xueyuanService.queryPage(params);

        //字典表数据转换
        List<XueyuanView> list =(List<XueyuanView>)page.getList();
        for(XueyuanView c:list){
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
        XueyuanEntity xueyuan = xueyuanService.selectById(id);
        if(xueyuan !=null){
            //entity转view
            XueyuanView view = new XueyuanView();
            BeanUtils.copyProperties( xueyuan , view );//把实体数据重构到view中

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
    public R save(@RequestBody XueyuanEntity xueyuan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xueyuan:{}",this.getClass().getName(),xueyuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<XueyuanEntity> queryWrapper = new EntityWrapper<XueyuanEntity>()
            .eq("username", xueyuan.getUsername())
            .or()
            .eq("xueyuan_phone", xueyuan.getXueyuanPhone())
            .or()
            .eq("xueyuan_id_number", xueyuan.getXueyuanIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XueyuanEntity xueyuanEntity = xueyuanService.selectOne(queryWrapper);
        if(xueyuanEntity==null){
            xueyuan.setCreateTime(new Date());
            xueyuan.setPassword("123456");
            xueyuanService.insert(xueyuan);
            return R.ok();
        }else {
            return R.error(511,"账户或者学员手机号或者学员身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XueyuanEntity xueyuan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,xueyuan:{}",this.getClass().getName(),xueyuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<XueyuanEntity> queryWrapper = new EntityWrapper<XueyuanEntity>()
            .notIn("id",xueyuan.getId())
            .andNew()
            .eq("username", xueyuan.getUsername())
            .or()
            .eq("xueyuan_phone", xueyuan.getXueyuanPhone())
            .or()
            .eq("xueyuan_id_number", xueyuan.getXueyuanIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XueyuanEntity xueyuanEntity = xueyuanService.selectOne(queryWrapper);
        if("".equals(xueyuan.getXueyuanPhoto()) || "null".equals(xueyuan.getXueyuanPhoto())){
                xueyuan.setXueyuanPhoto(null);
        }
        if(xueyuanEntity==null){
            xueyuanService.updateById(xueyuan);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者学员手机号或者学员身份证号已经被使用");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        xueyuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<XueyuanEntity> xueyuanList = new ArrayList<>();//上传的东西
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
                            XueyuanEntity xueyuanEntity = new XueyuanEntity();
//                            xueyuanEntity.setUsername(data.get(0));                    //账户 要改的
//                            //xueyuanEntity.setPassword("123456");//密码
//                            xueyuanEntity.setXueyuanName(data.get(0));                    //学员姓名 要改的
//                            xueyuanEntity.setXueyuanPhone(data.get(0));                    //学员手机号 要改的
//                            xueyuanEntity.setXueyuanIdNumber(data.get(0));                    //学员身份证号 要改的
//                            xueyuanEntity.setXueyuanPhoto("");//照片
//                            xueyuanEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            xueyuanEntity.setXueyuanEmail(data.get(0));                    //电子邮箱 要改的
//                            xueyuanEntity.setCreateTime(date);//时间
                            xueyuanList.add(xueyuanEntity);


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
                                //学员手机号
                                if(seachFields.containsKey("xueyuanPhone")){
                                    List<String> xueyuanPhone = seachFields.get("xueyuanPhone");
                                    xueyuanPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> xueyuanPhone = new ArrayList<>();
                                    xueyuanPhone.add(data.get(0));//要改的
                                    seachFields.put("xueyuanPhone",xueyuanPhone);
                                }
                                //学员身份证号
                                if(seachFields.containsKey("xueyuanIdNumber")){
                                    List<String> xueyuanIdNumber = seachFields.get("xueyuanIdNumber");
                                    xueyuanIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> xueyuanIdNumber = new ArrayList<>();
                                    xueyuanIdNumber.add(data.get(0));//要改的
                                    seachFields.put("xueyuanIdNumber",xueyuanIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<XueyuanEntity> xueyuanEntities_username = xueyuanService.selectList(new EntityWrapper<XueyuanEntity>().in("username", seachFields.get("username")));
                        if(xueyuanEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XueyuanEntity s:xueyuanEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //学员手机号
                        List<XueyuanEntity> xueyuanEntities_xueyuanPhone = xueyuanService.selectList(new EntityWrapper<XueyuanEntity>().in("xueyuan_phone", seachFields.get("xueyuanPhone")));
                        if(xueyuanEntities_xueyuanPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XueyuanEntity s:xueyuanEntities_xueyuanPhone){
                                repeatFields.add(s.getXueyuanPhone());
                            }
                            return R.error(511,"数据库的该表中的 [学员手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //学员身份证号
                        List<XueyuanEntity> xueyuanEntities_xueyuanIdNumber = xueyuanService.selectList(new EntityWrapper<XueyuanEntity>().in("xueyuan_id_number", seachFields.get("xueyuanIdNumber")));
                        if(xueyuanEntities_xueyuanIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XueyuanEntity s:xueyuanEntities_xueyuanIdNumber){
                                repeatFields.add(s.getXueyuanIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [学员身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        xueyuanService.insertBatch(xueyuanList);
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
        XueyuanEntity xueyuan = xueyuanService.selectOne(new EntityWrapper<XueyuanEntity>().eq("username", username));
        if(xueyuan==null || !xueyuan.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(xueyuan.getId(),username, "xueyuan", "学员");
        R r = R.ok();
        r.put("token", token);
        r.put("role","学员");
        r.put("username",xueyuan.getXueyuanName());
        r.put("tableName","xueyuan");
        r.put("userId",xueyuan.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody XueyuanEntity xueyuan){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<XueyuanEntity> queryWrapper = new EntityWrapper<XueyuanEntity>()
            .eq("username", xueyuan.getUsername())
            .or()
            .eq("xueyuan_phone", xueyuan.getXueyuanPhone())
            .or()
            .eq("xueyuan_id_number", xueyuan.getXueyuanIdNumber())
            ;
        XueyuanEntity xueyuanEntity = xueyuanService.selectOne(queryWrapper);
        if(xueyuanEntity != null)
            return R.error("账户或者学员手机号或者学员身份证号已经被使用");
        xueyuan.setCreateTime(new Date());
        xueyuanService.insert(xueyuan);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        XueyuanEntity xueyuan = new XueyuanEntity();
        xueyuan.setPassword("123456");
        xueyuan.setId(id);
        xueyuanService.updateById(xueyuan);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        XueyuanEntity xueyuan = xueyuanService.selectOne(new EntityWrapper<XueyuanEntity>().eq("username", username));
        if(xueyuan!=null){
            xueyuan.setPassword("123456");
            boolean b = xueyuanService.updateById(xueyuan);
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
    public R getCurrXueyuan(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        XueyuanEntity xueyuan = xueyuanService.selectById(id);
        if(xueyuan !=null){
            //entity转view
            XueyuanView view = new XueyuanView();
            BeanUtils.copyProperties( xueyuan , view );//把实体数据重构到view中

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
