package com.yirui.common.web.controller;

import com.yirui.common.Constants;
import com.yirui.common.model.AbstractModel;
import com.yirui.common.service.BaseService;
import com.yirui.common.web.controller.permission.PermissionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Serializable;
import java.util.Map;

/**
 * 基础CRUD 控制器
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/13
 * Time: 22:26
 */
public abstract class BaseCRUDController<M extends AbstractModel, ID extends Serializable>
        extends BaseController<M, ID> {

    protected BaseService<M, ID> baseService;

    private boolean listAlsoSetCommonData = false;

    protected PermissionList permissionList = null;

    /**
     * 设置基础service
     *
     * @param baseService
     */
    public void setBaseService(BaseService<M, ID> baseService) {
        this.baseService = baseService;
    }

    /**
     * 列表也设置common data
     */
    public void setListAlsoSetCommonData(boolean listAlsoSetCommonData) {
        this.listAlsoSetCommonData = listAlsoSetCommonData;
    }

    /**
     * 权限前缀：如sys:user
     * 则生成的新增权限为 sys:user:create
     */
    public void setResourceIdentity(String resourceIdentity) {
        if (!StringUtils.isEmpty(resourceIdentity)) {
            permissionList = PermissionList.newPermissionList(resourceIdentity);
        }
    }


    public String list(Map<String, Object> search, Model model) {

        search.put("sort", "id desc");

        if (permissionList != null) {
            this.permissionList.assertHasViewPermission();
        }

        model.addAttribute("page", baseService.findAllBySearch(search));
        if (listAlsoSetCommonData) {
            setCommonData(model);
        }
        return viewName("list");
    }

    /**
     * 仅返回表格数据
     *
     * @param searchable
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, headers = "table=true")
    public String listTable(Map<String, Object> search, Model model) {
        list(search, model);
        return viewName("listTable");
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") M m) {

        if (permissionList != null) {
            this.permissionList.assertHasViewPermission();
        }

        setCommonData(model);
        model.addAttribute("m", m);
        model.addAttribute(Constants.OP_NAME, "查看");
        return viewName("editForm");
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {

        if (permissionList != null) {
            this.permissionList.assertHasCreatePermission();
        }

        setCommonData(model);
        model.addAttribute(Constants.OP_NAME, "新增");
        if (!model.containsAttribute("m")) {
            model.addAttribute("m", newModel());
        }
        return viewName("editForm");
    }


    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(
            Model model, @ModelAttribute("m") M m, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (permissionList != null) {
            this.permissionList.assertHasCreatePermission();
        }

        if (hasError(m, result)) {
            return showCreateForm(model);
        }
        baseService.save(m);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "新增成功");
        return redirectToUrl(null);
    }


    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") M m, Model model) {

        if (permissionList != null) {
            this.permissionList.assertHasUpdatePermission();
        }

        setCommonData(model);
        model.addAttribute(Constants.OP_NAME, "修改");
        model.addAttribute("m", m);
        return viewName("editForm");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(
            Model model, @ModelAttribute("m") M m, BindingResult result,
            @RequestParam(value = Constants.BACK_URL, required = false) String backURL,
            RedirectAttributes redirectAttributes) {

        if (permissionList != null) {
            this.permissionList.assertHasUpdatePermission();
        }

        if (hasError(m, result)) {
            return showUpdateForm(m, model);
        }
        baseService.update(m);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "修改成功");
        return redirectToUrl(backURL);
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") M m, Model model) {

        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }

        setCommonData(model);
        model.addAttribute(Constants.OP_NAME, "删除");
        model.addAttribute("m", m);
        return viewName("editForm");
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    public String delete(
            @PathVariable("id") M m,
            @RequestParam(value = Constants.BACK_URL, required = false) String backURL,
            RedirectAttributes redirectAttributes) {


        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }

        baseService.delete(m);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "删除成功");
        return redirectToUrl(backURL);
    }

    @RequestMapping(value = "batch/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteInBatch(
            @RequestParam(value = "ids", required = false) ID[] ids,
            @RequestParam(value = Constants.BACK_URL, required = false) String backURL,
            RedirectAttributes redirectAttributes) {


        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }

        baseService.delete(ids);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "删除成功");
        return redirectToUrl(backURL);
    }
}
