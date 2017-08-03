import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import Navi from './components/Navi.vue'
import App from './app.vue'
import router from './router/router'
import Vuex from 'vuex'
import store from './vuex/store'

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
		console.log("2");
        this.$router.push('/login');
    }
  }
})
