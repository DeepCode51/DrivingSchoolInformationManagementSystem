<template>
    <div class="addEdit-block">
        <el-form
                class="detail-form-content"
                ref="ruleForm"
                :model="ruleForm"
                :rules="rules"
                label-width="80px"
                :style="{backgroundColor:addEditForm.addEditBoxColor}">
            <el-row>
                <el-col :span="12"  v-if="sessionTable !='jiaolian'">
                    <el-form-item class="select" v-if="type!='info'"  label="教练" prop="jiaolianId">
                        <el-select v-model="ruleForm.jiaolianId" :disabled="ro.jiaolianId" filterable placeholder="请选择教练" @change="jiaolianChange">
                            <el-option
                                    v-for="(item,index) in jiaolianOptions"
                                    v-bind:key="item.id"
                                    :label="item.jiaolianName"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12"  v-if="sessionTable !='jiaolian' ">
                    <el-form-item class="input" v-if="type!='info'"  label="教练姓名" prop="jiaolianName">
                        <el-input v-model="jiaolianForm.jiaolianName"
                                  placeholder="教练姓名" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="教练姓名" prop="jiaolianName">
                            <el-input v-model="ruleForm.jiaolianName"
                                      placeholder="教练姓名" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='jiaolian' ">
                    <el-form-item class="input" v-if="type!='info'"  label="教练手机号" prop="jiaolianPhone">
                        <el-input v-model="jiaolianForm.jiaolianPhone"
                                  placeholder="教练手机号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="教练手机号" prop="jiaolianPhone">
                            <el-input v-model="ruleForm.jiaolianPhone"
                                      placeholder="教练手机号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='jiaolian' ">
                    <el-form-item class="input" v-if="type!='info'"  label="教练身份证号" prop="jiaolianIdNumber">
                        <el-input v-model="jiaolianForm.jiaolianIdNumber"
                                  placeholder="教练身份证号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="教练身份证号" prop="jiaolianIdNumber">
                            <el-input v-model="ruleForm.jiaolianIdNumber"
                                      placeholder="教练身份证号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24" v-if="sessionTable !='jiaolian' ">
                    <el-form-item class="upload" v-if="type!='info' && !ro.jiaolianPhoto" label="教练头像" prop="jiaolianPhoto">
                        <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (jiaolianForm.jiaolianPhoto || '').split(',')" :src="item" width="100" height="100">
                    </el-form-item>
                    <div v-else>
                        <el-form-item v-if="ruleForm.jiaolianPhoto" label="教练头像" prop="jiaolianPhoto">
                            <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (ruleForm.jiaolianPhoto || '').split(',')" :src="item" width="100" height="100">
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='jiaolian' ">
                    <el-form-item class="input" v-if="type!='info'"  label="电子邮箱" prop="jiaolianEmail">
                        <el-input v-model="jiaolianForm.jiaolianEmail"
                                  placeholder="电子邮箱" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="电子邮箱" prop="jiaolianEmail">
                            <el-input v-model="ruleForm.jiaolianEmail"
                                      placeholder="电子邮箱" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='xueyuan'">
                    <el-form-item class="select" v-if="type!='info'"  label="学员" prop="xueyuanId">
                        <el-select v-model="ruleForm.xueyuanId" :disabled="ro.xueyuanId" filterable placeholder="请选择学员" @change="xueyuanChange">
                            <el-option
                                    v-for="(item,index) in xueyuanOptions"
                                    v-bind:key="item.id"
                                    :label="item.xueyuanName"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12"  v-if="sessionTable !='xueyuan' ">
                    <el-form-item class="input" v-if="type!='info'"  label="学员姓名" prop="xueyuanName">
                        <el-input v-model="xueyuanForm.xueyuanName"
                                  placeholder="学员姓名" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="学员姓名" prop="xueyuanName">
                            <el-input v-model="ruleForm.xueyuanName"
                                      placeholder="学员姓名" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='xueyuan' ">
                    <el-form-item class="input" v-if="type!='info'"  label="学员手机号" prop="xueyuanPhone">
                        <el-input v-model="xueyuanForm.xueyuanPhone"
                                  placeholder="学员手机号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="学员手机号" prop="xueyuanPhone">
                            <el-input v-model="ruleForm.xueyuanPhone"
                                      placeholder="学员手机号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='xueyuan' ">
                    <el-form-item class="input" v-if="type!='info'"  label="学员身份证号" prop="xueyuanIdNumber">
                        <el-input v-model="xueyuanForm.xueyuanIdNumber"
                                  placeholder="学员身份证号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="学员身份证号" prop="xueyuanIdNumber">
                            <el-input v-model="ruleForm.xueyuanIdNumber"
                                      placeholder="学员身份证号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24" v-if="sessionTable !='xueyuan' ">
                    <el-form-item class="upload" v-if="type!='info' && !ro.xueyuanPhoto" label="学员头像" prop="xueyuanPhoto">
                        <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (xueyuanForm.xueyuanPhoto || '').split(',')" :src="item" width="100" height="100">
                    </el-form-item>
                    <div v-else>
                        <el-form-item v-if="ruleForm.xueyuanPhoto" label="学员头像" prop="xueyuanPhoto">
                            <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (ruleForm.xueyuanPhoto || '').split(',')" :src="item" width="100" height="100">
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='xueyuan' ">
                    <el-form-item class="input" v-if="type!='info'"  label="电子邮箱" prop="xueyuanEmail">
                        <el-input v-model="xueyuanForm.xueyuanEmail"
                                  placeholder="电子邮箱" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="电子邮箱" prop="xueyuanEmail">
                            <el-input v-model="ruleForm.xueyuanEmail"
                                      placeholder="电子邮箱" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <input id="updateId" name="id" type="hidden">
            <input id="xueyuanId" name="xueyuanId" type="hidden">
            <input id="jiaolianId" name="jiaolianId" type="hidden">
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="考试编号" prop="kaoshiUuidNumber">
                       <el-input v-model="ruleForm.kaoshiUuidNumber"
                                 placeholder="考试编号" clearable  :readonly="ro.kaoshiUuidNumber"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="考试编号" prop="kaoshiUuidNumber">
                           <el-input v-model="ruleForm.kaoshiUuidNumber"
                                     placeholder="考试编号" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="考试名称" prop="kaoshiName">
                       <el-input v-model="ruleForm.kaoshiName"
                                 placeholder="考试名称" clearable  :readonly="ro.kaoshiName"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="考试名称" prop="kaoshiName">
                           <el-input v-model="ruleForm.kaoshiName"
                                     placeholder="考试名称" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
                <el-col :span="12">
                    <el-form-item class="select" v-if="type!='info'"  label="考试类型" prop="kaoshiTypes">
                        <el-select v-model="ruleForm.kaoshiTypes" :disabled="ro.kaoshiTypes" placeholder="请选择考试类型">
                            <el-option
                                v-for="(item,index) in kaoshiTypesOptions"
                                v-bind:key="item.codeIndex"
                                :label="item.indexName"
                                :value="item.codeIndex">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="考试类型" prop="kaoshiValue">
                        <el-input v-model="ruleForm.kaoshiValue"
                            placeholder="考试类型" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="得分" prop="kaoshiNumber">
                       <el-input v-model="ruleForm.kaoshiNumber"
                                 placeholder="得分" clearable  :readonly="ro.kaoshiNumber"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="得分" prop="kaoshiNumber">
                           <el-input v-model="ruleForm.kaoshiNumber"
                                     placeholder="得分" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
                <el-col :span="12">
                    <el-form-item class="select" v-if="type!='info'"  label="考试结果" prop="tongguoTypes">
                        <el-select v-model="ruleForm.tongguoTypes" :disabled="ro.tongguoTypes" placeholder="请选择考试结果">
                            <el-option
                                v-for="(item,index) in tongguoTypesOptions"
                                v-bind:key="item.codeIndex"
                                :label="item.indexName"
                                :value="item.codeIndex">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="考试结果" prop="tongguoValue">
                        <el-input v-model="ruleForm.tongguoValue"
                            placeholder="考试结果" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24">
                    <el-form-item v-if="type!='info'"  label="考试详情" prop="kaoshiContent">
                        <editor style="min-width: 200px; max-width: 600px;"
                                v-model="ruleForm.kaoshiContent"
                                class="editor"
                                action="file/upload">
                        </editor>
                    </el-form-item>
                    <div v-else>
                        <el-form-item v-if="ruleForm.kaoshiContent" label="考试详情" prop="kaoshiContent">
                            <span v-html="ruleForm.kaoshiContent"></span>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24">
                    <el-form-item v-if="type!='info'"  class="input" label="考试时间" prop="kaoshiTime">
                        <el-date-picker
                                value-format="yyyy-MM-dd HH:mm:ss"
                                v-model="ruleForm.kaoshiTime"
                                type="datetime"
                                placeholder="考试时间"
                                :disabled="ro.kaoshiTime">
                        </el-date-picker>
                    </el-form-item>
                    <div v-else>
                        <el-form-item v-if="ruleForm.kaoshiTime" label="考试时间" prop="kaoshiTime">
                            <span v-html="ruleForm.kaoshiTime"></span>
                        </el-form-item>
                    </div>
                </el-col>

            </el-row>
            <el-form-item class="btn">
                <el-button v-if="type!='info'" type="primary" class="btn-success" @click="onSubmit">提交</el-button>
                <el-button v-if="type!='info'" class="btn-close" @click="back()">取消</el-button>
                <el-button v-if="type=='info'" class="btn-close" @click="back()">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import styleJs from "../../../utils/style.js";
    // 数字，邮件，手机，url，身份证校验
    import { isNumber,isIntNumer,isEmail,isPhone, isMobile,isURL,checkIdCard } from "@/utils/validate";
    export default {
        data() {
            return {
                addEditForm:null,
                id: '',
                type: '',
                sessionTable : "",//登录账户所在表名
                role : "",//权限
                userId:"",//当前登录人的id
                jiaolianForm: {},
                xueyuanForm: {},
                ro:{
                    xueyuanId: false,
                    jiaolianId: false,
                    kaoshiUuidNumber: false,
                    kaoshiName: false,
                    kaoshiTypes: false,
                    kaoshiNumber: false,
                    tongguoTypes: false,
                    kaoshiContent: false,
                    kaoshiTime: false,
                    insertTime: false,
                },
                ruleForm: {
                    xueyuanId: '',
                    jiaolianId: '',
                    kaoshiUuidNumber: new Date().getTime(),
                    kaoshiName: '',
                    kaoshiTypes: '',
                    kaoshiNumber: '',
                    tongguoTypes: '',
                    kaoshiContent: '',
                    kaoshiTime: '',
                    insertTime: '',
                },
                kaoshiTypesOptions : [],
                tongguoTypesOptions : [],
                jiaolianOptions : [],
                xueyuanOptions : [],
                rules: {
                   xueyuanId: [
                              { required: true, message: '学员不能为空', trigger: 'blur' },
                              {  pattern: /^[1-9][0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   jiaolianId: [
                              { required: true, message: '教练不能为空', trigger: 'blur' },
                              {  pattern: /^[1-9][0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   kaoshiUuidNumber: [
                              { required: true, message: '考试编号不能为空', trigger: 'blur' },
                          ],
                   kaoshiName: [
                              { required: true, message: '考试名称不能为空', trigger: 'blur' },
                          ],
                   kaoshiTypes: [
                              { required: true, message: '考试类型不能为空', trigger: 'blur' },
                              {  pattern: /^[1-9][0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   kaoshiNumber: [
                              { required: true, message: '得分不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]{0,6}(\.[0-9]{1,2})?$/,
                                  message: '只允许输入整数6位,小数2位的数字',
                                  trigger: 'blur'
                              }
                          ],
                   tongguoTypes: [
                              { required: true, message: '考试结果不能为空', trigger: 'blur' },
                              {  pattern: /^[1-9][0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   kaoshiContent: [
                              { required: true, message: '考试详情不能为空', trigger: 'blur' },
                          ],
                   kaoshiTime: [
                              { required: true, message: '考试时间不能为空', trigger: 'blur' },
                          ],
                   insertTime: [
                              { required: true, message: '添加时间不能为空', trigger: 'blur' },
                          ],
                }
            };
        },
        props: ["parent"],
        computed: {
        },
        created() {
            //获取当前登录用户的信息
            this.sessionTable = this.$storage.get("sessionTable");
            this.role = this.$storage.get("role");
            this.userId = this.$storage.get("userId");


            if (this.role != "管理员"){
            }
            this.addEditForm = styleJs.addStyle();
            this.addEditStyleChange()
            this.addEditUploadStyleChange()
            //获取下拉框信息
                this.$http({
                    url:`dictionary/page?page=1&limit=100&sort=&order=&dicCode=kaoshi_types`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.kaoshiTypesOptions = data.data.list;
                    }
                });
                this.$http({
                    url:`dictionary/page?page=1&limit=100&sort=&order=&dicCode=tongguo_types`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.tongguoTypesOptions = data.data.list;
                    }
                });

         this.$http({
             url: `jiaolian/page?page=1&limit=100`,
             method: "get"
         }).then(({ data }) => {
             if (data && data.code === 0) {
                this.jiaolianOptions = data.data.list;
            }
         });
         this.$http({
             url: `xueyuan/page?page=1&limit=100`,
             method: "get"
         }).then(({ data }) => {
             if (data && data.code === 0) {
                this.xueyuanOptions = data.data.list;
            }
         });

        },
        mounted() {
        },
        methods: {
            // 下载
            download(file){
                window.open(`${file}`)
            },
            // 初始化
            init(id,type) {
                if (id) {
                    this.id = id;
                    this.type = type;
                }
                if(this.type=='info'||this.type=='else'){
                    this.info(id);
                }
                // 获取用户信息
                this.$http({
                    url:`${this.$storage.get("sessionTable")}/session`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        var json = data.data;
                    } else {
                        this.$message.error(data.msg);
                    }
                });
            },
            jiaolianChange(id){
                this.$http({
                    url: `jiaolian/info/`+id,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.jiaolianForm = data.data;
                    }
                });
            },
            xueyuanChange(id){
                this.$http({
                    url: `xueyuan/info/`+id,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.xueyuanForm = data.data;
                    }
                });
            },
            // 多级联动参数
            info(id) {
                let _this =this;
                _this.$http({
                    url: `kaoshi/info/${id}`,
                    method: 'get'
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        _this.ruleForm = data.data;
                        _this.jiaolianChange(data.data.jiaolianId)
                        _this.xueyuanChange(data.data.xueyuanId)
                    } else {
                        _this.$message.error(data.msg);
                    }
                });
            },
            // 提交
            onSubmit() {
                this.$refs["ruleForm"].validate(valid => {
                    if (valid) {
                        this.$http({
                            url:`kaoshi/${!this.ruleForm.id ? "save" : "update"}`,
                            method: "post",
                            data: this.ruleForm
                        }).then(({ data }) => {
                            if (data && data.code === 0) {
                                this.$message({
                                    message: "操作成功",
                                    type: "success",
                                    duration: 1500,
                                    onClose: () => {
                                        this.parent.showFlag = true;
                                        this.parent.addOrUpdateFlag = false;
                                        this.parent.kaoshiCrossAddOrUpdateFlag = false;
                                        this.parent.search();
                                        this.parent.contentStyleChange();
                                    }
                                });
                            } else {
                                this.$message.error(data.msg);
                            }
                        });
                    }
                });
            },
            // 获取uuid
            getUUID () {
                return new Date().getTime();
            },
            // 返回
            back() {
                this.parent.showFlag = true;
                this.parent.addOrUpdateFlag = false;
                this.parent.kaoshiCrossAddOrUpdateFlag = false;
                this.parent.contentStyleChange();
            },
            //图片

            addEditStyleChange() {
                this.$nextTick(()=>{
                    // input
                    document.querySelectorAll('.addEdit-block .input .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.inputHeight
                        el.style.color = this.addEditForm.inputFontColor
                        el.style.fontSize = this.addEditForm.inputFontSize
                        el.style.borderWidth = this.addEditForm.inputBorderWidth
                        el.style.borderStyle = this.addEditForm.inputBorderStyle
                        el.style.borderColor = this.addEditForm.inputBorderColor
                        el.style.borderRadius = this.addEditForm.inputBorderRadius
                        el.style.backgroundColor = this.addEditForm.inputBgColor
                    })
                    document.querySelectorAll('.addEdit-block .input .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.inputHeight
                        el.style.color = this.addEditForm.inputLableColor
                        el.style.fontSize = this.addEditForm.inputLableFontSize
                    })
                    // select
                    document.querySelectorAll('.addEdit-block .select .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.selectHeight
                        el.style.color = this.addEditForm.selectFontColor
                        el.style.fontSize = this.addEditForm.selectFontSize
                        el.style.borderWidth = this.addEditForm.selectBorderWidth
                        el.style.borderStyle = this.addEditForm.selectBorderStyle
                        el.style.borderColor = this.addEditForm.selectBorderColor
                        el.style.borderRadius = this.addEditForm.selectBorderRadius
                        el.style.backgroundColor = this.addEditForm.selectBgColor
                    })
                    document.querySelectorAll('.addEdit-block .select .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.selectHeight
                        el.style.color = this.addEditForm.selectLableColor
                        el.style.fontSize = this.addEditForm.selectLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .select .el-select__caret').forEach(el=>{
                        el.style.color = this.addEditForm.selectIconFontColor
                        el.style.fontSize = this.addEditForm.selectIconFontSize
                    })
                    // date
                    document.querySelectorAll('.addEdit-block .date .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.dateHeight
                        el.style.color = this.addEditForm.dateFontColor
                        el.style.fontSize = this.addEditForm.dateFontSize
                        el.style.borderWidth = this.addEditForm.dateBorderWidth
                        el.style.borderStyle = this.addEditForm.dateBorderStyle
                        el.style.borderColor = this.addEditForm.dateBorderColor
                        el.style.borderRadius = this.addEditForm.dateBorderRadius
                        el.style.backgroundColor = this.addEditForm.dateBgColor
                    })
                    document.querySelectorAll('.addEdit-block .date .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.dateHeight
                        el.style.color = this.addEditForm.dateLableColor
                        el.style.fontSize = this.addEditForm.dateLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .date .el-input__icon').forEach(el=>{
                        el.style.color = this.addEditForm.dateIconFontColor
                        el.style.fontSize = this.addEditForm.dateIconFontSize
                        el.style.lineHeight = this.addEditForm.dateHeight
                    })
                    // upload
                    let iconLineHeight = parseInt(this.addEditForm.uploadHeight) - parseInt(this.addEditForm.uploadBorderWidth) * 2 + 'px'
                    document.querySelectorAll('.addEdit-block .upload .el-upload--picture-card').forEach(el=>{
                        el.style.width = this.addEditForm.uploadHeight
                        el.style.height = this.addEditForm.uploadHeight
                        el.style.borderWidth = this.addEditForm.uploadBorderWidth
                        el.style.borderStyle = this.addEditForm.uploadBorderStyle
                        el.style.borderColor = this.addEditForm.uploadBorderColor
                        el.style.borderRadius = this.addEditForm.uploadBorderRadius
                        el.style.backgroundColor = this.addEditForm.uploadBgColor
                    })
                    document.querySelectorAll('.addEdit-block .upload .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.uploadHeight
                        el.style.color = this.addEditForm.uploadLableColor
                        el.style.fontSize = this.addEditForm.uploadLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .upload .el-icon-plus').forEach(el=>{
                        el.style.color = this.addEditForm.uploadIconFontColor
                        el.style.fontSize = this.addEditForm.uploadIconFontSize
                        el.style.lineHeight = iconLineHeight
                        el.style.display = 'block'
                    })
                    // 多文本输入框
                    document.querySelectorAll('.addEdit-block .textarea .el-textarea__inner').forEach(el=>{
                        el.style.height = this.addEditForm.textareaHeight
                        el.style.color = this.addEditForm.textareaFontColor
                        el.style.fontSize = this.addEditForm.textareaFontSize
                        el.style.borderWidth = this.addEditForm.textareaBorderWidth
                        el.style.borderStyle = this.addEditForm.textareaBorderStyle
                        el.style.borderColor = this.addEditForm.textareaBorderColor
                        el.style.borderRadius = this.addEditForm.textareaBorderRadius
                        el.style.backgroundColor = this.addEditForm.textareaBgColor
                    })
                    document.querySelectorAll('.addEdit-block .textarea .el-form-item__label').forEach(el=>{
                        // el.style.lineHeight = this.addEditForm.textareaHeight
                        el.style.color = this.addEditForm.textareaLableColor
                        el.style.fontSize = this.addEditForm.textareaLableFontSize
                    })
                    // 保存
                    document.querySelectorAll('.addEdit-block .btn .btn-success').forEach(el=>{
                        el.style.width = this.addEditForm.btnSaveWidth
                        el.style.height = this.addEditForm.btnSaveHeight
                        el.style.color = this.addEditForm.btnSaveFontColor
                        el.style.fontSize = this.addEditForm.btnSaveFontSize
                        el.style.borderWidth = this.addEditForm.btnSaveBorderWidth
                        el.style.borderStyle = this.addEditForm.btnSaveBorderStyle
                        el.style.borderColor = this.addEditForm.btnSaveBorderColor
                        el.style.borderRadius = this.addEditForm.btnSaveBorderRadius
                        el.style.backgroundColor = this.addEditForm.btnSaveBgColor
                    })
                    // 返回
                    document.querySelectorAll('.addEdit-block .btn .btn-close').forEach(el=>{
                        el.style.width = this.addEditForm.btnCancelWidth
                        el.style.height = this.addEditForm.btnCancelHeight
                        el.style.color = this.addEditForm.btnCancelFontColor
                        el.style.fontSize = this.addEditForm.btnCancelFontSize
                        el.style.borderWidth = this.addEditForm.btnCancelBorderWidth
                        el.style.borderStyle = this.addEditForm.btnCancelBorderStyle
                        el.style.borderColor = this.addEditForm.btnCancelBorderColor
                        el.style.borderRadius = this.addEditForm.btnCancelBorderRadius
                        el.style.backgroundColor = this.addEditForm.btnCancelBgColor
                    })
                })
            },
            addEditUploadStyleChange() {
                this.$nextTick(()=>{
                    document.querySelectorAll('.addEdit-block .upload .el-upload-list--picture-card .el-upload-list__item').forEach(el=>{
                        el.style.width = this.addEditForm.uploadHeight
                        el.style.height = this.addEditForm.uploadHeight
                        el.style.borderWidth = this.addEditForm.uploadBorderWidth
                        el.style.borderStyle = this.addEditForm.uploadBorderStyle
                        el.style.borderColor = this.addEditForm.uploadBorderColor
                        el.style.borderRadius = this.addEditForm.uploadBorderRadius
                        el.style.backgroundColor = this.addEditForm.uploadBgColor
                    })
                })
            },
        }
    };
</script>
<style lang="scss">
.editor{
  height: 500px;

  & /deep/ .ql-container {
	  height: 310px;
  }
}
.amap-wrapper {
  width: 100%;
  height: 500px;
}
.search-box {
  position: absolute;
}
.addEdit-block {
	margin: -10px;
}
.detail-form-content {
	padding: 12px;
}
.btn .el-button {
  padding: 0;
}</style>

