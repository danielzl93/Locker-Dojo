# Tasking

## locker
1. Given small locker 有可用容量，小包裹，When locker存包 Then 存包成功，存放在small locker中，返回票据
2. Given small locker 无可用容量，小包裹，When locker存包 Then 存包失败，柜满异常
3. Given 一张有效票据 When locker取包 Then 取包成功
4. Given 一张与locker型号不符的票据 When locker取包 Then 取包失败，票据型号不符异常
5. Given 一张无效票据 When locker取包 Then 取包失败，无效票据异常

##