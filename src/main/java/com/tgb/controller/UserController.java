package com.tgb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tgb.model.User;
import com.tgb.service.UserService;

import com.tgb.model.MsgHander;
import com.tgb.tool.DynamicCodeUtil;
import com.tgb.tool.JSONObjectAbstractController;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 获取所有用户列表 [返回为jsp页面]
	 * @param request
	 * @return
	 */
	@RequestMapping("/userList")
	public String getAllUser(HttpServletRequest request){
		
		List<User> findAll = userService.findAll();
		
		request.setAttribute("userList", findAll);
		return "/allUser";
	}
	/**
	 * 获取所有用户列表 [返回为json]
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userInfoList", method = RequestMethod.GET)
	@ResponseBody
	public MsgHander getUserList(HttpServletRequest request) {
		MsgHander msg = new MsgHander();
		List<User> findAll = userService.findAll();
		msg.setStatus(MsgHander.CONTROLLER_CODE_SUCCESS);
		msg.setContext(findAll);
		return msg;
	}
	/**
	 * 根据id查询单个用户信息
	 * @param user_id
	 * @return
	 */
	@RequestMapping(value = "/userInfoList/{user_id}", method = RequestMethod.GET)
	@ResponseBody
	public MsgHander getUserInfo(@PathVariable String user_id) {
		MsgHander msg = new MsgHander();
		User user_info = userService.findById(user_id);
		msg.setStatus(MsgHander.CONTROLLER_CODE_SUCCESS);
		msg.setContext(user_info);
		return msg;
	}
	/**
	 * 根据id查询单个用户 /getUser?id=xxxxx
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUser")
	public String getUser(String id,HttpServletRequest request){
		
		request.setAttribute("user", userService.findById(id));
		return "/editUser";
	}
	/**
	 *  跳转到编辑用户界面  /toEditUser?id=xxxxx
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEditUser")
	public String toEditUser(String id,HttpServletRequest request){
		
		request.setAttribute("user", userService.findById(id));
		return "/editUser";
	}
	/**
	 * 跳转到添加用户界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddUser")
	public String toAddUser(HttpServletRequest request){
		
		return "/addUser";
	}

	/**
	 * 添加用户并重定向
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/addUser")
	public String addUser(User user,HttpServletRequest request){
		userService.save(user);
		return "redirect:/user/userList";
	}
	
	/**
	 *编辑用户
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateUser")
	/*public String updateUser(User user,HttpServletRequest request){//返回页面
		if(userService.update(user)){
			user = userService.findById(user.getId());
			request.setAttribute("user", user);
			return "redirect:/user/userList";
		}else{
			return "/error";
		}
	}*/
	@ResponseBody
	public MsgHander updateUser(User user, HttpServletRequest request) {//返回json
		MsgHander msg = new MsgHander();
		msg.setStatus(MsgHander.CONTROLLER_CODE_SUCCESS);
		if(userService.update(user)){
			user = userService.findById(user.getId());
			request.setAttribute("user", user);
			msg.setStatus(MsgHander.CONTROLLER_CODE_SUCCESS);
		}else{
			msg.setStatus(MsgHander.CONTROLLER_CODE_ERROR);
		}
		return msg;
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delUser")
	public void delUser(String id,HttpServletRequest request,HttpServletResponse response){
		String result = "{\"result\":\"error\"}";
		
		if(userService.delete(id)){
			result = "{\"result\":\"success\"}";
		}
		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
