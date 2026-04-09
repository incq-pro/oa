#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import random
from datetime import datetime, timedelta

# 真实姓名库
first_names = ['王', '李', '张', '刘', '陈', '杨', '黄', '赵', '吴', '周', '徐', '孙', '马', '朱', '胡', '郭', '林', '何', '高', '罗']
second_names = ['建国', '秀英', '志明', '美华', '德明', '丽华', '文杰', '小燕', '志强', '雪梅', '建华', '碧玉', '瑞峰', '晓东', '海燕', '俊杰', '雅琪', '建华', '丽华', '志强', '浩宇', '思远', '雨晴', '子轩', '欣怡', '俊杰', '雅婷', '子涵', '浩然', '宇轩']

# 部门
departments = [
    '总经理办公室', '技术研发部', '人力资源部', '财务部', '市场推广部',
    '销售部', '采购部', '质量监管部', '客户服务部', '行政管理部',
    '战略发展部', '法务部', '信息技术部', '供应链管理部', '品牌运营部'
]

# 职称
positions = ['总经理', '副总经理', '总监', '经理', '主管', '专员', '助理', '工程师', '会计', '设计师', '分析师', '顾问']

# 客户公司
companies = [
    '科技有限公司', '实业有限公司', '商贸有限公司', '贸易有限公司', '集团股份有限公司',
    '电子科技有限公司', '网络技术有限公司', '信息科技有限公司', '制药有限公司', '机械制造有限公司',
    '食品有限公司', '建材有限公司', '能源有限公司', '环保科技有限公司', '文化传播有限公司',
    '教育咨询有限公司', '物流有限公司', '物业管理有限公司', '装饰工程有限公司', '园林绿化有限公司',
    '汽车服务有限公司', '家政服务有限公司', '财务咨询有限公司', '律师事务所', '会计师事务所'
]

cities = ['北京', '上海', '广州', '深圳', '杭州', '成都', '武汉', '南京', '西安', '苏州', '天津', '重庆', '青岛', '大连', '长沙', '郑州', '沈阳', '厦门', '福州', '济南']

# 产品
products = [
    ('联想ThinkPad X1 Carbon笔记本', '办公设备', '台', 8999.00),
    ('戴尔OptiPlex 7080台式机', '办公设备', '台', 6599.00),
    ('华为MateBook 14笔记本', '办公设备', '台', 5999.00),
    ('苹果MacBook Air M2', '办公设备', '台', 9999.00),
    ('惠普LaserJet Pro打印机', '办公设备', '台', 1899.00),
    ('得力订书机', '办公用品', '个', 45.00),
    ('三菱中性笔', '办公用品', '支', 8.50),
    ('洛斐机械键盘', '办公用品', '个', 399.00),
    ('索尼WH-1000XM4耳机', '电子设备', '副', 2299.00),
    ('小米路由器AX6000', '电子设备', '台', 599.00),
    ('海康威视监控摄像头', '电子设备', '台', 459.00),
    ('罗技MX Master3鼠标', '办公用品', '个', 699.00),
    ('飞利浦显示器27寸', '电子设备', '台', 1899.00),
    ('三星固态硬盘1TB', '存储设备', '个', 899.00),
    ('金士顿内存条16G', '存储设备', '个', 459.00),
    ('得力文件柜', '办公家具', '个', 680.00),
    ('办公桌椅套装', '办公家具', '套', 1599.00),
    ('会议室投影仪', '办公设备', '台', 4599.00),
    ('企业级路由器', '网络设备', '台', 2899.00),
    ('UPS不间断电源', '电源设备', '台', 1899.00),
    ('硒鼓 HP 136A', '办公耗材', '个', 289.00),
    ('A4打印纸一箱', '办公耗材', '箱', 198.00),
    ('白板 120*90', '办公用品', '块', 320.00),
    ('投影幕布150寸', '办公设备', '幅', 680.00),
    ('视频会议设备', '通信设备', '套', 8999.00),
    ('考勤机', '办公设备', '台', 899.00),
    ('扫描仪', '办公设备', '台', 1299.00),
    ('复印机', '办公设备', '台', 8999.00),
    ('碎纸机', '办公设备', '台', 459.00),
    ('保险柜', '办公家具', '个', 1280.00),
    ('空调2匹', '办公设备', '台', 3299.00),
    ('饮水机', '办公设备', '台', 599.00),
    ('冰箱', '办公设备', '台', 1599.00),
    ('微波炉', '办公设备', '台', 459.00),
    ('电热水壶', '办公设备', '个', 129.00),
    ('手提电脑包', '办公用品', '个', 189.00),
    ('无线充电器', '办公用品', '个', 89.00),
    ('扩展坞', '办公用品', '个', 299.00),
    ('移动硬盘2TB', '存储设备', '个', 499.00),
    ('U盘64G', '存储设备', '个', 49.00),
    ('数据线套装', '办公用品', '套', 69.00),
]

