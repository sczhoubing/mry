package com.mry.generator;

import com.mry.enums.DateFormat;
import com.mry.utils.CommonUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @author: cddufu@cn.ibm.com
 * @date: 2019-06-14
 * @desc: 自定义主键生成策略, 根据当前时间生成 15 位不重复
 */
public class IdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        synchronized(this) {
            String currentTime = CommonUtils.currentDate(DateFormat.FORMAT6.getFormat());
            return Long.valueOf(currentTime);
        }
    }
}
