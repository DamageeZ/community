# CommunityMapsiz
_______________________
## Learning
- Thymeleaf
- **Springboot**
- Maven
- OkHttp
## 已实现功能
- Github OAuth登录:Authorization Http Header
- Cookies登录状态保持
- Interceptor拦截未登录用户访问Publish，跳转回index，防止通过uri直接访问publish
- Publish，问题发布
- Publish成功后的恭喜页面
- 添加了随机生成的state，在回调时进行认证，防止CSRF攻击
- 退出登录 session.invalidate
- Gitee OAuth登录
- Cookie过期后重新登陆时更新token 而不是新建user

## 正在实现功能



