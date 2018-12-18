/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : damo_security

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-03-08 17:36:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_national_code
-- ----------------------------
DROP TABLE IF EXISTS `tb_national_code`;
CREATE TABLE `tb_national_code` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `national_name` varchar(50) DEFAULT NULL COMMENT '国际名称',
  `chinese_name` varchar(50) DEFAULT NULL COMMENT '中文名称',
  `abbre` varchar(10) DEFAULT NULL COMMENT '国际简称',
  `code` varchar(20) DEFAULT NULL COMMENT '国际编码',
  `land` varchar(255) DEFAULT NULL COMMENT '洲际',
  `price` decimal(10,4) DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=239 DEFAULT CHARSET=utf8mb4 COMMENT='国际区号信息';

-- ----------------------------
-- Records of tb_national_code
-- ----------------------------
INSERT INTO `tb_national_code` VALUES ('1', 'Afghanistan', '阿富汗', 'AF', '93', '亚洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('2', 'Albania', '阿尔巴尼亚', 'AL', '355', '欧洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('3', 'Algeria', '阿尔及利亚', 'DZ', '213', '非洲', '0.9830');
INSERT INTO `tb_national_code` VALUES ('4', 'American Samoa', '萨摩亚', 'AS', '684', '大洋洲', '0.7900');
INSERT INTO `tb_national_code` VALUES ('5', 'Andorra', '安道尔共和国', 'AD', '376', '欧洲', '0.2020');
INSERT INTO `tb_national_code` VALUES ('6', 'Angola', '安哥拉', 'AO', '244', '非洲', '0.5660');
INSERT INTO `tb_national_code` VALUES ('7', 'Anguilla', '安圭拉岛', 'AI', '1264', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('8', 'Antarctica', '南极洲', 'AQ', '672', '南极洲', null);
INSERT INTO `tb_national_code` VALUES ('9', 'Antigua and Barbuda', '安提瓜和巴布达', 'AG', '1268', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('10', 'Argentina', '阿根廷', 'AR', '54', '美洲', '0.2770');
INSERT INTO `tb_national_code` VALUES ('11', 'Armenia', '亚美尼亚', 'AM', '374', '亚洲', '0.4210');
INSERT INTO `tb_national_code` VALUES ('12', 'Aruba', '阿鲁巴', 'AW', '297', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('13', 'Australia', '澳大利亚', 'AU', '61', '大洋洲', '0.5180');
INSERT INTO `tb_national_code` VALUES ('14', 'Austria', '奥地利', 'AT', '43', '欧洲', '0.9190');
INSERT INTO `tb_national_code` VALUES ('15', 'Azerbaijan', '阿塞拜疆', 'AZ', '994', '亚洲', '1.1840');
INSERT INTO `tb_national_code` VALUES ('16', 'Bahamas', '巴哈马', 'BS', '1242', '美洲', '0.2610');
INSERT INTO `tb_national_code` VALUES ('17', 'Bahrain', '巴林', 'BH', '973', '亚洲', '0.3090');
INSERT INTO `tb_national_code` VALUES ('18', 'Bangladesh', '孟加拉国', 'BD', '880', '亚洲', '0.7420');
INSERT INTO `tb_national_code` VALUES ('19', 'Barbados', '巴巴多斯', 'BB', '1246', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('20', 'Belarus', '白俄罗斯', 'BY', '375', '欧洲', '0.1640');
INSERT INTO `tb_national_code` VALUES ('21', 'Belgium', '比利时', 'BE', '32', '欧洲', '0.5820');
INSERT INTO `tb_national_code` VALUES ('22', 'Belize', '伯利兹城', 'BZ', '501', '美洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('23', 'Benin', '贝宁', 'BJ', '229', '非洲', '0.6620');
INSERT INTO `tb_national_code` VALUES ('24', 'Bermuda', '百慕大', 'BM', '1441', '美洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('25', 'Bhutan', '不丹', 'BT', '975', '亚洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('26', 'Bolivia', '玻利维亚', 'BO', '591', '美洲', '0.8550');
INSERT INTO `tb_national_code` VALUES ('27', 'Bosnia and Herzegovina', '波斯尼亚和黑塞哥维那', 'BA', '387', '欧洲', '0.2610');
INSERT INTO `tb_national_code` VALUES ('28', 'Botswana', '博茨瓦纳', 'BW', '267', '非洲', '0.8220');
INSERT INTO `tb_national_code` VALUES ('29', 'Brazil', '巴西', 'BR', '55', '美洲', '0.2450');
INSERT INTO `tb_national_code` VALUES ('30', 'British Indian Ocean Territory', '英属印度洋领地', 'IO', '246', '亚洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('31', 'Brunei Darussalam', '文莱达鲁萨兰国', 'BN', '673', '亚洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('32', 'Bulgaria', '保加利亚', 'BG', '359', '欧洲', '0.9190');
INSERT INTO `tb_national_code` VALUES ('33', 'Burkina Faso', '布基纳法索', 'BF', '226', '非洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('34', 'Burundi', '布隆迪', 'BI', '257', '非洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('35', 'Cambodia', '柬埔寨', 'KH', '855', '亚洲', '0.3940');
INSERT INTO `tb_national_code` VALUES ('36', 'Cameroon', '喀麦隆', 'CM', '237', '非洲', '0.6620');
INSERT INTO `tb_national_code` VALUES ('37', 'Canada', '加拿大', 'CA', '1', '美洲', '0.2130');
INSERT INTO `tb_national_code` VALUES ('38', 'Cape Verde', '佛得角', 'CV', '238', '非洲', '0.9830');
INSERT INTO `tb_national_code` VALUES ('39', 'Cayman Islands', '开曼群岛', 'KY', '1345', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('40', 'Central African Republic', '中非共和国', 'CF', '236', '非洲', '0.4050');
INSERT INTO `tb_national_code` VALUES ('41', 'Chad', '乍得', 'TD', '235', '非洲', '0.4210');
INSERT INTO `tb_national_code` VALUES ('42', 'Chile', '智利', 'CL', '56', '美洲', '0.5500');
INSERT INTO `tb_national_code` VALUES ('43', 'China', '中国', 'CN', '86', '亚洲', '0.0700');
INSERT INTO `tb_national_code` VALUES ('44', 'Christmas Island', '圣延岛', 'CX', '61', '亚洲', '0.5180');
INSERT INTO `tb_national_code` VALUES ('45', 'Cocos (Keeling) Islands', '科科斯群岛', 'CC', '61', '大洋洲', '0.5180');
INSERT INTO `tb_national_code` VALUES ('46', 'Colombia', '哥伦比亚', 'CO', '57', '美洲', '0.2900');
INSERT INTO `tb_national_code` VALUES ('47', 'Comoros', '科摩罗', 'KM', '269', '非洲', '0.5980');
INSERT INTO `tb_national_code` VALUES ('48', 'Congo', '刚果', 'CG', '242', '非洲', '0.7110');
INSERT INTO `tb_national_code` VALUES ('49', 'Congo, The Democratic Republic Of The', '刚果民主共和国', 'ZR', '243', '非洲', '1.0940');
INSERT INTO `tb_national_code` VALUES ('50', 'Cook Islands', '库克群岛', 'CK', '682', '大洋洲', '0.4050');
INSERT INTO `tb_national_code` VALUES ('51', 'Costa Rica', '哥斯达黎加', 'CR', '506', '美洲', '0.4530');
INSERT INTO `tb_national_code` VALUES ('52', 'Cote D\'Ivoire', 'Cote D\'Ivoire', 'CI', '225', '非洲', '0.6620');
INSERT INTO `tb_national_code` VALUES ('53', 'Croatia (local name: Hrvatska)', '克罗地亚', 'HR', '385', '欧洲', '0.6620');
INSERT INTO `tb_national_code` VALUES ('54', 'Cuba', '古巴', 'CU', '53', '美洲', '0.2450');
INSERT INTO `tb_national_code` VALUES ('55', 'Cyprus', '塞浦路斯', 'CY', '357', '亚洲', '0.7420');
INSERT INTO `tb_national_code` VALUES ('56', 'Czech Republic', '捷克', 'CZ', '420', '欧洲', '0.6140');
INSERT INTO `tb_national_code` VALUES ('57', 'Denmark', '丹麦', 'DK', '45', '欧洲', '0.2770');
INSERT INTO `tb_national_code` VALUES ('58', 'Djibouti', '吉布提', 'DJ', '253', '非洲', '1.0790');
INSERT INTO `tb_national_code` VALUES ('59', 'Dominica', '多米尼克国', 'DM', '1767', '美洲', '0.4800');
INSERT INTO `tb_national_code` VALUES ('60', 'Dominican Republic', '多米尼加共和国', 'DO', '1849', '美洲', null);
INSERT INTO `tb_national_code` VALUES ('61', 'East Timor', '东帝汶', 'TP', '670', '亚洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('62', 'Ecuador', '厄瓜多尔', 'EC', '593', '美洲', '1.3040');
INSERT INTO `tb_national_code` VALUES ('63', 'Egypt', '埃及', 'EG', '20', '非洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('64', 'El Salvador', '萨尔瓦多', 'SV', '503', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('65', 'Equatorial Guinea', '赤道几内亚', 'GQ', '240', '非洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('66', 'Eritrea', '厄立特里亚国', 'ER', '291', '非洲', '3.2300');
INSERT INTO `tb_national_code` VALUES ('67', 'Estonia', '爱沙尼亚', 'EE', '372', '欧洲', '0.6620');
INSERT INTO `tb_national_code` VALUES ('68', 'Ethiopia', '埃塞俄比亚', 'ET', '251', '非洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('69', 'Falkland Islands (Malvinas)', '福克兰群岛', 'FK', '5', '美洲', '0.2610');
INSERT INTO `tb_national_code` VALUES ('70', 'Faroe Islands', '法罗群岛', 'FO', '298', '欧洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('71', 'Fiji', '斐济', 'FJ', '679', '大洋洲', '0.5820');
INSERT INTO `tb_national_code` VALUES ('72', 'Finland', '芬兰', 'FI', '358', '欧洲', '0.6780');
INSERT INTO `tb_national_code` VALUES ('73', 'France', '法国', 'FR', '33', '欧洲', '0.7900');
INSERT INTO `tb_national_code` VALUES ('74', 'France Metropolitan', '法国大都会', 'FX', '33', '欧洲', '0.7900');
INSERT INTO `tb_national_code` VALUES ('75', 'French Guiana', '法属圭亚那', 'GF', '594', '美洲', '1.3040');
INSERT INTO `tb_national_code` VALUES ('76', 'French Polynesia', '法属玻里尼西亚', 'PF', '689', '大洋洲', '0.8170');
INSERT INTO `tb_national_code` VALUES ('77', 'Gabon', '加蓬', 'GA', '241', '非洲', '0.3570');
INSERT INTO `tb_national_code` VALUES ('78', 'Gambia', ' 冈比亚', 'GM', '220', '非洲', '0.4050');
INSERT INTO `tb_national_code` VALUES ('79', 'Georgia', '格鲁吉亚', 'GE', '995', '亚洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('80', 'Germany', '德国', 'DE', '49', '欧洲', '0.5180');
INSERT INTO `tb_national_code` VALUES ('81', 'Ghana', '加纳', 'GH', '233', '非洲', '0.5820');
INSERT INTO `tb_national_code` VALUES ('82', 'Gibraltar', '直布罗陀', 'GI', '350', '欧洲', '0.3250');
INSERT INTO `tb_national_code` VALUES ('83', 'Greece', '希腊', 'GR', '30', '非洲', '0.6300');
INSERT INTO `tb_national_code` VALUES ('84', 'Greenland', '格陵兰', 'GL', '45', '美洲', '0.2770');
INSERT INTO `tb_national_code` VALUES ('85', 'Grenada', '格林纳达', 'GD', '1473', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('86', 'Guadeloupe', '瓜德罗普岛', 'GP', '590', '美洲', '1.3040');
INSERT INTO `tb_national_code` VALUES ('87', 'Guam', '关岛', 'GU', '1671', '大洋洲', '1.3040');
INSERT INTO `tb_national_code` VALUES ('88', 'Guatemala', '危地马拉', 'GT', '502', '美洲', '0.6620');
INSERT INTO `tb_national_code` VALUES ('89', 'Guinea', '几内亚', 'GN', '224', '大洋洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('90', 'Guinea-Bissau', '几内亚比绍', 'GW', '245', '非洲', '0.6620');
INSERT INTO `tb_national_code` VALUES ('91', 'Guyana', '圭亚那', 'GY', '592', '美洲', '0.5340');
INSERT INTO `tb_national_code` VALUES ('92', 'Haiti', '海地', 'HT', '509', '美洲', '0.8220');
INSERT INTO `tb_national_code` VALUES ('93', 'Honduras', '洪都拉斯', 'HN', '504', '美洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('94', 'Hong Kong', '香港', 'HK', '852', '亚洲', '0.4200');
INSERT INTO `tb_national_code` VALUES ('95', 'Hungary', '匈牙利', 'HU', '36', '美洲', '0.7260');
INSERT INTO `tb_national_code` VALUES ('96', 'Iceland', '冰岛', 'IS', '354', '欧洲', '0.4210');
INSERT INTO `tb_national_code` VALUES ('97', 'India', '印度', 'IN', '91', '亚洲', '0.1810');
INSERT INTO `tb_national_code` VALUES ('98', 'Indonesia', '印度尼西亚', 'ID', '62', '亚洲', '0.3730');
INSERT INTO `tb_national_code` VALUES ('99', 'Iran (Islamic Republic of)', '伊朗（伊斯兰共和国）', 'IR', '98', '亚洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('100', 'Iraq', '伊拉克', 'IQ', '964', '亚洲', '0.8220');
INSERT INTO `tb_national_code` VALUES ('101', 'Ireland', '爱尔兰', 'IE', '353', '欧洲', '0.6300');
INSERT INTO `tb_national_code` VALUES ('102', 'Israel', '以色列', 'IL', '972', '亚洲', '0.3090');
INSERT INTO `tb_national_code` VALUES ('103', 'Italy', '意大利', 'IT', '39', '欧洲', '0.6300');
INSERT INTO `tb_national_code` VALUES ('104', 'Jamaica', '牙买加', 'JM', '1876', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('105', 'Japan', '日本', 'JP', '81', '亚洲', '0.5970');
INSERT INTO `tb_national_code` VALUES ('106', 'Jordan', '约旦', 'JO', '962', '亚洲', '0.2610');
INSERT INTO `tb_national_code` VALUES ('107', 'Kazakhstan', '哈萨克', 'KZ', '7', '亚洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('108', 'Kenya', '肯尼亚', 'KE', '254', '非洲', '0.2130');
INSERT INTO `tb_national_code` VALUES ('109', 'Kuwait', '科威特', 'KW', '965', '亚洲', '0.6300');
INSERT INTO `tb_national_code` VALUES ('110', 'Kyrgyzstan', '吉尔吉斯', 'KG', '996', '亚洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('111', 'Latvia', '拉脱维亚', 'LV', '371', '欧洲', '0.5180');
INSERT INTO `tb_national_code` VALUES ('112', 'Lebanon', '黎巴嫩', 'LB', '961', '亚洲', '0.4050');
INSERT INTO `tb_national_code` VALUES ('113', 'Lesotho', '莱索托', 'LS', '266', '非洲', '0.4050');
INSERT INTO `tb_national_code` VALUES ('114', 'Liberia', '利比里亚', 'LR', '231', '非洲', '0.9830');
INSERT INTO `tb_national_code` VALUES ('115', 'Libyan Arab Jamahiriya', '利比亚', 'LY', '218', '非洲', '0.5340');
INSERT INTO `tb_national_code` VALUES ('116', 'Liechtenstein', '列支敦士登', 'LI', '423', '欧洲', '0.4210');
INSERT INTO `tb_national_code` VALUES ('117', 'Lithuania', '立陶宛', 'LT', '370', '欧洲', '0.1970');
INSERT INTO `tb_national_code` VALUES ('118', 'Luxembourg', '卢森堡', 'LU', '352', '欧洲', '0.4050');
INSERT INTO `tb_national_code` VALUES ('119', 'Macau', '澳门地区', 'MO', '853', '亚洲', '0.5820');
INSERT INTO `tb_national_code` VALUES ('120', 'Madagascar', '马达加斯加', 'MG', '261', '非洲', '0.4210');
INSERT INTO `tb_national_code` VALUES ('121', 'Malawi', '马拉维', 'MW', '265', '非洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('122', 'Malaysia', '马来西亚', 'MY', '60', '亚洲', '0.5820');
INSERT INTO `tb_national_code` VALUES ('123', 'Maldives', '马尔代夫', 'MV', '960', '亚洲', '0.1820');
INSERT INTO `tb_national_code` VALUES ('124', 'Mali', '马里', 'ML', '223', '非洲', '0.3570');
INSERT INTO `tb_national_code` VALUES ('125', 'Malta', '马尔他', 'MT', '356', '欧洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('126', 'Marshall Islands', '马绍尔群岛', 'MH', '692', '大洋洲', '3.2300');
INSERT INTO `tb_national_code` VALUES ('127', 'Martinique', '马提尼克岛', 'MQ', '596', '美洲', '1.3040');
INSERT INTO `tb_national_code` VALUES ('128', 'Mauritania', '毛里塔尼亚', 'MR', '222', '非洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('129', 'Mauritius', '毛里求斯', 'MU', '230', '非洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('130', 'Mayotte', '马约特', 'YT', '262', '非洲', '0.5570');
INSERT INTO `tb_national_code` VALUES ('131', 'Mexico', '墨西哥', 'MX', '52', '美洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('132', 'Micronesia', '密克罗尼西亚', 'FM', '691', '大洋洲', '0.6620');
INSERT INTO `tb_national_code` VALUES ('133', 'Moldova', '摩尔多瓦', 'MD', '373', '欧洲', '0.9030');
INSERT INTO `tb_national_code` VALUES ('134', 'Monaco', '摩纳哥', 'MC', '377', '非洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('135', 'Mongolia', '外蒙古', 'MN', '976', '亚洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('136', 'Montenegro', '黑山共和国', 'MNE', '382', '欧洲', '0.2450');
INSERT INTO `tb_national_code` VALUES ('137', 'Montserrat', '蒙特塞拉特', 'MS', '1664', '美洲', '0.2610');
INSERT INTO `tb_national_code` VALUES ('138', 'Morocco', '摩洛哥', 'MA', '212', '非洲', '0.5820');
INSERT INTO `tb_national_code` VALUES ('139', 'Mozambique', '莫桑比克', 'MZ', '258', '非洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('140', 'Myanmar', '缅甸', 'MM', '95', '亚洲', '0.9830');
INSERT INTO `tb_national_code` VALUES ('141', 'Namibia', '那米比亚', 'NA', '264', '非洲', '0.5500');
INSERT INTO `tb_national_code` VALUES ('142', 'Nauru', '瑙鲁', 'NR', '674', '大洋洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('143', 'Nepal', '尼泊尔', 'NP', '977', '亚洲', '0.9030');
INSERT INTO `tb_national_code` VALUES ('144', 'Netherlands', '荷兰', 'NL', '31', '欧洲', '0.8220');
INSERT INTO `tb_national_code` VALUES ('145', 'Netherlands Antilles', '荷兰安的列斯群岛', 'AN', '599', '美洲', '0.4800');
INSERT INTO `tb_national_code` VALUES ('146', 'New Caledonia', '新喀里多尼亚', 'NC', '687', '大洋洲', '0.8710');
INSERT INTO `tb_national_code` VALUES ('147', 'New Zealand', '新西兰', 'NZ', '64', '大洋洲', '0.9830');
INSERT INTO `tb_national_code` VALUES ('148', 'Nicaragua', '尼加拉瓜', 'NI', '505', '美洲', '0.4210');
INSERT INTO `tb_national_code` VALUES ('149', 'Niger', '尼日尔', 'NE', '227', '非洲', '1.2240');
INSERT INTO `tb_national_code` VALUES ('150', 'Nigeria', '尼日利亚', 'NG', '234', '非洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('151', 'Norfolk Island', '诺福克岛', 'NF', '6723', '大洋洲', '0.7420');
INSERT INTO `tb_national_code` VALUES ('152', 'North Korea', '朝鲜', 'KP', '850', '亚洲', '0.5340');
INSERT INTO `tb_national_code` VALUES ('153', 'Northern Mariana Islands', '北马里亚纳群岛', 'MP', '1670', '大洋洲', '0.5110');
INSERT INTO `tb_national_code` VALUES ('154', 'Norway', '挪威', 'NO', '47', '欧洲', '0.5340');
INSERT INTO `tb_national_code` VALUES ('155', 'Oman', '阿曼', 'OM', '968', '亚洲', '0.3570');
INSERT INTO `tb_national_code` VALUES ('156', 'Pakistan', '巴基斯坦', 'PK', '92', '亚洲', '0.2450');
INSERT INTO `tb_national_code` VALUES ('157', 'Palau', '帛琉', 'PW', '680', '大洋洲', '0.2610');
INSERT INTO `tb_national_code` VALUES ('158', 'Palestine', '巴勒斯坦', 'PS', '970', '亚洲', '0.4210');
INSERT INTO `tb_national_code` VALUES ('159', 'Panama', '巴拿马', 'PA', '507', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('160', 'Papua New Guinea', '巴布亚新几内亚', 'PG', '675', '大洋洲', '0.4210');
INSERT INTO `tb_national_code` VALUES ('161', 'Paraguay', '巴拉圭', 'PY', '595', '美洲', '0.3090');
INSERT INTO `tb_national_code` VALUES ('162', 'Peru', '秘鲁', 'PE', '51', '美洲', '0.3730');
INSERT INTO `tb_national_code` VALUES ('163', 'Philippines', '菲律宾共和国', 'PH', '63', '亚洲', '0.2610');
INSERT INTO `tb_national_code` VALUES ('164', 'Pitcairn', '皮特凯恩岛', 'PN', '64', '大洋洲', '0.9830');
INSERT INTO `tb_national_code` VALUES ('165', 'Poland', '波兰', 'PL', '48', '欧洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('166', 'Portugal', '葡萄牙', 'PT', '351', '欧洲', '0.4050');
INSERT INTO `tb_national_code` VALUES ('167', 'Puerto Rico', '波多黎各', 'PR', '1787', '美洲', '0.3890');
INSERT INTO `tb_national_code` VALUES ('168', 'Qatar', '卡塔尔', 'QA', '974', '亚洲', '0.4370');
INSERT INTO `tb_national_code` VALUES ('169', 'Reunion', '留尼汪岛', 'RE', '262', '非洲', '0.5570');
INSERT INTO `tb_national_code` VALUES ('170', 'Romania', '罗马尼亚', 'RO', '40', '欧洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('171', 'Russian Federation', '俄罗斯联邦', 'RU', '7', '欧洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('172', 'Rwanda', '卢旺达', 'RW', '250', '非洲', '0.3730');
INSERT INTO `tb_national_code` VALUES ('173', 'Samoa', '美属萨摩亚', 'WS', '685', '大洋洲', '0.6620');
INSERT INTO `tb_national_code` VALUES ('174', 'San Marino', '圣马力诺共和国', 'SM', '378', '欧洲', '0.8220');
INSERT INTO `tb_national_code` VALUES ('175', 'Saudi Arabia', '沙特阿拉伯', 'SA', '966', '亚洲', '0.3570');
INSERT INTO `tb_national_code` VALUES ('176', 'Senegal', '塞内加尔', 'SN', '221', '非洲', '0.7420');
INSERT INTO `tb_national_code` VALUES ('177', 'Serbia', '塞尔维亚共和国', 'SRB', '381', '欧洲', '0.2650');
INSERT INTO `tb_national_code` VALUES ('178', 'Seychelles', '塞舌尔', 'SC', '248', '非洲', '0.3890');
INSERT INTO `tb_national_code` VALUES ('179', 'Sierra Leone', '塞拉利昂', 'SL', '232', '非洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('180', 'Singapore', '新加坡', 'SG', '65', '亚洲', '0.4340');
INSERT INTO `tb_national_code` VALUES ('181', 'Slovakia (Slovak Republic)', '斯洛伐克（斯洛伐克人的共和国）', 'SK', '421', '欧洲', '0.5180');
INSERT INTO `tb_national_code` VALUES ('182', 'Slovenia', '斯洛文尼亚', 'SI', '386', '欧洲', '0.2770');
INSERT INTO `tb_national_code` VALUES ('183', 'Solomon Islands', '索罗门群岛', 'SB', '677', '大洋洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('184', 'Somalia', '索马里', 'SO', '252', '非洲', '0.9190');
INSERT INTO `tb_national_code` VALUES ('185', 'South Africa', '南非', 'ZA', '27', '非洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('186', 'South Korea', '韩国', 'KR', '82', '亚洲', '0.5340');
INSERT INTO `tb_national_code` VALUES ('187', 'Spain', '西班牙', 'ES', '34', '欧洲', '0.9830');
INSERT INTO `tb_national_code` VALUES ('188', 'Sri Lanka', '斯里兰卡', 'LK', '94', '亚洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('189', 'Sudan', '苏丹', 'SD', '249', '非洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('190', 'Suriname', '苏里南', 'SR', '597', '美洲', '0.6620');
INSERT INTO `tb_national_code` VALUES ('191', 'Swaziland', '斯威士兰', 'SZ', '268', '非洲', '0.4210');
INSERT INTO `tb_national_code` VALUES ('192', 'Sweden', '瑞典', 'SE', '46', '欧洲', '0.7740');
INSERT INTO `tb_national_code` VALUES ('193', 'Switzerland', '瑞士', 'CH', '41', '欧洲', '0.5660');
INSERT INTO `tb_national_code` VALUES ('194', 'Syrian Arab Republic', '叙利亚', 'SY', '963', '亚洲', '0.9190');
INSERT INTO `tb_national_code` VALUES ('195', 'Taiwan', '台湾地区', 'TW', '886', '亚洲', '0.4800');
INSERT INTO `tb_national_code` VALUES ('196', 'Tajikistan', '塔吉克', 'TJ', '992', '亚洲', '0.2290');
INSERT INTO `tb_national_code` VALUES ('197', 'Tanzania', '坦桑尼亚', 'TZ', '255', '非洲', '1.1430');
INSERT INTO `tb_national_code` VALUES ('198', 'Thailand', '泰国', 'TH', '66', '亚洲', '0.2610');
INSERT INTO `tb_national_code` VALUES ('199', 'Togo', '多哥', 'TG', '228', '非洲', '0.4370');
INSERT INTO `tb_national_code` VALUES ('200', 'Tokelau', '托克劳', 'TK', '690', '大洋洲', '3.2300');
INSERT INTO `tb_national_code` VALUES ('201', 'Tonga', '汤加', 'TO', '676', '大洋洲', '0.5820');
INSERT INTO `tb_national_code` VALUES ('202', 'Trinidad and Tobago', '特立尼达和多巴哥', 'TT', '1868', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('203', 'Tunisia', '突尼斯', 'TN', '216', '非洲', '0.9030');
INSERT INTO `tb_national_code` VALUES ('204', 'Turkey', '土耳其', 'TR', '90', '亚洲', '0.9430');
INSERT INTO `tb_national_code` VALUES ('205', 'Turkmenistan', '土库曼', 'TM', '993', '亚洲', '0.1640');
INSERT INTO `tb_national_code` VALUES ('206', 'Turks and Caicos Islands', '土克斯及开科斯群岛', 'TC', '1809', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('207', 'Tuvalu', '图瓦卢', 'TV', '688', '大洋洲', '3.2300');
INSERT INTO `tb_national_code` VALUES ('208', 'Uganda', '乌干达', 'UG', '256', '非洲', '1.8710');
INSERT INTO `tb_national_code` VALUES ('209', 'Ukraine', '乌克兰', 'UA', '380', '欧洲', '0.2450');
INSERT INTO `tb_national_code` VALUES ('210', 'United Arab Emirates', '阿拉伯联合酋长国', 'AE', '971', '亚洲', '0.3980');
INSERT INTO `tb_national_code` VALUES ('211', 'United Kingdom', '英国', 'UK', '44', '欧洲', '0.4690');
INSERT INTO `tb_national_code` VALUES ('212', 'United States', '美国', 'US', '1', '美洲', '0.2130');
INSERT INTO `tb_national_code` VALUES ('213', 'Uruguay', '乌拉圭', 'UY', '598', '美洲', '0.5340');
INSERT INTO `tb_national_code` VALUES ('214', 'Uzbekistan', '乌兹别克斯坦', 'UZ', '998', '亚洲', '0.8060');
INSERT INTO `tb_national_code` VALUES ('215', 'Vanuatu', '瓦努阿图', 'VU', '678', '大洋洲', '0.5820');
INSERT INTO `tb_national_code` VALUES ('216', 'Vatican City State (Holy See)', '梵蒂冈(罗马教廷)', 'VA', '39', '欧洲', '0.6300');
INSERT INTO `tb_national_code` VALUES ('217', 'Venezuela', '委内瑞拉', 'VE', '58', '美洲', '0.3090');
INSERT INTO `tb_national_code` VALUES ('218', 'Vietnam', '越南', 'VN', '84', '亚洲', '0.5820');
INSERT INTO `tb_national_code` VALUES ('219', 'Virgin Islands (British)', '维尔京群岛(英国)', 'VG', '1284', '美洲', null);
INSERT INTO `tb_national_code` VALUES ('220', 'Virgin Islands (U.S.)', '维尔京群岛(美国)', 'VI', '1340', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('221', 'Wallis And Futuna Islands', '沃利斯和富图纳群岛', 'WF', '681', '大洋洲', '3.2300');
INSERT INTO `tb_national_code` VALUES ('222', 'Western Sahara', '西撒哈拉', 'EH', '685', '非洲', '0.6620');
INSERT INTO `tb_national_code` VALUES ('223', 'Yemen', '也门', 'YE', '967', '亚洲', '0.4530');
INSERT INTO `tb_national_code` VALUES ('224', 'Yugoslavia', '南斯拉夫', 'YU', '381', '欧洲', '0.2650');
INSERT INTO `tb_national_code` VALUES ('225', 'Zambia', '赞比亚', 'ZM', '260', '非洲', '0.3250');
INSERT INTO `tb_national_code` VALUES ('226', 'Zimbabwe', '津巴布韦', 'ZW', '263', '非洲', '0.4050');
INSERT INTO `tb_national_code` VALUES ('227', 'the Republic of Abkhazia', '阿布哈兹', 'ABH', '7', '亚洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('228', 'the Republic of South Ossetia', '南奥赛梯', '', '7', '亚洲', '0.3410');
INSERT INTO `tb_national_code` VALUES ('229', 'Bailiwick of Guernsey', '格恩西岛', '', '441481', '欧洲', null);
INSERT INTO `tb_national_code` VALUES ('230', 'Bailiwick of Jersey', '泽西岛', '', '44', '欧洲', '0.4690');
INSERT INTO `tb_national_code` VALUES ('231', 'Lao People\'s Democratic Republic', '老挝', 'LAO', '856', '亚洲', '0.5340');
INSERT INTO `tb_national_code` VALUES ('232', 'The Republic of Macedonia', '马其顿', 'MKD', '389', '欧洲', '0.2450');
INSERT INTO `tb_national_code` VALUES ('233', 'The Federation of Saint Kitts and Nevis', '圣基茨和尼维斯', 'KNA', '1869', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('234', 'Santa Luzia Island', '圣卢西亚岛', '', '1758', '亚洲', '0.4800');
INSERT INTO `tb_national_code` VALUES ('235', 'Saint Vincent and the Grenadines', '圣文森特和格林纳丁斯', 'VCT', '1784', '美洲', '0.5010');
INSERT INTO `tb_national_code` VALUES ('236', 'Sao Tome and Principe', '圣多美和普林西比', 'STP', '239', '非洲', '0.6620');
INSERT INTO `tb_national_code` VALUES ('237', 'Saint Martin', '圣马丁岛', '', '590', '亚洲', '1.3040');
INSERT INTO `tb_national_code` VALUES ('238', 'The Republic of South Sudan', '南苏丹共和国', 'SSD', '211', '非洲', '0.6620');

-- ----------------------------
-- Table structure for tb_sms_captcha
-- ----------------------------
DROP TABLE IF EXISTS `tb_sms_captcha`;
CREATE TABLE `tb_sms_captcha` (
  `id` varchar(36) DEFAULT NULL COMMENT '主键',
  `area_code` varchar(10) DEFAULT '' COMMENT '国际区号(86,852)',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `code` varchar(8) DEFAULT NULL COMMENT '验证码',
  `status` varchar(6) DEFAULT '0' COMMENT '状态(0-可用 1-失效)',
  `lastTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `type` varchar(6) DEFAULT '0' COMMENT '短信类别(0-注册,1-忘记密码,2-重置交易密码)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='验证码信息';

-- ----------------------------
-- Records of tb_sms_captcha
-- ----------------------------
