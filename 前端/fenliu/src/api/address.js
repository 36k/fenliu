import request from '@/utils/request'

export function getAll() {
  return request({
    url: `/address`,
    method: 'get'
  })
}

export function getList(path) {
  return request({
    url: `/address/${path.page}/${path.size}/${path.keyword}`,
    method: 'get'
  })
}
export function delByid(id) {
  return request({
    url: `/address/${id}`,
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
    url: `/address`,
    method: 'post',
    data
  })
}
export function deleteByList(data) {
  return request({
    url: `/address`,
    method: 'delete',
    data
  })
}