# 通知标题
notice_titles = [
    '关于开展{}年第一季度工作总结的通知',
    '关于加强信息安全管理规定的通知',
    '关于举办年度优秀员工表彰大会的通知',
    '关于清明节放假安排的通知',
    '关于五一劳动节放假安排的通知',
    '关于端午节放假安排的通知',
    '关于中秋节放假安排的通知',
    '关于国庆节放假安排的通知',
    '关于春节放假安排的通知',
    '关于公司年会延期的通知',
    '关于办公楼装修施工的通知',
    '关于网络维护升级的通知',
    '关于启用新考勤系统的通知',
    '关于报销制度调整的通知',
    '关于差旅费标准变更的通知',
    '关于开展安全教育培训的通知',
    '关于消防演练的通知',
    '关于员工体检的通知',
    '关于团建活动安排的通知',
    '关于下发年度目标的通知',
    '关于绩效考核的通知',
    '关于工资发放时间的通知',
    '关于年终奖发放的通知',
    '关于项目奖金分配的通知',
    '关于职务晋升的通知',
    '关于新员工入职培训的通知',
    '关于岗位职责调整的通知',
    '关于工作时间调整的通知',
    '关于加班调休的通知',
    '关于请假制度变更的通知',
]

# 审批理由
approval_reasons = [
    '父亲身体不适需回家探望',
    '母亲生日回家庆祝',
    '孩子生病需要照顾',
    '房屋装修需到场验收',
    '项目紧急需要加班完成',
    '客户要求紧急交付',
    '系统上线需要通宵',
    '季度报告赶制',
    '客户拜访需要出差至北京',
    '客户拜访需要出差至上海',
    '客户拜访需要出差至广州',
    '参加行业展会',
    '供应商实地考察',
    '设备安装调试',
    '办公室搬迁需要整理',
    '参加培训课程',
    '获取专业证书',
    '参加职业资格考试',
    '办公设备老化需要更换',
    '办公用品库存不足',
    '急需采购原材料',
    '支付供应商货款',
    '员工借款用于应急',
    '业务拓展需要预支',
]

def random_date(start, end):
    delta = end - start
    random_days = random.randint(0, delta.days)
    return start + timedelta(days=random_days)

def random_time(start_hour, end_hour):
    hour = random.randint(start_hour, end_hour)
    minute = random.randint(0, 59)
    second = random.randint(0, 59)
    return f'{hour:02d}:{minute:02d}:{second:02d}'

# 生成日期范围
start_date = datetime(2025, 1, 1)
end_date = datetime(2026, 4, 8)

# 工作日
workdays = []
current = start_date
while current <= end_date:
    if current.weekday() < 5:  # 周一到周五
        workdays.append(current)
    current += timedelta(days=1)

# 生成数据
random.seed(42)

# ============ data.sql ============
data_sql = []
data_sql.append('-- 初始数据\n')

# 部门
data_sql.append('-- 部门')
for i, name in enumerate(departments, 1):
    data_sql.append(f"INSERT INTO department (id, name, parent_id) VALUES ({i}, '{name}', 0);")

