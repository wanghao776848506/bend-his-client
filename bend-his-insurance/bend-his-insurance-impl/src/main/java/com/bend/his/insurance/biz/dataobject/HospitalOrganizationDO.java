package com.bend.his.insurance.biz.dataobject;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class HospitalOrganizationDO implements Serializable {

    private Long id;

    /**
     * 医院名称
     */
    private String hospitalName;

    /**
     * 机构名称
     */
    private String organizationName;

    /**
     * 医院ID
     */
    private String hospitalId;

    /**
     * 医院地址
     */
    private String hospitalAddr;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记[0:默认,1:删除]
     */
    private String isDelete;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 操作员ID-[创建]
     */
    private String createUserId;

    /**
     * 操作员ID-[删除]
     */
    private String deleteUserId;

}
