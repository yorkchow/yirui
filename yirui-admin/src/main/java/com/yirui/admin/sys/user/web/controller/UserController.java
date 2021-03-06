package com.yirui.admin.sys.user.web.controller;

import com.google.common.collect.Maps;
import com.yirui.common.Constants;
import com.yirui.common.entity.enums.BooleanEnum;
import com.yirui.common.entity.search.utils.SearchConvertUtils;
import com.yirui.common.web.controller.BaseCRUDController;
import com.yirui.common.web.validate.ValidateResponse;
import com.yirui.admin.sys.organization.model.Job;
import com.yirui.admin.sys.organization.model.Organization;
import com.yirui.admin.sys.user.model.User;
import com.yirui.admin.sys.user.model.UserOrganizationJob;
import com.yirui.admin.sys.user.model.UserStatus;
import com.yirui.admin.sys.user.service.UserService;
import com.yirui.admin.sys.user.web.bind.annotation.CurrentUser;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/13
 * Time: 22:16
 */
@Controller("adminUserController")
@RequestMapping(value = "/admin/sys/user")
public class UserController extends BaseCRUDController<User, Integer> {

    @Autowired
    private UserService userService;

    /*private UserService getUserService() {
        return (UserService) baseService;
    }*/

    public UserController() {
        setResourceIdentity("sys:user");
    }

    @Override
    protected void setCommonData(Model model) {
        model.addAttribute("statusList", UserStatus.values());
        model.addAttribute("booleanList", BooleanEnum.values());
    }


    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main(Model model) {
        return viewName("main");
    }

    @RequestMapping(value = "tree", method = RequestMethod.GET)
    public String tree(Model model) {
        return viewName("tree");
    }