# 用户 - 20人
data_sql.append('\n-- 用户')
users = []
for i in range(1, 21):
    if i == 1:
        username = 'admin'
        real_name = '系统管理员'
        email = 'admin@oa.com'
        dept_id = 1
        position = '管理员'
        role = 'ADMIN'
    else:
        fn = random.choice(first_names)
        sn = random.choice(second_names)
        while (fn + sn) in [u[2] for u in users]:
            sn = random.choice(second_names)
        real_name = fn + sn
        username = f'{fn.lower()}{sn.lower()}'
        email = f'{username}@oa.com'
        dept_id = random.randint(1, 15)
        position = random.choice(positions)
        role = 'ADMIN' if i <= 3 else 'USER'
    phone = f'138{random.randint(10000000, 99999999)}'
    users.append((i, username, real_name, email, phone, dept_id, position, role))

for u in users:
    data_sql.append(f"INSERT INTO sys_user (id, username, password, real_name, email, phone, dept_id, position, role, status) VALUES ({u[0]}, '{u[1]}', 'E10ADC3949BA59ABBE56E057F20F883E', '{u[2]}', '{u[3]}', '{u[4]}', {u[5]}, '{u[6]}', '{u[7]}', 1);")

# 审批类型
data_sql.append('\n-- 审批类型')
approval_types = [(1, '请假申请', '员工请假审批'), (2, '报销申请', '费用报销审批'), (3, '加班申请', '加班审批'), (4, '出差申请', '出差审批'), (5, '采购申请', '办公用品/设备采购'), (6, '付款申请', '供应商付款审批'), (7, '借款申请', '员工借款审批')]
for t in approval_types:
    data_sql.append(f"INSERT INTO approval_type (id, name, description) VALUES ({t[0]}, '{t[1]}', '{t[2]}');")

# 通知 200+
data_sql.append('\n-- 通知公告')
notice_id = 1
for _ in range(220):
    title_template = random.choice(notice_titles)
    if '{}' in title_template:
        year = random.choice([2025, 2026])
        title = title_template.format(year)
    else:
        title = title_template
    content = f'各位同事：\n{title}，请相关人员遵照执行。'
    author_id = random.randint(1, 20)
    dt = random_date(start_date, end_date)
    create_time = dt.strftime('%Y-%m-%d %H:%M:%S')
    data_sql.append(f"INSERT INTO notice (id, title, content, author_id, create_time) VALUES ({notice_id}, '{title}', '{content}', {author_id}, '{create_time}');")
    notice_id += 1

# 客户 60+
data_sql.append('\n-- 客户')
for i in range(1, 61):
    city = random.choice(cities)
    company = random.choice(companies)
    name = f'{city}{company}'
    contact = random.choice(first_names) + random.choice(second_names)
    phone = f'138{random.randint(10000000, 99999999)}'
    address = f'{city}市{random.choice(["朝阳", "海淀", "浦东", "天河", "南山", "西湖"])}区{random.choice(["建国路", "中关村", "科技园", "工业园", "商业中心"])}{random.randint(1, 999)}号'
    status = 1
    dt = random_date(start_date, end_date)
    create_time = dt.strftime('%Y-%m-%d %H:%M:%S')
    data_sql.append(f"INSERT INTO customer (id, name, contact, phone, address, status, create_time) VALUES ({i}, '{name}', '{contact}', '{phone}', '{address}', {status}, '{create_time}');")

# 产品 80+
data_sql.append('\n-- 产品')
product_id = 1
# 已有产品
for p in products:
    data_sql.append(f"INSERT INTO product (id, name, category, unit, price, status, create_time) VALUES ({product_id}, '{p[0]}', '{p[1]}', '{p[2]}', {p[3]:.2f}, 1, '2025-01-10 09:00:00');")
    product_id += 1
