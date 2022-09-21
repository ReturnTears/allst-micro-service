package com.allst.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:35
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoPlayDto implements Serializable {
    private String fileId;
    private String playAuth;
}
