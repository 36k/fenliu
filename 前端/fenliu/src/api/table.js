import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/table/list',
    method: 'get',
    params
  })
}
export function delByid(params) {
  return request({
    url: '/table/list',
    method: 'delete',
    params
  })
}
