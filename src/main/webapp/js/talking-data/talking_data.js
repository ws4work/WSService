/**
 * Created by w on 2017/9/13.
 */

//Tips!
//*所标注位置需要进行校验

//注册、登录、切换帐户、唤醒游戏时传入玩家账户信息
function login(account) {
    //判断传入json数据
    if (account.indexOf('accountId') < 0) {
        console.error("JOSN数据缺少accountId值")
        return
    }
    if (account.indexOf('level') < 0) {
        console.log("JOSN数据缺少level值")
    }
    if (account.indexOf('gameServer') < 0) {
        console.log("JOSN数据缺少gameServer值")
    }
    if (account.indexOf('accountType') < 0) {
        console.error("JOSN数据缺少accountType值")
    }
    if (account.indexOf('age') < 0) {
        console.log("JOSN数据缺少age值")
    }
    if (account.indexOf('accountName') < 0) {
        console.log("JOSN数据缺少accountName值")
    }
    if (account.indexOf('gender') < 0) {
        console.log("JOSN数据缺少gender值")
    }
    console.log(account)
    var info = eval('(' + account + ')')
    TDGA.Account({
        accountId: info.accountId,
        level: info.level,
        gameServer: info.gameServer,
        accountType: info.accountType,
        age: info.age,
        accountName: info.accountName,
        gender: info.gender
    })
}

//修改账户信息
//单独对帐户的某种信息做修改，可以单独调用以下对应方法
//*accountName--最多支持 64 个字符
function setAccountName(accountName) {
    //设置昵称
    if (checkEmpty(accountName) && accountName.length <= 64) {
        TDGA.Account.setAccountName(accountName)
    } else {
        console.error("请正确输入昵称")
    }
}
//*accountType--默认匿名
function setAccountType(accountType) {
    //设置种类
    //1）匿名： AccountType.ANONYMOUS
    //2）自有帐户显性注册：AccountType.REGISTERED
    //3）国内主流的第三方帐号：
    //      新浪微博：AccountType.SINA_WEIBO
    //      QQ：AccountType.QQ
    //      腾讯微博：AccountType.TENCENT_WEIBO
    //      网龙91：AccountType.ND91
    //4）系统预留了10种自定义的帐户类型，分别为：AccountType.TYPE1到AccountType.TYPE10
    if (checkEmpty(accountType) && accountType <= 4) {
        TDGA.Account.setAccountType(accountType)
    } else {
        TDGA.Account.setAccountType(1)
    }
}
//*level--1-1000,默认1级
function setLevel(level) {
    //设置级别
    if (checkEmpty(level) && level <= 1000) {
        TDGA.Account.setLevel(level)
    } else {
        TDGA.Account.setLevel(1)
    }
}
//*gender--1，男；2，女,默认男
function setGender(gender) {
    if (checkEmpty(gender) && gender <= 2) {
        TDGA.Account.setGender(gender)
    } else {
        TDGA.Account.setGender(gender)
    }
}
//*age--0-120,默认0
function setAge(age) {
    if (checkEmpty(age) && age <= 120) {
        TDGA.Account.setAge(age)
    } else {
        TDGA.Account.setAge(0)
    }
}
//*gameServer--最多16 个字符,默认为空字符串
function setGameServer(gameServer) {
    if (checkEmpty(gameServer) && gameServer.length <= 16) {
        TDGA.Account.setGameServer(gameServer)
    } else {
        console.error("游戏服务器错误")
        TDGA.Account.setGameServer("")
    }
}

//无玩家账户或期望以设备为单位计算玩家
function getDeviceId() {
    //设置设备唯一的账号
    //TDGA.Account({
    //     accountId : TDGA.getDeviceId(),
    //     level : 1,
    //     accountType : 1
    // });
    TDGA.getDeviceId()
    console.log("用户设备唯一的账号:" + TDGA.getDeviceId())
}

//充值请求
//*orderId--64 个字符,订单号唯一
//*iapId--32 个字符,产品唯一
//currencyAmount--现金金额或现金等价物的额度
//virtualCurrencyAmount--虚拟币金额
//*paymentType--支付的途径,16 个字符
function rechargeRequest(orderId, iapId, currencyAmount, virtualCurrencyAmount, paymentType) {
    console.log('发送充值请求')
    if (!checkEmpty(orderId) || orderId.length > 64) {
        console.error("订单号异常")
        return
    }
    if (!checkEmpty(iapId) || iapId.length > 32) {
        console.error("产品异常")
        return
    }
    if (!checkEmpty(currencyAmount)) {
        console.error("现金金额异常")
        return
    }
    if (!checkEmpty(virtualCurrencyAmount)) {
        console.error("虚拟币金额异常")
        return
    }
    if (!checkEmpty(paymentType) || paymentType.length > 64) {
        console.error("支付途径异常")
        return
    }
    TDGA.onChargeRequest({
        //*64 个字符,订单号唯一
        orderId: orderId,
        //*32 个字符,产品唯一
        iapId: iapId,
        //现金金额或现金等价物的额度
        currencyAmount: currencyAmount,
        //*货币类型
        //人民币 CNY；美元 USD；欧元 EUR；如果您使用其他自定义等价物作为现金，亦可使用 ISO4217 中没有的 3 位字母组合传入货币型，我们会在报表页面中提供汇率设定功能
        currencyType: 'CNY',
        //虚拟币金额
        virtualCurrencyAmount: virtualCurrencyAmount,
        //支付的途径
        //*16 个字符
        paymentType: paymentType
    });
    console.log('发送充值请求成功')
}

