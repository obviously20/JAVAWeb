import request from '@/utils/request.js'

//获取部门列表接口
export const getDeptList = () => {
  return request.get('/depts')//请求地址：基础路径 + /depts
}

//新增部门接口
export const addDeptApi = (deptForm) => {
  return request.post('/depts', deptForm)//请求地址：基础路径 + /depts
}


// 修改操作--更具id查询部门信息
export const queryByIdApi = (id) => {
  return request.get(`/depts/${id}`)//请求地址：基础路径 + /depts/1001
}

// 修改操作--根据id修改部门信息
export const updateApi = (deptForm) => {
  return request.put('/depts', deptForm)
}


// 删除操作--根据id删除部门
export const deleteApi = (id) => {
  return request.delete(`/depts?id=${id}`)//请求地址：基础路径 + /depts?id=1001
} 










