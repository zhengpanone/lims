package com.zp.lims.sys.controller;

import com.zp.lims.common.core.response.PageResult;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.controller.dto.AdminDTO;
import com.zp.lims.sys.controller.dto.UserDTO;
import com.zp.lims.sys.controller.vo.AdminVO;
import com.zp.lims.sys.controller.vo.LoginInfoVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * PermissionController
 *
 * @author zhengpanone
 * @since 2022-11-27
 */
@Slf4j
@RestController
@RequestMapping("/setting")
public class TokenController {

   /* @PostMapping("/login")
    public R<LoginVO> login(@Validated @RequestBody LoginDTO loginDTO) {
        log.info("提交的用户数据为:" + loginDTO.toString());
        verifyService.checkCode(loginDTO.getImgCode());
        LoginVO loginVO = new LoginVO();
        loginVO.setToken("token");
        loginVO.setVersion("1.0.0");
        loginVO.setExpiresTime(new Date());
        UserInfo userInfo = new UserInfo();
        userInfo.setId("1");
        userInfo.setAccount(loginDTO.getAccount());
        loginVO.setUserInfo(userInfo);
        return R.success(loginVO);
    }*/




    @PostMapping("/logout")
    public R<?> logout() {
        return R.success();
    }
    /**
     * 添加用户
     * @param userDTO
     * @return
     */
  /*  @PostMapping("/admin")
    @ApiOperation("添加用户")
    public R<Boolean> addUser(@RequestBody UserDTO userDTO) {
        return R.success(userService.saveUser(userDTO));
    }*/


    @PutMapping("/admin/{id}")
    @ApiOperation("更新用户信息")
    public R<?> updateUser(@PathVariable("id") String id, @RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        return R.success();
    }
    @GetMapping("/user/info")
    public R<LoginInfoVO> loginInfo() {
        LoginInfoVO infoVO = new LoginInfoVO();
        infoVO.setSlide(new String[]{"a","b","c"});
        infoVO.setLoginLogo("loginLogo");
        infoVO.setLogoRectangle("logo rectangle");
        infoVO.setLogoSquare("logo square");

        return R.success(infoVO);
    }


    @GetMapping("/admin")
    @ApiOperation("获取用户信息")
    public R<PageResult<AdminVO>> getAdmins(AdminDTO adminDTO) {
        System.out.println(adminDTO);
        PageResult<AdminVO> page = new PageResult<>();
        AdminVO adminVO1 = new AdminVO();
        adminVO1.setId("1");
        adminVO1.setRealName("realName1");
        adminVO1.setAccount("admin1");
        adminVO1.setStatus(0);
        AdminVO adminVO2 = new AdminVO();
        adminVO2.setId("2");
        adminVO2.setRealName("realName2");
        adminVO2.setAccount("admin2");
        adminVO2.setStatus(1);
        AdminVO adminVO3 = new AdminVO();
        adminVO3.setId("3");
        adminVO3.setRealName("realName2");
        adminVO3.setAccount("admin2");
        adminVO3.setStatus(1);
        AdminVO adminVO4 = new AdminVO();
        adminVO4.setId("4");
        adminVO4.setRealName("realName2");
        adminVO4.setStatus(0);
        adminVO4.setAccount("admin2");
        page.setTotal(4L);
        page.setList(Arrays.asList(adminVO1, adminVO2, adminVO3, adminVO4));
        return R.success(page);
    }

    @DeleteMapping("/admin/{id}")
    public R<?> deleteAdmin(@PathVariable("id") String id) {
        System.out.println(id);
        return R.success();
    }




    @PutMapping("/admin/status/{id}/status/{status}")
    public R<?> updateAdminStatus(@PathVariable("id") String id, @PathVariable("status") int status) {
        System.out.println(id);
        System.out.println(status);
        return R.success();
    }


    @GetMapping("/admin/roles")
    public R<?> getRoles() {
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        Map<String, String> map3 = new HashMap<>();
        Map<String, String> map4 = new HashMap<>();
        map1.put("code", "1");
        map1.put("name", "超级管理员");
        map2.put("code", "2");
        map2.put("name", "运营");
        map3.put("code", "3");
        map3.put("name", "财务");
        map4.put("code", "4");
        map4.put("name", "测试账号");
        ArrayList<Map<String, String>> data = new ArrayList<>();
        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);
        return R.success(data);
    }


    @GetMapping("/admin/{id}/edit")
    public R<?> getAdmin(@PathVariable("id") String id) {
        AdminVO adminVO = new AdminVO();
        adminVO.setId("1");
        adminVO.setRealName("realName1");
        adminVO.setAccount("admin1");
        adminVO.setRoles(new String[]{"1", "2"});
        adminVO.setStatus(0);
        return R.success(adminVO);
    }
}
