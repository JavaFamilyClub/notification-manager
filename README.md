# 1. 简介
> 通知管理器

[![Build](https://github.com/JavaFamilyClub/notification-manager/actions/workflows/maven-build.yml/badge.svg)](https://github.com/JavaFamilyClub/notification-manager/actions/workflows/maven-build.yml)
[![MavenPublishSnapshot](https://github.com/JavaFamilyClub/notification-manager/actions/workflows/maven-publish-snapshot.yml/badge.svg)](https://github.com/JavaFamilyClub/notification-manager/actions/workflows/maven-publish-snapshot.yml)
[![MavenPublishRelease](https://github.com/JavaFamilyClub/notification-manager/actions/workflows/maven-publish-release.yml/badge.svg)](https://github.com/JavaFamilyClub/notification-manager/actions/workflows/maven-publish-release.yml)

# 2. 路线图

详情请参考 [Notification-Manager Roadmap](https://github.com/orgs/JavaFamilyClub/projects/3/views/1)

* [飞书通知](./feishu-notification-spring-boot-starter)
  * 普通文本
  * 富文本
  * 图片(delay)
  * 消息卡片

* [钉钉通知](./dingtalk-notification-spring-boot-starter)
  * 普通文本
  * MarkDown
  * Link
  * 卡片消息
    * 单按钮
    * 多按钮
    * feed card

* [UCloud 短信](./ucloud-sms-notification-spring-boot-starter)
> UCloud 短信通知

* [Tencent 短信](./tencent-sms-notification-spring-boot-starter)
> 腾讯短信通知

* [邮箱](./email-notification-spring-boot-starter)
> 邮箱通知

# 3. 示例代码

> 所有的示例代码都在 [examples](./examples)

* `单独的通知组件`: [单独 Starter 引用示例](./examples/demo-notification-manager)
* `notification-manager-all`: [notification-manager-all Starter 引用示例](./examples/demo-notification-manager-all)
* `通知抑制示例`: [抑制配置示例](./examples/demo-notification-manager-inhibit)

