import {axios,baseurl} from '../api/api.js'
import qs from 'qs';

export const login = (params) => {
  return axios.post(baseurl+`/ajax/loginajax`, qs.stringify(params))
    .then(res => res.data)
}

export const getmenu = () => {
  return axios.post(baseurl+`/ajax/getUserMenu`)
    .then(res => res.data)
}

export const loginOut = () => {
  return axios.post(baseurl+`/ajax/loginOut`)
    .then(res => res.data)
}
