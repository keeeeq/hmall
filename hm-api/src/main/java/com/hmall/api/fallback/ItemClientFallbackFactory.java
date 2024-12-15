package com.hmall.api.fallback;

import com.hmall.api.client.ItemClient;
import com.hmall.api.dto.ItemDTO;
import com.hmall.common.utils.CollUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collection;
import java.util.List;

@Slf4j
public class ItemClientFallbackFactory implements FallbackFactory<ItemClient> {
    @Override
    public ItemClient create(Throwable cause) {
        return new ItemClient() {
            @Override
            public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
                log.error("查询商品服务原因为：{}", cause.getMessage());
                return CollUtils.emptyList();
            }

            @Override
            public void deductStock(List<Object> detailDTOS) {
                log.error("扣减商品库存原因为：{}", cause.getMessage());
                throw new RuntimeException(cause);
            }
        };
    }
}
