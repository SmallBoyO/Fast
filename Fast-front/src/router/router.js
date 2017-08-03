import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../page/login.vue'
import menu1 from '../page/menu1.vue'
import menu2 from '../page/menu2.vue'
import home from '../page/home.vue'
import counter from '../page/counter.vue'
Vue.use(VueRouter)

const routes = [{
        path: '/login',
        component: login,
        name: '',
        hidden: true
    },{
        path: '/menu1',
        component: menu1,
        name: '',
        hidden: true
    },{
        path: '/menu2',
        component: menu2,
        name: '',
        hidden: true
    },{
		path: '/home',
        component: home,
        name: '',
        children:[
			{
			  path: '',
			  component: menu2
			},{
			  path: 'menu2',
			  component: menu2
			},{
				path:'counter',
				component: counter
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
