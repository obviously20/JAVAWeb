import request from '@/utils/request'

//  分页查询员工列表
export const queryPageApi = (name,gender,begin,end,page,pageSize) =>{
  return request.get(`/emps?name=${name}&gender=${gender}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`)
}



