# 单行注释
// 单行注释
/*
 * 多行注释
 */
namespace java com.levi.entity
struct User{
   1: string name ='levi',
   2: optional i32 age,
   3: list<i32> ages = [1,2,3,4],
   4: required i32 hieght
}
exception UserException {
    # 异常编码
    1: i32 code,
    # 异常信息
    2: string message
}

