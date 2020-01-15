package com.sixkery.dataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author sixkery
 * @date 2019/11/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SellerInfo {
    @Id
    private String sellerId;
    private String username;
    private String password;
    private String openId;
}
