import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/info',
    method: 'get'
  })
}
export function subscribe(data) {
  return request({
    url: '/subscribe',
    method: 'post',
    data
  })
}
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}
export function reviseAspiration(data) {
  return request({
    url: 'reviseAspiration',
    method: 'post',
    data
  })
}
export function revisePassword(data) {
  return request({
    url: '/revisePassword',
    method: 'post',
    data
  })
}
export function getList(path) {
  return request({
    url: `/user/${path.page}/${path.size}/${path.keyword}`,
    method: 'get'
  })
}
export function delByid(id) {
  return request({
    url: `/user/${id}`,
    method: 'delete'
  })
}
export function update(data) {
  return request({
    url: `/address`,
    method: 'put',
    data
  })
}
export function insert(data) {
  return request({
    url: `/user`,
    method: 'post',
    data
  })
}
export function deleteByList(data) {
  return request({
    url: `/user`,
    method: 'delete',
    data
  })
}
export function resetPassword(data) {
  return request({
    url: `/user/reset`,
    method: 'put',
    data
  })
}

