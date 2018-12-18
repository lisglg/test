-- 用户表
CREATE TABLE `tb_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) COMMENT '密码',
  `create_time` datetime COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- 用户Token表
CREATE TABLE `tb_token` (
  `user_id` bigint NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime COMMENT '过期时间',
  `update_time` datetime COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户Token';

-- 账号：13612345678  密码：admin
INSERT INTO `tb_user` (`username`, `mobile`, `password`, `create_time`) VALUES ('mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');

-- 查询视图
select * from (
  select
      tbl.id,
      tbl.nick_name,-- 昵称
      tbl.phone,-- 手机号
      tbl.transfer_np,-- 转出NP
      tbl.transfer_on,-- 到账NP
      tbl.buy_np,-- 买入KGC
      tbl.seller_np,-- 卖出KGC
      tbl.recharge_kgc, -- 充值NP
      tbl.recharge_org_integral,-- 充值矿石积分
      tbl.seller_order_np,-- 卖出商品所得NP
      tbl.pay_np,-- 购买商品，下单NP
      tbl.exchanger_np,-- 兑换NP
      tbl.red_np_profit, -- 红包NP总额
      tbl.red_ore_profit,-- 红包矿石积分总额
      tbl.kgc,-- 账户剩余NP
      tbl.ore_integral,-- 账户剩余矿石积分
      tbl.transfer_org_integral,-- 转账奖励的矿石积分
      tbl.transfer_org_integral_on,-- 到账矿石积分
      tbl.buy_org_integral,-- 买入矿石积分
      tbl.seller_org_integral,-- 卖出KGC
      tbl.exchanger_org_integral,-- 兑换矿石积分
      IFNULL(tbl.pay_np*0.8,0) mall_seller_ore_integral,-- 购买商品奖励的矿石积分
      IFNULL(tbl.seller_order_np*0.2,0) mall_ore_integral, -- 卖出商品获得20%矿石积分
      tbl.org_integral_reward,-- 奖励的矿石积分（到达一定等级时，推荐人数到达目标值时）
      IFNULL(tbl.transfer_on,0)+IFNULL(tbl.recharge_kgc,0)+IFNULL(tbl.buy_np*0.8,0)+IFNULL(tbl.seller_order_np*0.8,0)+IFNULL(tbl.red_np_profit,0)  np_total_revenue,-- 到账+充值+买入NP*0.8+卖出商品*0.8+红包收益
      IFNULL(tbl.transfer_np,0)+IFNULL(tbl.seller_np,0)+IFNULL(tbl.pay_np,0)+IFNULL(tbl.exchanger_np,0)  np_total_expenses,-- 转账+卖出KGC+购买商品NP+兑换NP
      IFNULL(tbl.transfer_org_integral,0) + IFNULL(tbl.transfer_org_integral_on,0) + IFNULL(tbl.buy_org_integral, 0) +
      IFNULL(tbl.seller_org_integral, 0) + IFNULL(tbl.seller_order_np * 0.2, 0) + IFNULL(tbl.pay_np * 0.8, 0) +
      IFNULL(tbl.recharge_org_integral,0) + IFNULL(tbl.exchanger_org_integral,0)+IFNULL(tbl.org_integral_reward,0) ore_total_revenue, -- 矿石积分总收入
      IFNULL(tbl.red_ore_profit,0)  ore_total_expenses,
      (IFNULL(tbl.transfer_on,0)+IFNULL(tbl.recharge_kgc,0)+IFNULL(tbl.buy_np*0.8,0)+IFNULL(tbl.seller_order_np*0.8,0)+IFNULL(tbl.red_np_profit,0))-(IFNULL(tbl.transfer_np,0)+IFNULL(tbl.seller_np,0)+IFNULL(tbl.pay_np,0)+IFNULL(tbl.exchanger_np,0)) exception_np,
      (IFNULL(tbl.transfer_org_integral,0) + IFNULL(tbl.transfer_org_integral_on,0) + IFNULL(tbl.buy_org_integral, 0) +
      IFNULL(tbl.seller_org_integral, 0) + IFNULL(tbl.seller_order_np * 0.2, 0) + IFNULL(tbl.pay_np * 0.8, 0) +
      IFNULL(tbl.recharge_org_integral,0) + IFNULL(tbl.exchanger_org_integral,0)+IFNULL(tbl.org_integral_reward,0))-IFNULL(tbl.red_ore_profit,0) excetipn_org
   from (SELECT
      u.id,-- ID 主键自增长
      u.nick_name,-- 昵称
      u.phone,-- 手机号
      IFNULL((select sum(kgc) from user_transfer_record where user_id=u.id and type=1),0) transfer_np,-- 转出NP
      IFNULL((select sum(kgc) from user_transfer_record where user_id=u.id and type=2),0) transfer_on,-- 到账NP
      IFNULL((select sum(kgc) from user_buy_record where user_id=u.id and recharge_status=1),0) buy_np,-- 买入NP
      IFNULL((select sum(kgc) from user_buy_record where seller_user_id=u.id and recharge_status=1),0) seller_np,-- 卖出KGC
      IFNULL(sum(r.kgc),0) recharge_kgc, -- 充值NP
      IFNULL(sum(r.org_integral),0) recharge_org_integral,-- 充值矿石积分
      IFNULL((select sum(amount) from mall_order_info where sell_id=u.id and `status`=3),0) seller_order_np,-- 卖出商品所得NP
      IFNULL((select sum(amount) from mall_order_info where user_id=u.id and `status` not in(0,4)),0) pay_np,-- 购买商品，下单NP
      IFNULL((select sum(kgc) from user_exchange_record where user_id=u.id),0) exchanger_np,-- 兑换NP
      IFNULL(sum(re.kgc),0) red_np_profit, -- 红包NP总额
      IFNULL(sum(re.ore_integral),0) red_ore_profit,-- 红包矿石积分总额
      a.kgc,-- 账户剩余NP
      a.ore_integral,-- 账户剩余矿石积分
      IFNULL((select sum(t.org_integral) from user_transfer_record t where t.user_id=u.id and t.type=1),0) transfer_org_integral,-- 转账奖励的矿石积分
      IFNULL((select sum(t.org_integral) from user_transfer_record t where t.user_id=u.id and t.type=2),0) transfer_org_integral_on,-- 到账矿石积分
      IFNULL((select sum(b.kgc)*0.8 from user_buy_record b where b.user_id=u.id and b.recharge_status=1),0) buy_org_integral,-- 买入矿石积分
      IFNULL((select sum(b.kgc)*0.2 from user_buy_record b where b.seller_user_id=u.id and b.recharge_status=1),0) seller_org_integral,-- 卖出KGC
      IFNULL((select sum(e.kgc*6) from user_exchange_record e where e.user_id=u.id),0) exchanger_org_integral,-- 兑换矿石积分=NP*6倍杠杆率
      IFNULL((select sum(p.ore_integral) from user_pool_profit_record p where p.user_id=u.id and p.profit_type=6 ),0) org_integral_reward -- 流通奖励的矿石积分
    FROM
      user_basic_info u
      left join sys_recharge_record r on u.id=r.user_id
      left join user_pool_profit_record re on (u.id=re.user_id and  re.profit_type in (2,3,4)	)
      left join user_account_info a on u.id=a.user_id
  where 1=1 -- 2.兑换算力 3.流通算力 4.开采算力
group by u.id)tbl )tt  order by tt.exception_np asc;

