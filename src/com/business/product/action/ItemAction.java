package com.business.product.action;

import com.business.product.exception.ItemException;
import com.business.product.po.Item;
import com.business.product.service.ItemService;
import com.business.user.po.User;
import com.business.user.service.UserService;
import com.sysBasic.action.BasicAction;
import com.utils.JSONUtils;
import com.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pxc on 2018/4/30.
 */
@Namespace("/item")
@ParentPackage("json-default")
public class ItemAction extends BasicAction {

    private final Logger logger = LoggerFactory.getLogger(ItemAction.class);

    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

    @Action(value = "inList", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getAllUser() {
        try {
            if (null == item) {
                item = new Item();
            }
            item.setItemStatus("inStock");
            List<Item> items = itemService.getAll(item);
            items.forEach(item1 -> {
                if(StringUtils.isNotBlank(item1.getCustomerId())){
                    item1.setCustomerId(userService.getById(item1.getCustomerId()).getUserAlias());
                }
                if(StringUtils.isNotBlank(item1.getOutUserId())){
                    item1.setOutUserId(userService.getById(item1.getOutUserId()).getUserAlias());
                }
            });
            response = Response.ok(JSONUtils.toJSONInclude(items,"itemId","productId","inPrice",
                    "outPrice","supplierId","productTime","inTime","inUserId","outTime","outUserId","remark",
                    "itemStatus","typeId","customerId","warehouseId","product","productName","type","typeName","warehouse","name",
                    "supplier","userAlias","inUser"));
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    /**
     * 入库
     * @return
     */
    @Action(value = "in", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String itemIn() {
        try {
            itemService.inStock(item,totalCount);
            response = Response.ok();
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    /**
     * 出库
     * @return
     */
    @Action(value = "out", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String itemOut() {
        try {
            itemService.outStock(item,totalCount);
            response = Response.ok();
        } catch (ItemException e) {
            response = Response.error(e.getMessage());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    private Item item;
    private Integer totalCount;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
