import axios from 'axios'
import router from '../router/router'
axios.defaults.withCredentials = true;
axios.interceptors.response.use(function(response) {
  if (response.data.ret == -100) {
    router.push("/login");
  }
  return response
}, function(error) {
  // Do something with response error
  return Promise.reject(error);
});

const baseurl = `http://127.0.0.1:8081`

export {
  axios,
  baseurl
}