# 再加40个
extra_products = [
    ('戴尔服务器R740', '办公设备', '台', 25999.00),
    ('思科交换机', '网络设备', '台', 8999.00),
    ('oracle数据库 license', '软件', '套', 50000.00),
    ('Windows Server 2022', '软件', '套', 8999.00),
    ('office 365 订阅', '软件', '套', 1999.00),
    ('会议室音响系统', '办公设备', '套', 12999.00),
    ('高清摄像头', '电子设备', '个', 899.00),
    ('无线麦克风', '电子设备', '套', 699.00),
    ('交换机6口', '网络设备', '台', 299.00),
    ('网线CAT6 100米', '网络设备', '箱', 399.00),
    ('光纤收发器', '网络设备', '对', 259.00),
    ('工具箱', '办公用品', '个', 189.00),
    ('电钻', '办公用品', '个', 299.00),
    ('梯子', '办公用品', '个', 259.00),
    ('急救箱', '办公用品', '个', 159.00),
    ('灭火器', '办公用品', '个', 199.00),
    ('停车管理系统', '办公设备', '套', 15999.00),
    ('门禁系统', '办公设备', '套', 8999.00),
    ('监控存储服务器', '存储设备', '台', 12999.00),
    ('企业路由器', '网络设备', '台', 4599.00),
    ('打印机硒鼓套装', '办公耗材', '套', 589.00),
    ('墨盒', '办公耗材', '个', 189.00),
    ('打印纸A4', '办公耗材', '箱', 45.00),
    ('档案盒', '办公用品', '个', 12.00),
    ('文件夹', '办公用品', '个', 8.00),
    ('回形针盒', '办公用品', '个', 6.00),
    ('便签纸', '办公用品', '本', 5.00),
    ('计算器', '办公用品', '个', 35.00),
    ('时钟', '办公用品', '个', 89.00),
    ('垃圾桶', '办公用品', '个', 45.00),
    ('擦鞋器', '办公用品', '个', 29.00),
    ('雨伞架', '办公家具', '个', 159.00),
    ('报架', '办公家具', '个', 99.00),
    ('白板笔', '办公用品', '盒', 25.00),
    ('胶带', '办公用品', '卷', 5.00),
    ('剪刀', '办公用品', '把', 15.00),
    ('直尺', '办公用品', '把', 8.00),
    ('鼠标垫', '办公用品', '个', 25.00),
    ('键盘清洁泥', '办公用品', '个', 15.00),
    ('屏幕清洁剂', '办公用品', '瓶', 25.00),
]
for p in extra_products:
    data_sql.append(f"INSERT INTO product (id, name, category, unit, price, status, create_time) VALUES ({product_id}, '{p[0]}', '{p[1]}', '{p[2]}', {p[3]:.2f}, 1, '2025-01-10 09:00:00');")
    product_id += 1

# 审批 350+
data_sql.append('\n-- 审批')
approval_id = 1
for _ in range(380):
    type_id = random.randint(1, 7)
    title = approval_types[type_id-1][1]
    reason = random.choice(approval_reasons)
    amount = round(random.uniform(100, 50000), 2) if type_id in [2, 5, 6, 7] else 0
    applicant_id = random.randint(2, 20)  # 不包括admin
    assignee_id = random.randint(2, 5)
    status = random.choice([0, 0, 0, 1, 1, 1, 1, 2])  # 偏向已通过
    dt = random_date(start_date, end_date)
    create_time = dt.strftime('%Y-%m-%d %H:%M:%S')
    data_sql.append(f"INSERT INTO approval (id, type_id, title, reason, amount, applicant_id, assignee_id, status, create_time) VALUES ({approval_id}, {type_id}, '{title}', '{reason}', {amount}, {applicant_id}, {assignee_id}, {status}, '{create_time}');")
    approval_id += 1

# 日程 350+
data_sql.append('\n-- 日程')
schedule_id = 1
schedule_titles = [
    '周例会', '月度销售总结会议', '项目进度评审会', '新员工入职培训', '消防安全演练',
    '客户答谢会', '技术分享交流会', '产品发布会', '供应商洽谈会', '年度预算会议',
    '绩效考核会议', '团建活动', '生日会', '培训课程', '方案评审会',
    '市场分析会议', '战略规划会议', '运营分析会', '质量分析会', '安全例会'
]
locations = ['会议室A', '会议室B', '多功能厅', '培训室1', '培训室2', '大会议室', '小会议室', '展厅']
for _ in range(380):
    title = random.choice(schedule_titles)
    content = f'请相关人员准时参加{title}'
    user_id = random.randint(1, 20)
    dt = random_date(start_date, end_date)
    start_hour = random.choice([9, 10, 14, 15, 16])
    start_time = f'{dt.strftime("%Y-%m-%d")} {start_hour:02d}:00:00'
    end_hour = start_hour + random.choice([1, 2])
    end_time = f'{dt.strftime("%Y-%m-%d")} {end_hour:02d}:00:00'
    location = random.choice(locations)
    data_sql.append(f"INSERT INTO schedule (id, user_id, title, content, start_time, end_time, location, create_time) VALUES ({schedule_id}, {user_id}, '{title}', '{content}', '{start_time}', '{end_time}', '{location}', '{start_time}');")
    schedule_id += 1

