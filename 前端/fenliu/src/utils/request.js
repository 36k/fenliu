import axios from 'axios'
import { resetRouter } from '@/router'
import { removeLogin } from '@/utils/auth'

import router from '@/router'
import store from '../store';
// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    return config
  },
  error => {
    // do something with request error
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    //正常请求
    if (res.code === 20000) {
      return res
      //登录失效，或者未登录
    }else if(res.code=20001){
      resetRouter()
      removeLogin()
      store.state.user.roles=[]
      router.push(`/login?redirect=/`)
      return Promise.reject(res.msg)
    }
     else {
      return Promise.reject(res.msg)
    }
  },
  error => {
    const msg = error
    // Message({
    //   message: msg,
    //   type: 'error',
    //   duration: 5 * 1000
    // })
    return Promise.reject(error)
  }
)

export default service