//充值成功
//*orderId--64 个字符,唯一,且与rechargeRequest中一致
function rechargeSuccess(orderId) {
    console.log('发送充值成功请求')
    if (!checkEmpty(orderId) || orderId.length > 64) {
        console.error("订单号异常")
        return
    }
    TDGA.onChargeSuccess({
        orderId: orderId
        //*32 个字符,产品唯一
        //iapId: '大号宝箱',
        //现金金额或现金等价物的额度
        //currencyAmount: '100',
        //*货币类型
        //人民币 CNY；美元 USD；欧元 EUR；如果您使用其他自定义等价物作为现金，亦可使用 ISO4217 中没有的 3 位字母组合传入货币型，我们会在报表页面中提供汇率设定功能
        //currencyType: 'CNY',
        //虚拟币金额
        //virtualCurrencyAmount: 1000,
        //支付的途径
        //*16 个字符
        //paymentType: 'AliPay'
    });
    console.log('发送充值成功请求成功')
}

//赠予虚拟币
//virtualCurrencyAmount--虚拟币金额
//reason--32 个字符内的中文、空格、英文、数字。不要带有任何开发中的转义字符，如斜杠。注意：最多支持 100 种不同原因。
function reward(virtualCurrencyAmount, reason) {
    if (!checkEmpty(virtualCurrencyAmount)) {
        console.error("虚拟币金额异常")
        return
    }
    if (!checkEmpty(reason)) {
        console.error("赠予原因异常")
        return
    }
    //赠予虚拟币
    TDGA.onReward(virtualCurrencyAmount, reason)
}

//记录付费点
//*itemID--某个消费点的编号，最多 32 个字符。
//itemNumber--消费数量
//priceInVirtualCurrency--虚拟币单价
function consume(itemID, itemNumber, priceInVirtualCurrency) {
    if (!checkEmpty(itemID) || itemID.length > 32) {
        console.error("消费点编号异常")
        return
    }
    if (!checkEmpty(itemNumber)) {
        console.error("消费数量异常")
        return
    }
    if (!checkEmpty(priceInVirtualCurrency)) {
        console.error("虚拟币单价异常")
        return
    }
    TDGA.onItemPurchase({
        item: itemID,
        itemNumber: itemNumber,
        priceInVirtualCurrency: priceInVirtualCurrency
    });
}

//消耗物品或服务等
//*itemID--某个消费点的编号，最多 32 个字符。
//itemNumber--消费数量
function expend(itemID, itemNumber) {
    if (!checkEmpty(itemID) || itemID.length > 32) {
        console.error("消费点编号异常")
        return
    }
    if (!checkEmpty(itemNumber)) {
        console.error("消费数量异常")
        return
    }
    TDGA.onItemUse({
        item: itemID,
        itemNumber: itemNumber
    });
}

//任务、关卡或副本
//*missionId--任务、关卡或副本的编号，最多 32 个字符
//*cause--失败原因，最多 16个字符。共支持100种原因。
//*oper--0:接受或进入;1:完成;-1:失败
function task(missionId, cause, oper) {
    if (!checkEmpty(missionId) || missionId.length > 32) {
        console.error("任务编号异常")
        return
    }
    if (!checkEmpty(cause) || cause.length > 16) {
        console.error("失败原因异常")
        return
    }
    if (undefined == oper || null == oper) {
        console.error("操作异常")
        return
    }
    switch (oper) {
        case 0 :
            //接受或进入
            TDGA.onMissionBegin(missionId)
            break
        case 1:
            //完成
            TDGA.onMissionCompleted(missionId)
            break
        case -1:
            //失败
            TDGA.onMissionFailed(missionId, cause)
            break
        default:
            console.log("操作类型错误")
            break
    }
}

//自定义事件
//TODO
function event() {
    //*自定义事件名称，最多支持 32 个字符
    var eventId = '110'
    //*自定义事件参数名称，key最多支持 32 个字符
    var map = new Array();
    map.put("level", "50-60")     //级别区间，注意是字符串哟！
    map.put("map", "沼泽地阿卡村") //地图场景
    map.put("mission", "屠龙副本") //关卡。
    map.put("reason", "PK致死") //死亡原因
    map.put("coin", "10000～20000") //携带金币数量

    //在游戏程序的event事件中加入下面的代码，也就成功的添加了一个简单的事件到您的游戏程序中
    TDGA.onEvent(eventId, map)
}

//用户退出游戏
function exit() {
    TDGA.onPageLeave();
}

//检验参数是否为空,非空true,空false
function checkEmpty(param) {
    if (null != param && undefined != param) {
        if ('string' == typeof param) {
            if ("" != param && param.length > 0) {
                return true
            }
        } else {
            return true
        }
    } else {
        return false
    }
}