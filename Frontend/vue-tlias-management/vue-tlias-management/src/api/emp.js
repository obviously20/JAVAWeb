import request from '@/utils/request'

//  分页查询员工列表
export const queryPageApi = (name,gender,begin,end,page,pageSize) =>{
  return request.get(`/emps?name=${name}&gender=${gender}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`)
}

//  新增员工
export const addEmpApi = (employee) =>{
    return request.post(`/emps`,employee)
}

// 根据id查询员工
export const queryEmpByIdApi = (id) =>{
    return request.get(`/emps/${id}`)
}

// 修改员工
export const updateEmpApi = (employee) =>{
    return request.put(`/emps`,employee)
}

// 删除员工
export const deleteEmpApi = (ids) =>{
    return request.delete(`/emps?ids=${ids}`)
}

//查询所有员工
export const queryAllApi = () => request.get(`/emps/list`)