    @RequestMapping(value = "list/discard", method = RequestMethod.GET)
    public String list(Model model) {
        throw new RuntimeException("discarded method");
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    // 搜索参数格式: k(键)_op(操作符)=v(值)
    // condition表是关系连接器：or,and
    public String listAll(@RequestParam(value = "search", required = true, defaultValue = "") String searchParams,
                          @RequestParam(value = "condition", required = true, defaultValue = "1=1") String condition,
                          Model model) {

        return list(null, null, searchParams, condition, model);
    }


    @RequestMapping(value = {"{organization}/{job}"}, method = RequestMethod.GET)
    public String list(
            @PathVariable("organization") Organization organization,
            @PathVariable("job") Job job,
            @RequestParam(value = "search", required = true, defaultValue = "") String searchParams,
            @RequestParam(value = "condition", required = true, defaultValue = "1=1") String condition, Model model) {

        setCommonData(model);

        String searchValue = SearchConvertUtils.getConvertValue(searchParams, condition);
        Map<String, Object> search = Maps.newHashMap();
        search.put("search", searchValue);

        if (organization != null && !organization.isRoot()) {
            search.put("organizationId", organization.getId());
        }
        if (job != null && !job.isRoot()) {
            search.put("jobId", job.getId());
        }

        return super.list(search, model);
    }


    @RequestMapping(value = "create/discard", method = RequestMethod.POST)
    @Override
    public String create(
            Model model, @Valid @ModelAttribute("m") User m, BindingResult result,
            RedirectAttributes redirectAttributes) {
        throw new RuntimeException("discarded method");
    }

    @RequestMapping(value = "{id}/update/discard", method = RequestMethod.POST)
    @Override
    public String update(
            Model model, @Valid @ModelAttribute("m") User m, BindingResult result,
            @RequestParam(value = Constants.BACK_URL, required = false) String backURL,
            RedirectAttributes redirectAttributes) {
        throw new RuntimeException("discarded method");
    }

    /*
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createWithOrganization(
            Model model,
            @Valid @ModelAttribute("m") User m, BindingResult result,
            @RequestParam(value = "organizationId", required = false) Long[] organizationIds,
            @RequestParam(value = "jobId", required = false) Long[][] jobIds,
            RedirectAttributes redirectAttributes) {

        fillUserOrganization(m, organizationIds, jobIds);

        return super.create(model, m, result, redirectAttributes);
    }

    private void fillUserOrganization(User m, Long[] organizationIds, Long[][] jobIds) {
        if (ArrayUtils.isEmpty(organizationIds)) {
            return;
        }
        for (int i = 0, l = organizationIds.length; i < l; i++) {

            //仅新增/修改一个 spring会自动split（“，”）--->给数组
            if (l == 1) {
                for (int j = 0, l2 = jobIds.length; j < l2; j++) {
                    UserOrganizationJob userOrganizationJob = new UserOrganizationJob();
                    userOrganizationJob.setOrganizationId(organizationIds[i]);
                    userOrganizationJob.setJobId(jobIds[j][0]);
                    m.addOrganizationJob(userOrganizationJob);
                }
            } else {
                Long[] jobId = jobIds[i];
                for (int j = 0, l2 = jobId.length; j < l2; j++) {
                    UserOrganizationJob userOrganizationJob = new UserOrganizationJob();
                    userOrganizationJob.setOrganizationId(organizationIds[i]);
                    userOrganizationJob.setJobId(jobId[j]);
                    m.addOrganizationJob(userOrganizationJob);
                }
            }

        }
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String updateWithOrganization(
            Model model, @Valid @ModelAttribute("m") User m, BindingResult result,
            @RequestParam(value = "organizationId", required = false) Long[] organizationIds,
            @RequestParam(value = "jobId", required = false) Long[][] jobIds,
            @RequestParam(value = Constants.BACK_URL, required = false) String backURL,
            RedirectAttributes redirectAttributes) {

        fillUserOrganization(m, organizationIds, jobIds);

        return super.update(model, m, result, backURL, redirectAttributes);
    }


    @RequestMapping(value = "changePassword")
    public String changePassword(
            HttpServletRequest request,
            @RequestParam("ids") Long[] ids, @RequestParam("newPassword") String newPassword,
            @CurrentUser User opUser,
            RedirectAttributes redirectAttributes) {

        getUserService().changePassword(opUser, ids, newPassword);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "改密成功！");

        return redirectToUrl((String) request.getAttribute(Constants.BACK_URL));
    }

    @RequestMapping(value = "changeStatus/{newStatus}")
    public String changeStatus(
            HttpServletRequest request,
            @RequestParam("ids") Long[] ids,
            @PathVariable("newStatus") UserStatus newStatus,
            @RequestParam("reason") String reason,
            @CurrentUser User opUser,
            RedirectAttributes redirectAttributes) {

        getUserService().changeStatus(opUser, ids, newStatus, reason);

        if (newStatus == UserStatus.normal) {
            redirectAttributes.addFlashAttribute(Constants.MESSAGE, "解封成功！");
        } else if (newStatus == UserStatus.blocked) {
            redirectAttributes.addFlashAttribute(Constants.MESSAGE, "封禁成功！");
        }

        return redirectToUrl((String) request.getAttribute(Constants.BACK_URL));
    }

    @RequestMapping(value = "recycle")
    public String recycle(HttpServletRequest request, @RequestParam("ids") Long[] ids, RedirectAttributes redirectAttributes) {
        for (Long id : ids) {
            User user = getUserService().findOne(id);
            user.setDeleted(Boolean.FALSE);
            getUserService().update(user);
        }
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "还原成功！");
        return redirectToUrl((String) request.getAttribute(Constants.BACK_URL));
    }


    @RequestMapping("{user}/organizations")
    public String permissions(@PathVariable("user") User user) {
        return viewName("organizationsTable");
    }


    @RequestMapping("ajax/autocomplete")
    @PageableDefaults(value = 30)
    @ResponseBody
    public Set<Map<String, Object>> autocomplete(
            Searchable searchable,
            @RequestParam("term") String term) {

        return getUserService().findIdAndNames(searchable, term);
    }
    */

    /**
     * 验证返回格式
     * 单个：[fieldId, 1|0, msg]
     * 多个：[[fieldId, 1|0, msg],[fieldId, 1|0, msg]]
     *
     * @param fieldId
     * @param fieldValue
     * @return
     */
    /*
    @RequestMapping(value = "validate", method = RequestMethod.GET)
    @ResponseBody
    public Object validate(
            @RequestParam("fieldId") String fieldId, @RequestParam("fieldValue") String fieldValue,
            @RequestParam(value = "id", required = false) Long id) {

        ValidateResponse response = ValidateResponse.newInstance();


        if ("username".equals(fieldId)) {
            User user = getUserService().findByUsername(fieldValue);
            if (user == null || (user.getId().equals(id) && user.getUsername().equals(fieldValue))) {
                //如果msg 不为空 将弹出提示框
                response.validateSuccess(fieldId, "");
            } else {
                response.validateFail(fieldId, "用户名已被其他人使用");
            }
        }

        if ("email".equals(fieldId)) {
            User user = getUserService().findByEmail(fieldValue);
            if (user == null || (user.getId().equals(id) && user.getEmail().equals(fieldValue))) {
                //如果msg 不为空 将弹出提示框
                response.validateSuccess(fieldId, "");
            } else {
                response.validateFail(fieldId, "邮箱已被其他人使用");
            }
        }

        if ("mobilePhoneNumber".equals(fieldId)) {
            User user = getUserService().findByMobilePhoneNumber(fieldValue);
            if (user == null || (user.getId().equals(id) && user.getMobilePhoneNumber().equals(fieldValue))) {
                //如果msg 不为空 将弹出提示框
                response.validateSuccess(fieldId, "");
            } else {
                response.validateFail(fieldId, "手机号已被其他人使用");
            }
        }

        return response.result();
    }
    */
}
