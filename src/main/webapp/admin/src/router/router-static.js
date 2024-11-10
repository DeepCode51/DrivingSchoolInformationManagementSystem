import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

     import users from '@/views/modules/users/list'
    import cheliang from '@/views/modules/cheliang/list'
    import dictionary from '@/views/modules/dictionary/list'
    import jiaolian from '@/views/modules/jiaolian/list'
    import kaoshi from '@/views/modules/kaoshi/list'
    import liancheyuyue from '@/views/modules/liancheyuyue/list'
    import xueyuan from '@/views/modules/xueyuan/list'
    import dictionaryCheliang from '@/views/modules/dictionaryCheliang/list'
    import dictionaryKaoshi from '@/views/modules/dictionaryKaoshi/list'
    import dictionaryLiancheyuyueYesno from '@/views/modules/dictionaryLiancheyuyueYesno/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryTongguo from '@/views/modules/dictionaryTongguo/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
    ,{
        path: '/dictionaryCheliang',
        name: '车辆类型',
        component: dictionaryCheliang
    }
    ,{
        path: '/dictionaryKaoshi',
        name: '考试类型',
        component: dictionaryKaoshi
    }
    ,{
        path: '/dictionaryLiancheyuyueYesno',
        name: '申请状态',
        component: dictionaryLiancheyuyueYesno
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryTongguo',
        name: '是否通过',
        component: dictionaryTongguo
    }


    ,{
        path: '/cheliang',
        name: '车辆',
        component: cheliang
      }
    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/jiaolian',
        name: '教练',
        component: jiaolian
      }
    ,{
        path: '/kaoshi',
        name: '考试',
        component: kaoshi
      }
    ,{
        path: '/liancheyuyue',
        name: '练车预约',
        component: liancheyuyue
      }
    ,{
        path: '/xueyuan',
        name: '学员',
        component: xueyuan
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
