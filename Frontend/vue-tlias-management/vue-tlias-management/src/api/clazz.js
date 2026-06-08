import request from '@/utils/request'

// 分页查询班级列表
// 请求数据样例：/clazzs?name=java&begin=2023-01-01&end=2023-06-30&page=1&pageSize=5
export const queryPageApi = (name,begin,end,page,pageSize) =>{
  return request.get(`/clazzs?name=${name}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`)
}

// 添加班级
export const addClazzApi = (data) =>{
  return request.post('/clazzs',data)
}


//  根据班级ID查询班级详情
export const queryClazzByIdApi = (id) =>{
  return request.get(`/clazzs/${id}`)
}

// 修改班级
export const updateClazzApi = (data) =>{
  return request.put(`/clazzs`,data)
}

// 删除班级
// /clazzs/{id}
export const deleteClazzApi = (id) =>{
  return request.delete(`/clazzs/${id}`)
}

// 查询所有班级
// /clazzs/list
export const queryAllApi = () =>{
  return request.get(`/clazzs/list`)
}