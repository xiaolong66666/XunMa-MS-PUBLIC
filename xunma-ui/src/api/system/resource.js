import request from '@/utils/request'

// 查询资源管理列表
export function listResource(query) {
  return request({
    url: '/system/resource/list',
    method: 'get',
    params: query
  })
}

// 查询资源管理详细
export function getResource(id) {
  return request({
    url: '/system/resource/' + id,
    method: 'get'
  })
}

// 新增资源管理
export function addResource(data) {
  return request({
    url: '/system/resource',
    method: 'post',
    data: data
  })
}

// 修改资源管理
export function updateResource(data) {
  return request({
    url: '/system/resource',
    method: 'put',
    data: data
  })
}

// 删除资源管理
export function delResource(id) {
  return request({
    url: '/system/resource/' + id,
    method: 'delete'
  })
}
