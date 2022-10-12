package com.allst.micro.mq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Hutu
 * @since 2022-10-12 下午 09:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelPayOrderDTO implements Serializable {

    private static final long serialVersionUID = -191417001399470940L;

    private Long orderId;
}
