package kr.amc.amis.admin.ui.query;

import kr.amc.amis.admin.ui.dto.GetTableListResponse;
import kr.amc.amis.admin.ui.dto.posts.GetPostTableRequestDto;
import kr.amc.amis.admin.ui.dto.posts.GetPostTableResponseDto;
import kr.amc.amis.admin.ui.dto.users.GetUserTableRequestDto;
import kr.amc.amis.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {

    GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto);
    GetTableListResponse<GetPostTableResponseDto> getPostTableData(GetPostTableRequestDto dto);
}
