package com.tiger.common.core.domain.model;

import com.tiger.common.annotation.Log;

import java.util.List;

/**
 * 用户注册对象
 * 
 * @author tiger
 */
public class RegisterBody extends LoginBody
{
    private List<Long> roleIds;

    private Long organizationId;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
