include "test1.thrift"

service UserService{
    void add(1:test1.User user)
}