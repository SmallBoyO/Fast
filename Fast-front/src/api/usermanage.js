import {
  axios,
  baseurl
} from '../api/api.js'
import qs from 'qs';

export const checkUserName = (params) => {
  return axios.post(baseurl+`/ajax/UserManager/checkUserName`, qs.stringify(params))
    .then(res => res.data)
}

export const checkName = (params) => {
  return axios.post(baseurl + `/ajax/UserManager/checkName`,qs.stringify(params))
    .then(res => res.data)
}

export const getRight = (params) => {
  return axios.post(baseurl + `/ajax/getRight`,qs.stringify(params))
    .then(res => res.data)
}

export const getRoleList = (params) => {
  return axios.post(baseurl + `/ajax/UserManager/getRoleList`,qs.stringify(params))
    .then(res => res.data)
}

export const getUserRoleList = (params) => {
  return axios.post(baseurl + `/ajax/UserManager/getUserRoleList`,qs.stringify(params))
    .then(res => res.data)
}

export const deleteUser = (params) => {
  return axios.post(baseurl + `/ajax/UserManager/deleteUser`,qs.stringify(params))
    .then(res => res.data)
}

export const userList = (params) => {
  return axios.post(baseurl + `/ajax/UserManager/userList`,qs.stringify(params))
    .then(res => res.data)
}

export const updateUser = (params) => {
  return axios.post(baseurl + `/ajax/UserManager/updateUser`,qs.stringify(params))
    .then(res => res.data)
}

export const addUser = (params) => {
  return axios.post(baseurl + `/ajax/UserManager/addUser`,qs.stringify(params))
    .then(res => res.data)
}