# 考勤 5000+ - 每个用户300+条
data_sql.append('\n-- 考勤记录')
attendance_id = 1
attendance_status = {1: '正常', 2: '迟到', 3: '早退', 4: '请假'}
for user_id in range(1, 21):
    # 每个用户约300-320条记录
    num_records = random.randint(300, 320)
    selected_workdays = random.sample(workdays, num_records)
    for wd in selected_workdays:
        # 签到时间 08:30-09:30 为主
        if random.random() < 0.88:  # 88%正常
            check_in_hour = random.choices([8, 9], weights=[0.3, 0.7])[0]
            if check_in_hour == 8:
                check_in_min = random.randint(30, 59)
            else:
                check_in_min = random.randint(0, 30)
            status = 1
        elif random.random() < 0.6:  # 迟到
            check_in_hour = 9
            check_in_min = random.randint(1, 30)
            status = 2
        else:  # 请假或早退
            check_in_hour = random.choice([8, 9])
            check_in_min = random.randint(0, 59)
            status = random.choice([3, 4])

        check_in = f'{check_in_hour:02d}:{check_in_min:02d}:{random.randint(0, 59):02d}'

        # 签退
        if status == 3:  # 早退
            check_out_hour = random.randint(16, 17)
            check_out_min = random.randint(0, 30)
        elif status == 4:  # 请假
            check_out_hour = random.randint(12, 14)
            check_out_min = random.randint(0, 59)
        else:  # 正常
            if random.random() < 0.85:
                check_out_hour = random.choices([18, 19], weights=[0.7, 0.3])[0]
                check_out_min = random.randint(0, 59)
            else:  # 加班
                check_out_hour = random.randint(19, 21)
                check_out_min = random.randint(0, 59)

        check_out = f'{check_out_hour:02d}:{check_out_min:02d}:{random.randint(0, 59):02d}'
        date_str = wd.strftime('%Y-%m-%d')
        remark = '' if status == 1 else attendance_status[status]
        data_sql.append(f"INSERT INTO attendance (id, user_id, date, check_in_time, check_out_time, status, remark) VALUES ({attendance_id}, {user_id}, '{date_str}', '{check_in}', '{check_out}', {status}, '{remark}');")
        attendance_id += 1

# 写data.sql
with open('/Users/zq/workspace/study/oa/src/main/resources/data.sql', 'w', encoding='utf-8') as f:
    f.write('\n'.join(data_sql))

print(f'data.sql generated: {len(data_sql)} lines')
print(f'Approximate records:')
print(f'  - Users: 20')
print(f'  - Departments: 15')
print(f'  - Notices: 220')
print(f'  - Customers: 60')
print(f'  - Products: {product_id-1}')
print(f'  - Approvals: 380')
print(f'  - Schedules: 380')
print(f'  - Attendances: {attendance_id-1}')

# ============ schema.sql - 只更新voucher部分 ============
with open('/Users/zq/workspace/study/oa/src/main/resources/schema.sql', 'r', encoding='utf-8') as f:
    schema_content = f.read()

# 找到voucher数据开始和结束的位置
voucher_start = schema_content.find('-- 凭证主表')
if voucher_start == -1:
    voucher_start = schema_content.find('INSERT INTO voucher')

# 找到voucher_item数据
voucher_item_start = schema_content.find('INSERT INTO voucher_item')

