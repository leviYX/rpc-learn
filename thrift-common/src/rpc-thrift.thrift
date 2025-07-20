# 用来构建rpc的thrift文件

namespace java com.levi
struct User {
    1: required string name,
    2: required string password
}
service UserService {
    /**
     * 注册用户
     * @param user 用户信息
     * @return 注册结果
     */
    void registerUser(1: User user)

    /**
     * 获取用户信息
     * @param name 用户名称
     * @return 用户信息
     */
    User getUser(1: string name)

}