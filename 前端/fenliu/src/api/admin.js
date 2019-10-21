import request from '@/utils/request'

export function fenliu(data) {
  return request({
    url: '/admin/fenliu',
    method: 'post',
    data
  })
}
export function count() {
  return request({
    url: '/admin/count',
    method: 'get'
  })
}
export function studentInfo() {
  return request({
    url: '/admin/studentInfo',
    method: 'get'
  })
}
