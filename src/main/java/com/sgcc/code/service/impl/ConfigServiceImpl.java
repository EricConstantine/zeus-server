package com.sgcc.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgcc.code.common.exception.CommonException;
import com.sgcc.code.common.utils.*;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.Config;
import com.sgcc.code.entity.Dictionary;
import com.sgcc.code.entity.User;
import com.sgcc.code.entity.io.config.*;
import com.sgcc.code.entity.io.zonetree.ZonetreeIo;
import com.sgcc.code.entity.io.zonetree.ZonetreeQueryIo;
import com.sgcc.code.mapper.ConfigMapper;
import com.sgcc.code.service.ConfigService;
import com.sgcc.code.service.DictionaryService;
import com.sgcc.code.service.UserService;
import com.sgcc.code.service.ZonetreeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 配置事务
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 10 09:42:21 CST 2023
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

	/**
	 * MD5加密 盐值
	 */

	@Value("${zeus.salt}")
	private String SALTER;


	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ConfigMapper configMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private DictionaryService dictionaryService;

	@Autowired
	private ZonetreeService zonetreeService;

	//查询配置分页数据
	public ResponseModel<UapPage<ConfigIo>> query(RequestModel<ConfigQueryIo> body, HttpServletRequest httpServletRequest){
		logger.info("查询配置分页数据 ===> " + body.getParameter());
		QueryWrapper<Map> queryWrapper = new QueryWrapper<>();
		User user = userService.findByUsername(httpServletRequest);
		if(body.getParameter().getProjectid()!=null) queryWrapper.eq("projectid", body.getParameter().getProjectid());
		if(StringUtils.isNotEmpty(body.getParameter().getName())) queryWrapper.like("name", body.getParameter().getName());
		if(StringUtils.isNotEmpty(body.getParameter().getConfigDescribe())) queryWrapper.like("config_describe", body.getParameter().getConfigDescribe());
		if(StringUtils.isNotEmpty(body.getParameter().getPath())) queryWrapper.like("path", body.getParameter().getPath());
		if(body.getParameter().getDbtype() != null) queryWrapper.eq("dbtype", body.getParameter().getDbtype());
		if(StringUtils.isNotEmpty(body.getParameter().getDbhost())) queryWrapper.like("dbhost", body.getParameter().getDbhost());
		if(StringUtils.isNotEmpty(body.getParameter().getDbname())) queryWrapper.like("dbname", body.getParameter().getDbname());
		if(StringUtils.isNotEmpty(body.getParameter().getDbuser())) queryWrapper.like("dbuser", body.getParameter().getDbuser());
		if(StringUtils.isNotEmpty(body.getParameter().getDbpassword())) queryWrapper.like("dbpassword", body.getParameter().getDbpassword());
		if(user!=null) queryWrapper.eq("userid", user.getUserid());
		//查询出mybatisplus的分页对象 并转换成内部分页对象
        //IPage<Config> iPage = super.page(new Page(body.getCurrent(), body.getSize()), queryWrapper);
		// 自定义分页查询
		queryWrapper.orderByDesc("create_time");
		IPage<Map> iPage = configMapper.selectPageList(new Page(body.getCurrent(), body.getSize()), queryWrapper);
        return ResponseModel.success(BeanUtil.convertPage(iPage,ConfigIo.class), body.getHeader());
	}
	
	//查询配置记录
	public ResponseModel<List<ConfigIo>> queryAll(RequestModel<ConfigQueryIo> body){
		logger.info("查询配置数据列表 ===> " + body.getParameter());
		QueryWrapper<Map> queryWrapper = new QueryWrapper<>();
		if(body.getParameter().getProjectid()!=null) queryWrapper.eq("projectid", body.getParameter().getProjectid());
		if(StringUtils.isNotEmpty(body.getParameter().getName())) queryWrapper.like("name", body.getParameter().getName().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getConfigDescribe())) queryWrapper.like("config_describe", body.getParameter().getConfigDescribe().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getPath())) queryWrapper.like("path", body.getParameter().getPath().trim());
		if(body.getParameter().getDbtype() != null) queryWrapper.eq("dbtype", body.getParameter().getDbtype());
		if(StringUtils.isNotEmpty(body.getParameter().getDbhost())) queryWrapper.like("dbhost", body.getParameter().getDbhost().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getDbname())) queryWrapper.like("dbname", body.getParameter().getDbname().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getDbuser())) queryWrapper.like("dbuser", body.getParameter().getDbuser().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getDbpassword())) queryWrapper.like("dbpassword", body.getParameter().getDbpassword().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getUserid())) queryWrapper.like("userid", body.getParameter().getUserid());
		queryWrapper.orderByDesc("create_time");
		List<Map> configs = configMapper.queryAll(queryWrapper);
        if(configs == null) return ResponseModel.failure("获取数据发生异常，请重试");
        return ResponseModel.success(BeanUtil.convert(configs,ConfigIo.class),body.getHeader());
	}
	
	//获取单条配置
	public ResponseModel<ConfigIo> view(RequestModel<ConfigViewIo> body){
		logger.info("查询配置详情数据 ===> " + body.getParameter());
		Config config = super.getById(body.getParameter().getId());
		if(config == null) return ResponseModel.failure("未找到对应的数据记录");
		return ResponseModel.success(BeanUtil.convert(config,ConfigIo.class),body.getHeader());
	}
	
	//保存配置
	public ResponseModel<Boolean> add(RequestModel<ConfigAddIo> body, HttpServletRequest httpServletRequest){
		logger.info("保存配置数据 ===> " + body.getParameter());
		User user = userService.findByUsername(httpServletRequest);
		Config config = BeanUtil.convert(body.getParameter(),Config.class);
		//用户名密码加密
		String sk = DigestUtils.md5Hex(user.getUsername()+SALTER);
		try {
			String encodeName = ThreeDESUtils.encode(config.getDbuser(), sk.substring(0,24));
			String encodePwd = ThreeDESUtils.encode(config.getDbpassword(), sk.substring(0,24));
			config.setDbuser(encodeName);
			config.setDbpassword(encodePwd);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		config.setUserid(user.getUserid());
		config.setPath(config.getPath().replace("\\","/"));
		return ResponseModel.success("保存成功",super.save(config),body.getHeader());
	}
	
	//删除配置
	public ResponseModel<Boolean> delete(RequestModel<String> body){
		logger.info("删除配置数据 ===> " + body.getParameter());
		boolean flag = false;
		StringUtil.isValidIds(body.getParameter());//验证ID是否合法
		String[] idsArr = body.getParameter().split(",");
	    for(String idStr:idsArr){
			flag = super.removeById(Integer.parseInt(idStr));
        }
		return ResponseModel.success("删除成功",flag,body.getHeader());
	}
	
	//修改配置
	public ResponseModel<Boolean> update(RequestModel<ConfigUpdateIo> body, HttpServletRequest httpServletRequest){
		logger.info("修改配置数据 ===> " + body.getParameter());
		Config config = BeanUtil.convert(body.getParameter(),Config.class);
		User user = userService.findByUsername(httpServletRequest);
		//用户名密码加密
		String sk = DigestUtils.md5Hex(user.getUsername()+SALTER);
		try {
			String encodeName = ThreeDESUtils.encode(config.getDbuser(), sk.substring(0,24));
			String encodePwd = ThreeDESUtils.encode(config.getDbpassword(), sk.substring(0,24));
			config.setDbuser(encodeName);
			config.setDbpassword(encodePwd);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ResponseModel.success("修改成功",super.updateById(config),body.getHeader());
	}
	
	
	// 导入配置
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<String> imports(RequestModel<List<ConfigAddIo>> body){
		logger.info("导入配置数据 ===> 文件" );
		int imp_num = 0;
		String[] titleNameArray = {"项目id","配置名称","配置描述","生成代码根目录","数据库类型 1mysql 2oracle","数据库地址","数据库名称","数据库用户","数据库密码","用户id","创建时间"};
		String[] fieldNameArray = {"projectid","name","describe","path","dbtype","dbhost","dbname","dbuser","dbpassword","userid","createTime"};
		try {
			for (ConfigAddIo configAddIo : body.getParameter()) {
				Config config = BeanUtil.convert(configAddIo,Config.class);
				super.save(config);
				imp_num++;
			}
		}catch (Exception e) {
			throw new CommonException(500,"导入失败,请确认Excel内容是否正确。</br>错误行号："+(imp_num+2)+"。</br>错误信息："+ExcelUtil.getPointOfException(e.getMessage(),fieldNameArray,titleNameArray)+"。");
		}
		return ResponseModel.success("导入成功，总条数："+imp_num+"条！");
	}

	//获取用户配置列表
	@Override
	public ResponseModel<List<Map<String, Object>>> getConfigs(HttpServletRequest httpServletRequest) {
		logger.info("获取用户配置列表 ===> " );
		User user = userService.findByUsername(httpServletRequest);
		List<Map<String,Object>> configs = new ArrayList<>();
		if(user.getType()==1){
			configs = configMapper.getConfigs(null);
		}else{
			configs = configMapper.getConfigs(user.getUserid());
		}
		return ResponseModel.success(configs);
	}

	//获取配置项目详情
	@Override
	public ResponseModel<Map<String, Object>> getProjectDetail(RequestModel<ConfigViewIo> body) {
		logger.info("获取配置项目详情 ===> " + body.getParameter());
		Map<String,Object> resultMap = new HashMap<>();
		Config config = BeanUtil.convert(body.getParameter(),Config.class);
		// 查询数据库配置信息
		Config con = super.getById(config.getId());
		Integer projectid = con.getProjectid();
		// 查询项目字典配置
		QueryWrapper<Dictionary> dictionaryWrapper = new QueryWrapper<>();
		dictionaryWrapper.eq("projectid",projectid);
		dictionaryWrapper.orderByDesc("create_time");
		List<Dictionary> dictionaryList = dictionaryService.list(dictionaryWrapper);
		// 查询空间树
		RequestModel<ZonetreeQueryIo> requestModel = RequestModel.getInstance(new ZonetreeQueryIo(projectid.toString()));
		List<ZonetreeIo> zonetreeIoList = zonetreeService.queryAll(requestModel).getData();
		resultMap.put("dictionarys", dictionaryList);
		resultMap.put("zonetree", zonetreeIoList);
		resultMap.put("config", con);
		return ResponseModel.success(resultMap);
	}

	//测试数据库连接
	@Override
	public ResponseModel<Map> testDb(RequestModel<ConfigConnIo> body) {
		logger.info("测试数据库连接 ===> " + body.getParameter());
		Connection conn = null;// 创建一个数据库连接
		PreparedStatement pre = null;// 创建预编译语句对象
		ResultSet result = null;// 创建一个结果集对象
		Config config = null;
		boolean flag = false;
		Map resultMap = new HashMap<>();
		try {
			//获取Driver实现类的对象，使用反射
			Class clazz = Class.forName("com.mysql.jdbc.Driver");
			Driver driver = (Driver) clazz.newInstance();
			//注册驱动
			DriverManager.registerDriver(driver);
			//获取配置信息
			config = super.getById(body.getParameter().getId());
			if(config!=null){
				String url = "jdbc:mysql://"+ config.getDbhost() +":"+ config.getDbport() +"/"+ config.getDbname() +"?serverTimezone=UTC&characterEncoding=utf8&useSSL=false";
				if(config.getDbtype()==1){
					url = "jdbc:mysql://"+ config.getDbhost() +":"+ config.getDbport() +"/"+ config.getDbname() +"?serverTimezone=UTC&characterEncoding=utf8&useSSL=false";
				}else if(config.getDbtype()==2){
					url = "jdbc:oracle:" + "thin:@"+ config.getDbhost() +":"+ config.getDbport() +":"+ config.getDbname();
				}
				String user = config.getDbuser();
				String pwd = config.getDbpassword();
				//获取连接
				conn = DriverManager.getConnection(url,user,pwd);
				//测试sql
				String sql= "select 1 val from DUAL";
				// 实例化预编译语句
				pre = conn.prepareStatement(sql);
				//执行sql语句
				result = pre.executeQuery();
				String val = "";
				while(result.next()){
					val = result.getString("val");
				}
				System.out.println("获取sql返回数据为：" + val);
				flag = StringUtils.isNotBlank(val) && "1".equals(val) ?true:false;
				resultMap.put("isNot", flag);
				resultMap.put("message", (config.getDbtype()==1?"MYSQL":"ORACLE")+"数据库连接: 成功!");
				return ResponseModel.success((config.getDbtype()==1?"MYSQL":"ORACLE")+"数据库连接: 成功!", resultMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try{
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (conn != null)
					conn.close();
				System.out.println("数据库连接已关闭！");
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		resultMap.put("isNot", flag);
		resultMap.put("message", (config.getDbtype()==1?"MYSQL":"ORACLE")+"数据库连接: 失败!");
		return ResponseModel.success((config.getDbtype()==1?"MYSQL":"ORACLE")+"数据库连接: 失败!", resultMap);
	}

	//获取配置项目数据库表信息
	@Override
	public ResponseModel<List<Map>> getConnTables(RequestModel<ConfigConnIo> body) {
		logger.info("获取配置项目数据库表信息 ===> " + body.getParameter());
		List<Map> resultMaps = new ArrayList<>();
		//获取配置信息
		Config config = super.getById(body.getParameter().getId());
		if(config!=null){
			String dbname = config.getDbname();
			String dbuser = config.getDbuser();
			if(config.getDbtype()==1){
				resultMaps = configMapper.queryMysqlTables(dbname);
			}else if(config.getDbtype()==2){
				resultMaps = configMapper.queryOracleTables(dbuser.toLowerCase());
			}
		}
		return ResponseModel.success("获取成功", resultMaps);
	}

	//获取配置项目数据库表信息详情
	@Override
	public ResponseModel<List<Map>> getConnTableView(RequestModel<ConfigConnIo> body) {
		logger.info("获取配置项目数据库表信息详情 ===> " + body.getParameter());
		List<Map> resultMaps = new ArrayList<>();
		String tableName = body.getParameter().getTableName();
		//获取配置信息
		Config config = super.getById(body.getParameter().getId());
		if(config!=null){
			String dbname = config.getDbname();
			if(config.getDbtype()==1){
				resultMaps = configMapper.getMysqlTableView(dbname, tableName);
			}else if(config.getDbtype()==2){
				resultMaps = configMapper.getOracleTableView(dbname, tableName);
			}
		}
		return ResponseModel.success("获取成功", resultMaps);
	}
}