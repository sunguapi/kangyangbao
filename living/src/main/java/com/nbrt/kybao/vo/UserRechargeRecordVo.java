package com.nbrt.kybao.vo;

import com.nbrt.kybao.entity.UserRechargeRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sunjinbao
 * @date 2022/6/11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRechargeRecordVo {

    private Integer integers;

    private List<UserRechargeRecord> records;
}
