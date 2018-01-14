import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../page/login.vue'
import home from '../page/home.vue'
import UserList from '../page/UserList.vue'
import RoleList from '../page/RoleList.vue'
import MenuManage from '../page/MenuManage.vue'
Vue.use(VueRouter)

const routes = [{
        path: '',
        component: login,
        name: '',
        hidden: true
    },{
        path: '/login',
        component: login,
        name: '',
        hidden: true
    },{
		path: '/home',
        component: home,
        name: '系统管理',
        children:[{
				path:'UserList',
				name:'用户管理',
				component:UserList
			},{
        path:'RoleList',
        name:'角色管理',
        component:RoleList,
      },{
        path:'MenuManage',
        name:'菜单管理',
        component:MenuManage,
      }
		]
	}];
const router = new VueRouter({
    routes:routes
})
router.beforeEach((to, from, next) => {
	next();
});
export default router;
