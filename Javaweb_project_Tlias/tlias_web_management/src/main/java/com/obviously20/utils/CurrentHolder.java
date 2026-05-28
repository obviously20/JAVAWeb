package com.obviously20.utils;

public class CurrentHolder {

    /*- ThreadLocal 并不是一个Thread，而是Thread的局部变量。
        - ThreadLocal为每个线程提供一份单独的存储空间，具有线程隔离的效果，不同的线程之间不会相互干扰。*/

    /*- 常见方法：
  - public void set(T value)   设置当前线程的线程局部变量的值
  - public T get()                     返回当前线程所对应的线程局部变量的值
  - public void remove()          移除当前线程的线程局部变量*/

    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
