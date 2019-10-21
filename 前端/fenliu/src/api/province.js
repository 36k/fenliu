import request from '@/utils/request'

export function getProvinceList() {
  return request({
    url: `/province`,
    method: 'get'
  })
}

export function getProvinces(path) {
  return request({
    url: `/province/${path.page}/${path.size}/${path.keyword}`,
    method: 'get'
  })
}
export function delByid(id) {
  return request({
    url: `/province/${id}`,
    method: 'delete'
  })
}
export function update(data) {
  return request({
    url: `/province`,
    method: 'put',
    data
  })
}
export function insert(data) {
  return request({
    url: `/province`,
    method: 'post',
    data
  })
}
export function deleteByList(data) {
  return request({
    url: `/province`,
    method: 'delete',
    data
  })
}
