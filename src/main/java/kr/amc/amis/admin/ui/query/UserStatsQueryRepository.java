package kr.amc.amis.admin.ui.query;

import java.util.List;
import kr.amc.amis.admin.ui.dto.users.GetDailyRegisterUserResponseDto;

public interface UserStatsQueryRepository {

    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);
}
