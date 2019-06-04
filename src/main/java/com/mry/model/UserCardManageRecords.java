package com.mry.model;

import lombok.Data;
import javax.persistence.*;

/**
 * @author: cddufu@cn.ibm.com
 * @date:
 * @desc: 用户卡消费记录
 */
@Entity
@Table(name = "user_card_manage_records")
@Data
public class UserCardManageRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "card_id")
    private int cardId;
    @Column(name = "card_item_id")
    private int cardItemId;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "card_option")
    private String cardOption;
    @Column(name = "cons_type")
    private String consType;
    @Column(name = "cons_money")
    private String consMoney;
    @Column(name = "cons_desc")
    private String consDesc;
    @Column(name = "card_item")
    private String cardItem;
    @Column(name = "cons_date")
    private String consDate;
}
