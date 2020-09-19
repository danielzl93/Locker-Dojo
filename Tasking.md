# Tasking

## locker

1. Given small locker 有可用容量，小包裹，When locker存包 Then 存包成功，存放在small locker中，返回票据
2. Given small locker 无可用容量，小包裹，When locker存包 Then 存包失败，柜满异常
3. Given 一张有效票据 When locker取包 Then 取包成功
4. Given 一张与locker型号不符的票据 When locker取包 Then 取包失败，票据型号不符异常
5. Given 一张无效票据 When locker取包 Then 取包失败，无效票据异常

## primaryLockerRobot

1. Given robot管理多个中型locker，例如两个locker，两个locker都有可用容量; When robot存包; Then 成功存入第一个locker，返回票据
2. Given robot管理多个中型locker，例如两个locker，第一个locker已经存满，第二个locker有可用容量; When robot存包; Then 成功存入第二个locker，返回票据
3. Given robot管理多个中型locker，例如两个locker，两个locker都没有可用容量; When robot存包; Then 存包失败，提示储物柜已满
4. Given robot管理多个中型locker，例如两个locker，拿到一张有效的票; When robot取包; Then 取包成功
5. Given robot管理多个中型locker，例如两个locker，拿到一张无效票; When robot取包; Then 取包失败，无效票据异常
6. Given robot管理多个中型locker，例如两个locker，拿到一张非中型locker的票; When robot取包; Then 取包失败，票据型号不符异常

## smartLockerRobot

1. Given robot管理多个大型locker，例如两个locker，第一个locker剩余容量为10，第二个剩余容量为20; When robot存包，Then 成功存入第二个locker，返回票据
2. Given robot管理多个大型locker，例如两个locker，剩余容量都为10; When robot存包，Then 成功存入第一个locker，返回票据
3. Given robot管理多个大型locker，例如两个locker，且两个locker都存满了; When robot存包，Then 存包失败，提示locker已满
4. Given robot管理多个大型locker，例如两个locker，拿到一张有效的票; When robot取包; Then 取包成功
5. Given robot管理多个大型locker，例如两个locker，拿到一张无效票; When robot取包; Then 取包失败，无效票据异常
6. Given robot管理多个大型locker，例如两个locker，拿到一张非中型locker的票; When robot取包; Then 取包失败，票据型号不符异常

## lockerRobotManager
1. Given manager管理储存设备，例如一个locker，一个primaryLockerRobot，一个superLockerRobot，均有可用容量 When manager存包，Then 存包成功，返回票据
2. Given manager管理储存设备，例如一个locker，一个primaryLockerRobot，一个superLockerRobot，有些设备无可用容量 When manager存包，Then 存包成功，存放至有可用容量的设备返回票据
3. Given manager管理储存设备，例如一个locker，一个primaryLockerRobot，一个superLockerRobot，都存满了，When manager存包，Then 存包失败，提示locker已满
4. Given manager管理储存设备，例如一个locker，一个primaryLockerRobot，一个superLockerRobot，拿到一张有效的票; When manager取包; Then 取包成功
5. Given manager管理储存设备，例如一个locker，一个primaryLockerRobot，一个superLockerRobot，拿到一张无效票; When manager取包; Then 取包失败，无效票据异常