if voucher_start > 0 and voucher_item_start > 0:
    # 保留schema的DDL部分，只替换数据
    ddl_part = schema_content[:voucher_start]
    end_part = schema_content[voucher_item_start:]

    # 生成凭证数据
    voucher_sql = ['-- 凭证主表']
    voucher_no = 1

    # 凭证模板
    voucher_templates = [
        ('收到{}货款', '1', '2'),
        ('支付{}材料款', '2', '1'),
        ('发放员工工资', '2', '1'),
        ('缴纳增值税', '2', '1'),
        ('收到{}预付款', '1', '2'),
        ('购买办公设备', '2', '1'),
        ('支付水电费', '2', '1'),
        ('收到咨询服务费', '1', '2'),
        ('支付运费', '2', '1'),
        ('报销差旅费', '2', '1'),
        ('收到保险赔款', '1', '2'),
        ('支付会议费', '2', '1'),
        ('采购固定资产', '2', '1'),
        ('利息收入', '1', '2'),
        ('罚款收入', '1', '2'),
    ]

    customers_short = ['北京', '上海', '广州', '深圳', '杭州', '成都', '武汉', '南京', '西安', '苏州']

    for day in range(1, 460):  # 约15个月
        # 生成日期
        date = start_date + timedelta(days=day)
        if date > end_date:
            break
        # 每天1-3张凭证
        num_vouchers = random.randint(1, 3)
        for v in range(num_vouchers):
            vid = voucher_no
            voucher_no_str = f'V{date.strftime("%Y%m%d")}{v+1:03d}'
            # 随机模板
            tpl_item = random.choice(voucher_templates)
            tpl = tpl_item[0]
            if '{}' in tpl:
                tpl = tpl.format(random.choice(customers_short))
            amount = round(random.uniform(1000, 500000), 2)
            # 借方和贷方都是同一个金额
            amount_debit = amount
            amount_credit = amount
            maker_id = random.randint(1, 20)  # 随机制单人
            checker_id = random.choice([1, 2, 3, 4, 5, None]) if random.random() > 0.3 else None
            poster_id = random.choice([1, 2, 3, 4, 5, None]) if random.random() > 0.3 and checker_id else None

            # 状态：老凭证更可能已过账
            if date < datetime(2025, 10, 1):
                audit_status = random.choice([1, 1, 2])
                posting_status = 1 if audit_status == 1 else 0
            elif date < datetime(2026, 1, 1):
                audit_status = random.choice([0, 1, 1, 2])
                posting_status = 1 if audit_status == 1 else 0
            else:
                audit_status = random.choice([0, 0, 1, 2])
                posting_status = 0

            audit_status_str = 'NULL' if checker_id is None else '1'
            posting_status_str = 'NULL' if poster_id is None else '1'

            voucher_sql.append(f"INSERT INTO voucher (id, voucher_no, voucher_date, attachment_count, audit_status, posting_status, maker_id, checker_id, poster_id, total_amount, remark) VALUES ({vid}, '{voucher_no_str}', '{date.strftime('%Y-%m-%d')}', {random.randint(0, 5)}, {audit_status}, {posting_status}, {maker_id}, {checker_id if checker_id else 'NULL'}, {poster_id if poster_id else 'NULL'}, {amount:.2f}, '{tpl}');")
            voucher_no += 1

    voucher_sql.append('\n-- 凭证明细表')
    voucher_sql.append('-- 每个凭证2条明细（借贷平衡）')

    # 生成凭证明细
    subject_ids = list(range(1, 23))  # 1-22
    for vid in range(1, voucher_no):
        amount = round(random.uniform(1000, 500000), 2)
        subject1 = random.choice(subject_ids)
        subject2 = random.choice(subject_ids)
        voucher_sql.append(f"INSERT INTO voucher_item (voucher_id, subject_id, direction, amount, summary) VALUES ({vid}, {subject1}, '1', {amount:.2f}, '借方');")
        voucher_sql.append(f"INSERT INTO voucher_item (voucher_id, subject_id, direction, amount, summary) VALUES ({vid}, {subject2}, '2', {amount:.2f}, '贷方');")

    new_schema = ddl_part + '\n'.join(voucher_sql) + '\n\n' + end_part

    with open('/Users/zq/workspace/study/oa/src/main/resources/schema.sql', 'w', encoding='utf-8') as f:
        f.write(new_schema)

    print(f'\nschema.sql updated: ~{voucher_no-1} vouchers, ~{(voucher_no-1)*2} voucher_items')
