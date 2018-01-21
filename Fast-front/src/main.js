import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import Navi from './components/Navi.vue'
import App from './app.vue'
import router from './router/router'
import Vuex from 'vuex'
import store from './vuex/store'
import {axios} from './api/api.js';
Vue.use(ElementUI)
Vue.use(Vuex)

var app = new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App),
  created() {
    this.checkLogin();
  },
  methods:{
    checkLogin(){
      console.log("checkLogin");
        axios.post(`http://127.0.0.1:8081/ajax/checklogin`).then((res)=>{
          if(res.data.ret==1){
            console.log("已登录");
          }else {
            console.log("未登录");
          }
        })
    }
  }
})
